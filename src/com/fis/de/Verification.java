package com.fis.de;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.fis.model.User;
import com.fis.services.UserDAO;

public class Verification {
	
	private HTMLWriter htmlWriter;	
	private UserDAO userDao = new UserDAO();
	
	public Verification(HTMLWriter htmlWriter) { this.htmlWriter = htmlWriter; }
	
	public void register(String username, String userType, String password) {
		User user = new User(username, toMD5(password), userType);
		userDao.createUser(user);
		if(user.getId() != 0) {
			htmlWriter.writeAlert("Erfolg!", "Sie wurden erfolgreich registriert.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Sie konnten leider nicht registriert werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");			
		}
	}
	
	public void login(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		User user = userDao.findUser(username);
		if(user.getPasswort().equals(toMD5(password))) {
			session.setAttribute("user", user);
			try {
				response.sendRedirect(user.getType() + ".jsp");
			} catch (IOException e) {
				e.printStackTrace();
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
		return "";
	}
	
}
