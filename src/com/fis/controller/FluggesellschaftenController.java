/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.FluggesellschaftenDao;
import com.fis.dto.Fluggesellschaften;
import com.fis.impl.FluggesellschaftenDaoImpl;

/**
 * Klasse: FluggesellschaftenController
 * Beschreibung: Abstraktionsschicht des FluggesellschaftenDao.
 */

public class FluggesellschaftenController implements FluggesellschaftenDao {

	@EJB
	private FluggesellschaftenDaoImpl fluggesellschaftenDao;
	
	/** Constructor **/
	public FluggesellschaftenController() { this.fluggesellschaftenDao = new FluggesellschaftenDaoImpl(); }
	
	/** Abstraktionsmethode der FluggesellschaftenDao-Methode 'listAllFlugzeuge'. **/
	public List<Fluggesellschaften> listAllFlugzeuge() { return fluggesellschaftenDao.listAllFlugzeuge(); }
	
}
