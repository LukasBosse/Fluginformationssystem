/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.de;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fis.dto.User;

/**
 * Klasse: Redirection
 * Beschreibung: Umleitung des Nutzers bei unberechtigten Seitenzugriffe.
 */

@Stateless
public class Redirection {
	
	public void checkDirection(HttpSession session, HttpServletResponse response, String userType) {
		checkUser(session, response, userType);
	}
	
	/**
	 * Abgleich der aktuellen Seite mit den Berechtigungen des Nutzers.
	 * @param session
	 * @param response
	 * @param userType
	 */
	public void checkUser(HttpSession session, HttpServletResponse response,String userType) {
		if(session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if(!user.getType().equals(userType)) {
				redirectTo(response, user.getType());
			}
		} else {
			redirectTo(response, "Index");
		}
	}
	
	/**
	 * Weiterleitung des Nutzer zur ihm erlaubten Seite.
	 * @param response
	 * @param target
	 */
	private void redirectTo(HttpServletResponse response, String target) {
		if(!target.isEmpty()) {
			try {
				if(target.equals("Index")) {
					response.sendRedirect(target + ".faces");
				} else {
					response.sendRedirect(target + ".jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
