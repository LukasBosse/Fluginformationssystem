/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.BuchungsDao;
import com.fis.dto.Buchung;
import com.fis.dto.Passagier;
import com.fis.impl.BuchungsDaoImpl;

/**
 * Klasse: BuchungsController
 * Beschreibung: Abstraktionsschicht des BuchungsDao.
 */

public class BuchungsController implements BuchungsDao {

	@EJB
	private BuchungsDaoImpl buchungsDao;
	
	/** Constructor **/
	public BuchungsController() { buchungsDao = new BuchungsDaoImpl(); }
	
	/** Abstraktionsmethode der BuchungsDao-Methode 'findUnbestätigteBuchung'. **/
	public List<Buchung> findUnbestätigteBuchung() { return buchungsDao.findUnbestätigteBuchung(); }
	
	/** Abstraktionsmethode der BuchungsDao-Methode 'findBuchungById'. **/
	public List<Buchung> findBuchungById(String flugNr) { return buchungsDao.findBuchungById(flugNr); }
	
	/** Abstraktionsmethode der BuchungsDao-Methode 'findBuchungByName'. **/
	public List<Buchung> findBuchungByName(String name) { return buchungsDao.findBuchungByName(name); }
	
	/** Abstratkionsmethode der BuchungsDao-Methode 'addBuchung'. **/
	public void addBuchung(PrintWriter pw, Passagier p, String flugNr) { buchungsDao.addBuchung(pw, p, flugNr); }
	
	/** Abstraktionsmethode der BuchungsDao-Methode 'updateBuchung'. **/
	public void updateBuchung(PrintWriter pw, int id) { buchungsDao.updateBuchung(pw, id); }
	
}
