/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.FlughafenDao;
import com.fis.dto.Flughäfen;
import com.fis.impl.FlughafenDaoImpl;

/**
 * Klasse: FlughafenController
 * Beschreibung: Abstraktionsschicht des FlughafenDao.
 */

public class FlughafenController implements FlughafenDao {
	
	@EJB
	private FlughafenDaoImpl flughafenDao;
	
	/** Constructor **/
	public FlughafenController() { this.flughafenDao = new FlughafenDaoImpl(); }

	/** Abstraktionsmethode der FlughafenDao-Methode 'listAllFlughäfen'. **/
	public List<Flughäfen>listAllFlughäfen() { return flughafenDao.listAllFlughäfen(); }	

	/** Abstraktionsmethode der FlughafenDao-Methode 'createFlughafen'. **/
	public void createFlughafen(PrintWriter pw, String bezeichnung) { flughafenDao.createFlughafen(pw, bezeichnung); }
	
}
