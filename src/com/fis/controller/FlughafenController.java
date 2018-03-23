package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Flughäfen;
import com.fis.services.FlughafenDao;

public class FlughafenController {
	
	@EJB
	private FlughafenDao flughafenDao;
	
	public FlughafenController() { this.flughafenDao = new FlughafenDao(); }

	public List<Flughäfen>listAllFlughäfen() { return flughafenDao.listAllFlughäfen(); }	

	public void createFlughafen(PrintWriter pw, String bezeichnung) { flughafenDao.createFlughafen(pw, bezeichnung); }
	
}
