package com.fis.de;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.fis.controller.UserController;
import com.fis.model.User;

@ManagedBean(name = "verification", eager = true)
@SessionScoped
public class Verification {
	
	private UserController userController;
	private PrintWriter pw;
	
	private String username;

	private String password;
	
	public Verification()
	{
		userController = new UserController();
	}
	
	public Verification(PrintWriter pw) { 
		userController = new UserController();
		this.pw = pw;
	}
	
	public void register(PrintWriter pw, String username, String userType, String password) {
		User user = new User(username, toMD5(password), userType);
		userController.create(pw, user);
	}
	
	public String doLogin() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		HttpSession session = request.getSession();
		
		User user = userController.findUser(username);
		if(user != null && user.getPasswort().equals(toMD5(password))) {
			session.setAttribute("user", user);
			return user.getType() + ".jsp";
		}
		else
		{	
		
		try {
			userController.getWriter().writeAlert(response.getWriter(), "Warnung!", "Ihre Anmeldung ist leider fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
		}
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
