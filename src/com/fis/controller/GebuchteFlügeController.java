/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.GebuchteFlügeDao;
import com.fis.dto.GebuchteFlüge;
import com.fis.impl.GebuchteFlügeDaoImpl;

/**
 * Klasse: GebuchteFlügeController
 * Beschreibung: Abstraktionsschicht des GebuchteFlügeDao.
 */

public class GebuchteFlügeController implements GebuchteFlügeDao {

	@EJB
	private GebuchteFlügeDaoImpl gebuchteFlügeDao;
	
	/** Constructor **/
	public GebuchteFlügeController() { this.gebuchteFlügeDao = new GebuchteFlügeDaoImpl(); }
	
	/** Abstraktionsmethode der GebuchteFlügeDao-Methode 'listAllFlughäfen'. **/
	public List<GebuchteFlüge> listAllFlüge() { return gebuchteFlügeDao.listAllFlüge(); }
	
}
