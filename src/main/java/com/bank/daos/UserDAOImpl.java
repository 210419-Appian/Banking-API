package com.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public int addItem(User a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "INSERT INTO user_table (username, user_password, first_name, last_name, email, role_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 0;
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getFirstName());
			statement.setString(++index, a.getLastName());
			statement.setString(++index, a.getEmail());
			statement.setInt(++index, a.getRole().getRoleId());
			
			statement.execute();
			
			ResultSet myResultSet = statement.getGeneratedKeys();
			
			myResultSet.next();
			
			return myResultSet.getInt("user_id");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
	
}
