package com.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.Account;
import com.bank.models.User;
import com.bank.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAll() {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			AccountStatusDAOImpl asdi = new AccountStatusDAOImpl();
			AccountTypeDAOImpl atdi = new AccountTypeDAOImpl();
			
			String sql = "SELECT * FROM account";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> myList = new ArrayList<>();
			
			while(result.next()) {
				Account myAccount = new Account(
						result.getInt("account_id"),
						result.getDouble("balance"),
						asdi.findById(result.getInt("status_id")),
						atdi.findById(result.getInt("type_id")),
						result.getInt("user_id"));
				myList.add(myAccount);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account findById(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			AccountStatusDAOImpl asdi = new AccountStatusDAOImpl();
			AccountTypeDAOImpl atdi = new AccountTypeDAOImpl();
			String sql = "SELECT * FROM account WHERE account_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			Account myAccount = null;
			
			while(result.next()) {
				myAccount = new Account(
						result.getInt("account_id"),
						result.getDouble("balance"),
						asdi.findById(result.getInt("status_id")),
						atdi.findById(result.getInt("type_id")),
						result.getInt("user_id"));
				
			}
			return myAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addItem(Account a, User u) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "INSERT INTO account (balance, status_id, type_id, user_id) "
					+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			statement.setInt(++index, u.getUserId());
			
			statement.execute();
			
			ResultSet myResultSet = statement.getGeneratedKeys();
			
			myResultSet.next();
			
			return myResultSet.getInt("account_id");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int addItem(Account a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "INSERT INTO account (balance, status_id, type_id, user_id) "
					+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			statement.setInt(++index, a.getUserId());
			
			statement.execute();
			
			ResultSet myResultSet = statement.getGeneratedKeys();
			
			myResultSet.next();
			
			return myResultSet.getInt("account_id");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean removeItemGivenId(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "DELETE FROM account WHERE account_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setInt(++index, id);
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<Account> getUserAccounts(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			AccountStatusDAOImpl asdi = new AccountStatusDAOImpl();
			AccountTypeDAOImpl atdi = new AccountTypeDAOImpl();
			String sql = "SELECT * FROM account WHERE user_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			List<Account> myList = new ArrayList<>();
			
			while(result.next()) {
				Account myAccount = new Account(
						result.getInt("account_id"),
						result.getDouble("balance"),
						asdi.findById(result.getInt("status_id")),
						atdi.findById(result.getInt("type_id")),
						result.getInt("user_id"));
				myList.add(myAccount);
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Account a, User u) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "UPDATE account SET balance = ?, status_id = ?, type_id = ?, user_id = ? WHERE account_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			statement.setInt(++index, u.getUserId());
			statement.setInt(++index, a.getAccountId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getOwnerId(Account myAccount) {
		int id = myAccount.getAccountId();
		
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "SELECT user_id FROM account WHERE account_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			result.next();
			int ownerId = result.getInt("user_id");
				
			return ownerId;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean update(Account a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "UPDATE account SET balance = ?, status_id = ?, type_id = ?, user_id = ? WHERE account_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			statement.setInt(++index, a.getUserId());
			statement.setInt(++index, a.getAccountId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findByStatus(int statusId) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			AccountStatusDAOImpl asdi = new AccountStatusDAOImpl();
			AccountTypeDAOImpl atdi = new AccountTypeDAOImpl();
			
			String sql = "SELECT * FROM account WHERE status_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, statusId);
			
			ResultSet result = statement.executeQuery();
			
			List<Account> myList = new ArrayList<>();
			
			while(result.next()) {
				Account myAccount = new Account(
						result.getInt("account_id"),
						result.getDouble("balance"),
						asdi.findById(result.getInt("status_id")),
						atdi.findById(result.getInt("type_id")),
						result.getInt("user_id"));
				myList.add(myAccount);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Account> findByOwner(int ownerId) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			AccountStatusDAOImpl asdi = new AccountStatusDAOImpl();
			AccountTypeDAOImpl atdi = new AccountTypeDAOImpl();
			
			String sql = "SELECT * FROM account WHERE user_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, ownerId);
			
			ResultSet result = statement.executeQuery();
			
			List<Account> myList = new ArrayList<>();
			
			while(result.next()) {
				Account myAccount = new Account(
						result.getInt("account_id"),
						result.getDouble("balance"),
						asdi.findById(result.getInt("status_id")),
						atdi.findById(result.getInt("type_id")),
						result.getInt("user_id"));
				myList.add(myAccount);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
