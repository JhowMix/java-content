package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.model.User;

public class UserDAO implements GenericDAO{
	private Connection connection;
	
	 public UserDAO(Connection connection) {
		 this.connection = connection;
	}

	@Override
	public void create(Object object) throws ServletException {
		User user = (User) object;
		String sql = "INSERT INTO user(ID,name) VALUES(?,?);";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, user.getID());
			stmt.setString(2, user.getName());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new ServletException(e);
		}		
	}

	@Override
	public List<?> read() {
		try {
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user;");
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			User user = new User();
			user.setID(result.getInt("ID"));
			user.setName(result.getString("name"));
			users.add(user);
		}
		
		result.close();
		stmt.close();
		
		return users;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Object object) {
		User user = (User) object;
		String sql = "UPDATE user SET name=? WHERE ID=?;";	
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());			
			stmt.execute();
			stmt.close();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public void delete(Object object) {
		User user = (User) object;
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM user 	WHERE id=?;");
			stmt.setInt(1, user.getID());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
