package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Flugzeuge;
import com.fis.services.FlugzeugDao;

public class FlugzeugController {

	@EJB
	private FlugzeugDao flugzeugDao;
	
	public FlugzeugController() { flugzeugDao = new FlugzeugDao(); }
	
	public List<Flugzeuge> listAllFlugzeuge() { return flugzeugDao.listAllFlugzeuge(); }
	
	public Flugzeuge findFlugzeug(int id) { return flugzeugDao.findFlugzeug(id); }
	
	public List<Flugzeuge> findFlugzeugeByFluglinie(String fluglinie) { return flugzeugDao.findFlugzeugeByFluglinie(fluglinie); }
	
	public List<Object[]> findFlugzeugWithDetails() { return flugzeugDao.findFlugzeugWithDetails(); }
	
	public void create(PrintWriter pw, Flugzeuge f) { flugzeugDao.create(pw, f); }
	
	public void updateFluglinie(PrintWriter pw, int id, String fluglinie) { flugzeugDao.updateFluglinie(pw, id, fluglinie); }
	
}
