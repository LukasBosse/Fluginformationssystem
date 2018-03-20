package com.fis.de;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirection {
	
	public void checkDirection(HttpSession session, HttpServletResponse response, String userType) {
		checkUser(session, response, userType);
	}
	
	public void checkUser(HttpSession session, HttpServletResponse response,String userType) {
		if(session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if(!user.getUserType().equals(userType)) {
				redirectTo(response, "Main");
			}
		} else {
			redirectTo(response, "Main");
		}
	}
	
	private void redirectTo(HttpServletResponse response, String target) {
		if(!target.isEmpty()) {
			try {
				response.sendRedirect(target + ".jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
