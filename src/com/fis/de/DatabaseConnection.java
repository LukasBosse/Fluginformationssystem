package com.fis.de;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.EJB;

public class DatabaseConnection {

	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String HOST = "localhost";
	private final String PORT = "3306";
	private final String DATABASE = "flugbuchung";
	private final String USER = "root";
	private final String PASSWORT = "";
	
	private Connection cn = null;
	private PreparedStatement  st = null;
	private ResultSet  rs = null;
	
	public DatabaseConnection() {}
	
	public void connect() {
		try {
			Class.forName(DRIVER).newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", USER, PASSWORT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean execute(String query, String[] param) {
		if(cn == null) { return false; }
		try {
			st = cn.prepareStatement(query);
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					st.setString(i + 1, param[i]);
				}
			}
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return false;
	}
	
	public ResultSet executeQuery(String query, String[] param) {
		if(cn == null) { return null; }
		try {
			st = cn.prepareStatement(query);
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					st.setString(i+1, param[i]);
				}
			}
			rs = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void disconnect() {
		if(cn == null) return;
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
