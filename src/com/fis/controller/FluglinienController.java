package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.services.FluglinienDao;

public class FluglinienController {

	@EJB
	private FluglinienDao fluglinienDao;
	
	public FluglinienController() { this.fluglinienDao = new FluglinienDao(); }
	
	public void createRelation(PrintWriter pw, String fluglinie, int startOrt, int zielOrt) { fluglinienDao.createRelation(pw, fluglinie, startOrt, zielOrt); }
	
	public List<Object[]> listRelationen() { return fluglinienDao.listRelationen(); }
	
}
