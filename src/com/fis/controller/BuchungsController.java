package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Buchung;
import com.fis.services.BuchungsDao;

public class BuchungsController {

	@EJB
	private BuchungsDao buchungsDao;
	
	public BuchungsController() { buchungsDao = new BuchungsDao(); }
	
	public List<Buchung> findUnbestätigteBuchung() { return buchungsDao.findUnbestätigteBuchung(); }
	
	public List<Buchung> findBuchungById(String flugNr) { return buchungsDao.findBuchungById(flugNr); }
	
	public List<Buchung> findBuchungByName(String name) { return buchungsDao.findBuchungByName(name); }
	
	public void updateBuchung(PrintWriter pw, int id) { buchungsDao.updateBuchung(pw, id); }
	
}
