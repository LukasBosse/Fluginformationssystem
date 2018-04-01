/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;

import javax.ejb.EJB;

import com.fis.dao.UserDao;
import com.fis.dto.User;
import com.fis.impl.UserDaoImpl;

/**
 * Klasse: UserController
 * Beschreibung: Abstraktionsschicht des UserDao.
 */

public class UserController implements UserDao {
	
	@EJB
	private UserDaoImpl userDao;
	
	/** Constructor **/
	public UserController() { userDao = new UserDaoImpl(); }
	
	/** Abstraktionsmethode der UserDao-Methode 'findUser'. **/
	public User findUser(String username) { return userDao.findUser(username); }
	
	/** Abstraktionsmethode der UserDao-Methode 'findUser'. **/
	public void create(PrintWriter pw, User user) { userDao.create(pw, user); }
	
	/** Abstraktionmethode der UserDao-Methode 'writerAlert'. **/
	public void writeAlert(PrintWriter pw, String title, String message, String type, String position) { userDao.writeAlert(pw, title, message, type, position); } 

}
