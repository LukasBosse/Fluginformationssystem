package com.fis.de;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.fis.controller.UserController;
import com.fis.model.User;

public class Verification {
	
	private UserController userController;
	private PrintWriter pw;
	
	public Verification(PrintWriter pw) { 
		userController = new UserController();
		this.pw = pw;
	}
	
	public void register(PrintWriter pw, String username, String userType, String password) {
		User user = new User(username, toMD5(password), userType);
		userController.create(pw, user);
	}
	
	public void login(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		User user = userController.findUser(username);
		if(user != null && user.getPasswort().equals(toMD5(password))) {
			session.setAttribute("user", user);
			try {
				response.sendRedirect(user.getType() + ".jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		userController.getWriter().writeAlert(pw, "Warnung!", "Ihre Anmeldung ist leider fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
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
