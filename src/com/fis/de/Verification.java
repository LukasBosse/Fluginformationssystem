/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

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
import com.fis.dto.User;

/**
 * Klasse: Verification
 * Beschreibung: Verifizierung des Nutzers mit Nutzerdaten aus der Datenbank.
 */

@ManagedBean(name = "verification", eager = true)
@SessionScoped
public class Verification {
	
	private UserController userController;
	
	private String username;

	private String password;
	
	/** Constructor **/
	public Verification()
	{
		userController = new UserController();
	}
	
	/**
	 * Registrierung eines neuen Nutzers.
	 * @param pw
	 * @param username
	 * @param userType
	 * @param password
	 */
	public void register(PrintWriter pw, String username, String userType, String password) {
		User user = new User(username, toMD5(password), userType);
		userController.create(pw, user);
	}
	
	/**
	 * Anmeldefunktion des Nutzers und Weiterleitung zu einer ihm zugeteilten Seite.
	 * @return
	 */
	public String doLogin() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		HttpSession session = request.getSession();
		
		User user = userController.findUser(username);
		if(user != null && user.getPasswort().equals(toMD5(password))) {
			session.setAttribute("user", user);
			return user.getType() + ".jsp";
		} else {	
			try {
				userController.writeAlert(response.getWriter(), "Warnung!", "Ihre Anmeldung ist leider fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
		
	}
	
	/**
	 * MD5 Verschlüsselung eines Passwortes.
	 * @param pass
	 * @return
	 */
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
	
	/**
	 * Getter-Methode Username.
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter-Methode Username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter-Methode Passwort.
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter-Methode Passwort.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
