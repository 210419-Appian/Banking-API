package com.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.Role;
import com.bank.utils.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO {
	@Override
	public List<Role> getAll() {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "SELECT * FROM user_role";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Role> myList = new ArrayList<>();
			
			while(result.next()) {
				Role myRole = new Role(
						result.getInt("role_id"),
						result.getString("role_title"));
				myList.add(myRole);
				
			}
			return myList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Role findById(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "SELECT * FROM user_role WHERE role_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			Role myRole = new Role();
			
			while(result.next()) {
				myRole.setRoleId(result.getInt("role_id"));
				myRole.setRole(result.getString("role_title"));
			}
			return myRole;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addItem(Role a) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "INSERT INTO user_role (role_title) "
					+ "VALUES (?)";
			
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 0;
			statement.setString(++index, a.getRole());
			
			statement.execute();
			
			ResultSet myResultSet = statement.getGeneratedKeys();
			
			myResultSet.next();
			
			return myResultSet.getInt("role_id");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean removeItemGivenId(int id) {
		try(Connection conn = ConnectionUtil.getDatabaseConnection()){
			String sql = "DELETE FROM user_role WHERE role_id = ?";
			
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
