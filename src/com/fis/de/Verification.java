package com.fis.de;

import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.fis.model.User;
import com.fis.services.UserDao;

@ManagedBean
public class Verification {
	
	private UserDao userDao;
	
	public Verification(Writer writer) { 
		userDao = new UserDao(writer);
	}
	
	public void register(String username, String userType, String password) {
		User user = new User(username, toMD5(password), userType);
		userDao.create(user);
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
		userDao.getWriter().writeAlert("Warnung!", "Ihre Anmeldung ist leider fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
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
