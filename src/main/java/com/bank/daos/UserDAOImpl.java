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

public class UserDAOImpl implements UserDAO {
	
	@Override
	public List<User> getAll() {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			RoleDAOImpl rdi = new RoleDAOImpl();
			AccountDAOImpl adi = new AccountDAOImpl();
			
			String sql = "SELECT * FROM user_table";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<User> myList = new ArrayList<>();
			
			while(result.next()) {
				User myUser = new User(
						result.getInt("user_id"),
						result.getString("username"),
						result.getString("user_password"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("email"),
						rdi.findById(result.getInt("role_id")),
						adi.getUserAccounts(result.getInt("user_id")));
				myList.add(myUser);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User findById(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			RoleDAOImpl rdi = new RoleDAOImpl();
			AccountDAOImpl adi = new AccountDAOImpl();

			String sql = "SELECT * FROM user_table WHERE user_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			User myUser = null;
			
			while(result.next()) {
				myUser = new User(
						result.getInt("user_id"),
						result.getString("username"),
						result.getString("user_password"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("email"),
						rdi.findById(result.getInt("role_id")),
						adi.getUserAccounts(result.getInt("user_id")));
				
			}
			
			return myUser;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User addItem(User a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			List<Account> b = a.getAccounts();
			StringBuilder sql = new StringBuilder();
			sql.append("BEGIN; ");
			sql.append("INSERT INTO user_table (username, user_password, first_name, last_name, email, role_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?); ");
			
			for(int i = 0; i < b.size(); i++) {
				sql.append("INSERT INTO account (balance, status_id, type_id, user_id) "
					+ "VALUES (?, ?, ?, (SELECT user_id FROM user_table WHERE username = ?)); ");
			}
			
			sql.append("COMMIT;");
			
			PreparedStatement statement = conn.prepareStatement(sql.toString());
			
			int index = 0;
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getFirstName());
			statement.setString(++index, a.getLastName());
			statement.setString(++index, a.getEmail());
			statement.setInt(++index, a.getRole().getRoleId());
			
			for(int i = 0; i < b.size(); i++) {
				statement.setDouble(++index, b.get(i).getBalance());
				statement.setInt(++index, b.get(i).getStatus().getStatusId());
				statement.setInt(++index, b.get(i).getType().getTypeId());
				statement.setString(++index, a.getUsername()); //username is unique and we can't guarantee an id because of SERIAL
			}
			
			statement.execute();
			
			//For whatever reason, Statement.RETURN_GENERATED_KEYS did not work here. I think that it may have something to do with
			//using a transaction. That means that I need to get the keys myself.
			//The easiest way to do this is to make a new user object.
			return findByUsername(a.getUsername());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeItemGivenId(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "DELETE FROM user_table WHERE user_id = ?";
			
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

	@Override
	public boolean update(User a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "UPDATE user_table SET username = ?, user_password = ?, first_name = ?, last_name = ?, email = ?, role_id = ? WHERE user_id = ? ";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getFirstName());
			statement.setString(++index, a.getLastName());
			statement.setString(++index, a.getEmail());
			statement.setInt(++index, a.getRole().getRoleId());
			statement.setInt(++index, a.getUserId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findByUsername(String username) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			RoleDAOImpl rdi = new RoleDAOImpl();
			AccountDAOImpl adi = new AccountDAOImpl();

			String sql = "SELECT * FROM user_table WHERE username = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			User myUser = null;
			
			while(result.next()) {
				myUser = new User(
						result.getInt("user_id"),
						result.getString("username"),
						result.getString("user_password"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("email"),
						rdi.findById(result.getInt("role_id")),
						adi.getUserAccounts(result.getInt("user_id")));
				
			}
			
			return myUser;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
