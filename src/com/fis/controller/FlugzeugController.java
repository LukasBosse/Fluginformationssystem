/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.FlugzeugDao;
import com.fis.dto.Flugzeuge;
import com.fis.impl.FlugzeugDaoImpl;

/**
 * Klasse: FlugzeugController
 * Beschreibung: Abstraktionsschicht des FlugzeugDao.
 */

public class FlugzeugController implements FlugzeugDao {

	@EJB
	private FlugzeugDaoImpl flugzeugDao;
	
	/** Constructor **/
	public FlugzeugController() { flugzeugDao = new FlugzeugDaoImpl(); }
	
	/** Abstraktionsmethode der FlugzeugDao-Methode 'listAllFlugzeuge'. **/
	public List<Flugzeuge> listAllFlugzeuge() { return flugzeugDao.listAllFlugzeuge(); }
	
	/** Abstraktionsmethode der FlugzeugDao-Methode 'findFlugzeug'. **/
	public Flugzeuge findFlugzeug(int id) { return flugzeugDao.findFlugzeug(id); }
	
	/** Abstraktionsmethode der FlugzeugDao-Methode 'findFlugzeugeByFluglinie'. **/
	public List<Flugzeuge> findFlugzeugeByFluglinie(String fluglinie) { return flugzeugDao.findFlugzeugeByFluglinie(fluglinie); }
	
	/** Abstraktionsmethode der FlugzeugDao-Methode 'findFlugzeugWithDetails'. **/
	public List<Object[]> findFlugzeugWithDetails() { return flugzeugDao.findFlugzeugWithDetails(); }
	
	/** Abstraktionsmethode der FlugzeugDao-Methode 'create'. **/
	public void create(PrintWriter pw, Flugzeuge f) { flugzeugDao.create(pw, f); }

	/** Abstraktionsmethode der FlugzeugDao-Methode 'updateFluglinie'. **/
	public void updateFluglinie(PrintWriter pw, int id, String fluglinie) { flugzeugDao.updateFluglinie(pw, id, fluglinie); }
	
}
