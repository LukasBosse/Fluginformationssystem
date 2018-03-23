package com.fis.controller;

import java.io.PrintWriter;

import javax.ejb.EJB;

import com.fis.model.User;
import com.fis.services.UserDao;

public class UserController {
	
	@EJB
	private UserDao userDao;
	
	public UserController() {
		userDao = new UserDao();
	}
	
	public User findUser(String username) {
		return userDao.findUser(username);
	}
	
	public void create(PrintWriter pw, User user) {
		userDao.create(pw, user);
	}
	
	public UserDao getWriter() { return userDao; } 
	

}
