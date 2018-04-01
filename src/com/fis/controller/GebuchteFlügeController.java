/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.GebuchteFl�geDao;
import com.fis.dto.GebuchteFl�ge;
import com.fis.impl.GebuchteFl�geDaoImpl;

/**
 * Klasse: GebuchteFl�geController
 * Beschreibung: Abstraktionsschicht des GebuchteFl�geDao.
 */

public class GebuchteFl�geController implements GebuchteFl�geDao {

	@EJB
	private GebuchteFl�geDaoImpl gebuchteFl�geDao;
	
	/** Constructor **/
	public GebuchteFl�geController() { this.gebuchteFl�geDao = new GebuchteFl�geDaoImpl(); }
	
	/** Abstraktionsmethode der GebuchteFl�geDao-Methode 'listAllFlugh�fen'. **/
	public List<GebuchteFl�ge> listAllFl�ge() { return gebuchteFl�geDao.listAllFl�ge(); }
	
}
