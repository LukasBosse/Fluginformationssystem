/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.Flugh�fen;

@Remote
public interface FlughafenDao {

	public List<Flugh�fen>listAllFlugh�fen();
	
	public void createFlughafen(PrintWriter pw, String bezeichnung);
	
}
