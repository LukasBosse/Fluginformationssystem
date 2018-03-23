package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Flugh�fen;
import com.fis.services.FlughafenDao;

public class FlughafenController {
	
	@EJB
	private FlughafenDao flughafenDao;
	
	public FlughafenController() { this.flughafenDao = new FlughafenDao(); }

	public List<Flugh�fen>listAllFlugh�fen() { return flughafenDao.listAllFlugh�fen(); }	

	public void createFlughafen(PrintWriter pw, String bezeichnung) { flughafenDao.createFlughafen(pw, bezeichnung); }
	
}
