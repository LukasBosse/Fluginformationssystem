/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.dao.FluglinienDao;
import com.fis.impl.FluglinienDaoImpl;

/**
 * Klasse: FluglinienController
 * Beschreibung: Abstraktionsschicht des FluglinienDao.
 */

public class FluglinienController implements FluglinienDao {

	@EJB
	private FluglinienDaoImpl fluglinienDao;
	
	/** Constructor **/
	public FluglinienController() { this.fluglinienDao = new FluglinienDaoImpl(); }
	
	/** Abstraktionsmethode der FluglinienDao-Methode 'listRelationen'. **/
	public List<Object[]> listRelationen() { return fluglinienDao.listRelationen(); }
	
	/** Abstraktionsmethode der FluglinienDao-Methode 'createRelation'. **/
	public void createRelation(PrintWriter pw, String fluglinie, int startOrt, int zielOrt) { fluglinienDao.createRelation(pw, fluglinie, startOrt, zielOrt); }

}
