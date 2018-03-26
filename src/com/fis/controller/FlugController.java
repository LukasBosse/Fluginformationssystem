package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.DetailFlug;
import com.fis.model.Flug;
import com.fis.services.FlugDao;

public class FlugController {

	@EJB
	private FlugDao flugDao;
	
	public FlugController() { flugDao = new FlugDao(); }
	
	public List<Flug> listFlüge() { return flugDao.listFlüge(); }
	
	public Flug findFlugByFlugNr(String flugNr) { return flugDao.findFlugByFlugNr(flugNr); }
	
	public void create(PrintWriter pw, Flug f) { flugDao.create(pw, f); }

	public void updateFlug(PrintWriter pw, String flugNr) { flugDao.updateFlug(pw, flugNr); }
	
	public DetailFlug findFlugByIdWithDetails(String flugNr) { return flugDao.findFlugByIdWithDetails(flugNr); }
	
	public List<Object[]> listAllFluglinien() { return flugDao.listAllFluglinien(); }
	
	public List<Flug> listFlightsByTime(String ersteZeit, String zweiteZeit) { return flugDao.listFlightsByTime(ersteZeit, zweiteZeit); }
	
	public Flug generateFlug(PrintWriter pw, String flugnr, String flugzeug, String km, String landezeit, String startzeit) {
		return flugDao.generateFlug(pw, flugnr, flugzeug, km, landezeit, startzeit);
	}

}
