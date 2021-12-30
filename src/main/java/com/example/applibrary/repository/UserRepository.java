package com.example.applibrary.repository;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedUsers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	
	static Connection conn;

	public UserRepository() {
		conn = DBConnection.getConnection();
	}

	
	public List<EncapsulatedUsers> findAll() {
		
		List<EncapsulatedUsers> listOfUsers = new ArrayList<EncapsulatedUsers>();
		try {
			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT id,Name,Surname,Login,Ewallet,Password FROM users");
			while (rs.next()) {
				listOfUsers.add(new EncapsulatedUsers(Integer.parseInt(rs.getString("id")), rs.getString("name"),
						rs.getString("surname"), rs.getString("login"), Integer.parseInt(rs.getString("ewallet")),
						rs.getString("password")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfUsers;
	}

	public List<EncapsulatedUsers> findAllNotLibrarian() {

		List<EncapsulatedUsers> listOfUsers = new ArrayList<EncapsulatedUsers>();
		try {
			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT id,Name,Surname,Login,Ewallet,Password FROM users WHERE accType NOT IN (2)");
			while (rs.next()) {
				listOfUsers.add(new EncapsulatedUsers(Integer.parseInt(rs.getString("id")), rs.getString("name"),
						rs.getString("surname"), rs.getString("login"), Integer.parseInt(rs.getString("ewallet")),
						rs.getString("password")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfUsers;
	}

}
