package com.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.AccountType;
import com.bank.utils.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO {
	@Override
	public List<AccountType> getAll() {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "SELECT * FROM account_type";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<AccountType> myList = new ArrayList<>();
			
			while(result.next()) {
				AccountType myAccountType = new AccountType(
						result.getInt("type_id"),
						result.getString("type_name"));
				myList.add(myAccountType);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public AccountType findById(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "SELECT * FROM account_type WHERE type_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			AccountType myAccountType = new AccountType();
			
			while(result.next()) {
				myAccountType.setTypeId(result.getInt("type_id"));
				myAccountType.setType(result.getString("type_name"));
			}
			return myAccountType;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addItem(AccountType a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "INSERT INTO account_type (type_name) "
					+ "VALUES (?)";
			
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 0;
			statement.setString(++index, a.getType());
			
			statement.execute();
			
			ResultSet myResultSet = statement.getGeneratedKeys();
			
			myResultSet.next();
			
			return myResultSet.getInt("type_id");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean removeItemGivenId(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "DELETE FROM account_type WHERE type_id = ?";
			
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
}
