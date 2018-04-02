/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;

import javax.ejb.Remote;

@Remote
public interface AbstractDao {

	public void initalizeDatabaseConnection();
	
	public void writeAlert(PrintWriter pw, String title, String message, String type, String position);
	
	public void closeConnection();
	
}
