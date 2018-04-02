/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.Flugzeuge;

@Remote
public interface FlugzeugDao {

	public List<Flugzeuge> listAllFlugzeuge();
	
	public Flugzeuge findFlugzeug(int id);
	
	public List<Flugzeuge> findFlugzeugeByFluglinie(String fluglinie);
	
	public List<Object[]> findFlugzeugWithDetails();
	
	public void create(PrintWriter pw, Flugzeuge f);
	
	public void updateFluglinie(PrintWriter pw, int id, String fluglinie);
	
}
