/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;

import javax.ejb.Remote;

import com.fis.dto.User;

@Remote
public interface UserDao {

	public User findUser(String username);
	
	public void create(PrintWriter pw, User u);

	public void writeAlert(PrintWriter pw, String title, String message, String type, String position);
	
}
