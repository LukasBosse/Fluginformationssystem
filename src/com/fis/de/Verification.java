package com.fis.de;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

public class Verification {
	
	private DatabaseConnection dbC;
	private HTMLWriter htmlWriter;

	public Verification(HTMLWriter htmlWriter) {
		this.htmlWriter = htmlWriter;
		//initDatabaseConnection();
		dbC = new DatabaseConnection();
	}
	
	public void register(String username, String userType, String password) {
		dbC.connect();
		String encryptedPassword = toMD5(password);
		String[] param = { username, encryptedPassword, userType};
		System.err.println(param);
		if(dbC.execute("INSERT INTO users (username, passwort, type) VALUES (?,?,?)", param)) {
			htmlWriter.writeAlert("Erfolg!", "Sie wurden erfolgreich registriert.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Sie konnten leider nicht registriert werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");			
		}
		dbC.disconnect();
	}
	
	public void login(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		dbC.connect();
		String encryptedPassword = toMD5(password);	
		ResultSet rs = dbC.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND passwort = '" + encryptedPassword + "' LIMIT 1", null);
		if(rs != null) {
			try {
				while(rs.next()) {
					User user = new User(rs.getInt("ID"), username, rs.getString("type"));
					session.setAttribute("user", user);
					response.sendRedirect(user.getUserType() + ".jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				dbC.disconnect();	
			}
		} 
		htmlWriter.writeAlert("Warnung!", "Ihre Anmeldung ist leider fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
	}

	public static String toMD5(String pass) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		    return myHash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "N/A";
	}
	
}
