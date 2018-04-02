/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.PassagierDao;
import com.fis.dto.Passagier;
import com.fis.impl.PassagierDaoImpl;

/**
 * Klasse: PassagierController
 * Beschreibung: Abstraktionsschicht des PassagierDao.
 */

public class PassagierController implements PassagierDao {

	@EJB
	private PassagierDaoImpl passagierDao;
	
	/** Constructor **/
	public PassagierController() { passagierDao = new PassagierDaoImpl(); }

	/** Abstraktionsmethode der PassagierDao-Methode 'listPassagier'. **/
	public List<Passagier> listPassagier() { return passagierDao.listPassagier(); }
	
	/** Abstraktionsmethode der PassagierDao-Methode 'findPassagier'. **/
	public Passagier findPassagier(String name) { return passagierDao.findPassagier(name); }
	
	/** Abstraktionsmethode der PassagierDao-Methode 'createPassagier'. **/
	public void createPassagier(PrintWriter pw, String name, String ort, String geburtsdatum, boolean geschlecht) {
		passagierDao.createPassagier(pw, name, ort, geburtsdatum, geschlecht);
	}
	
}
