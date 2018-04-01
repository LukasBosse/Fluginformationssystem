/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.FlugDao;
import com.fis.dto.DetailFlug;
import com.fis.dto.Flug;
import com.fis.impl.FlugDaoImpl;

/**
 * Klasse: FlugController
 * Beschreibung: Abstraktionsschicht des FlugDao.
 */

public class FlugController implements FlugDao {

	@EJB
	private FlugDaoImpl flugDao;
	
	/** Constructor **/
	public FlugController() { flugDao = new FlugDaoImpl(); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'listFlüge'. **/
	public List<Flug> listFlüge() { return flugDao.listFlüge(); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'findFlugByFlugNr'. **/
	public Flug findFlugByFlugNr(String flugNr) { return flugDao.findFlugByFlugNr(flugNr); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'create'. **/
	public void create(PrintWriter pw, Flug f) { flugDao.create(pw, f); }

	/** Abstraktionsmethode der FlugDao-Methode 'updateFlug'. **/
	public void updateFlug(PrintWriter pw, String flugNr) { flugDao.updateFlug(pw, flugNr); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'findFlugByIdWithDetails'. **/
	public DetailFlug findFlugByIdWithDetails(String flugNr) { return flugDao.findFlugByIdWithDetails(flugNr); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'listAllFluglinien'. **/
	public List<Object[]> listAllFluglinien() { return flugDao.listAllFluglinien(); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'listFlightsByTime'. **/
	public List<Flug> listFlightsByTime(String ersteZeit, String zweiteZeit) { return flugDao.listFlightsByTime(ersteZeit, zweiteZeit); }
	
	/** Abstratkionsmethode der FlugDao-Method 'dateFormatter'. **/
	public Date dateFormatter(String time) { return flugDao.dateFormatter(time); }

	/** Abstratkionsmethode der FlugDao-Method 'getFlugzeit'. **/
	public double getFlugzeit(PrintWriter pw, Date d1, Date d2) { return flugDao.getFlugzeit(pw, d1, d2); }

	/** Abstratkionsmethode der FlugDao-Method 'timeFormatter'. **/
	public Time timeFormatter(String time) { return flugDao.timeFormatter(time); }
	
	/** Abstratkionsmethode der FlugDao-Method 'calcDifferenz'. **/
	public long calcDifferenz(Date d1, Date d2) { return flugDao.calcDifferenz(d1, d2); }

	/** Abstratkionsmethode der FlugDao-Method 'calcToHour'. **/
	public long calcToHour(long time) { return flugDao.calcToHour(time); }

	/** Abstratkionsmethode der FlugDao-Method 'calcToMin'. **/
	public long calcToMin(long time) { return flugDao.calcToMin(time); }
	
	/** Abstraktionsmethode der FlugDao-Methode 'generateFlug'. */
	public Flug generateFlug(PrintWriter pw, String flugnr, String flugzeug, String km, String landezeit, String startzeit) {
		return flugDao.generateFlug(pw, flugnr, flugzeug, km, landezeit, startzeit);
	}
	
}
