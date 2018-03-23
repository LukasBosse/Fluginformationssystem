package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.model.GebuchteFlüge;
import com.fis.services.GebuchteFlügeDao;

public class GebuchteFlügeController {

	@EJB
	private GebuchteFlügeDao gebuchteFlügeDao;
	
	public GebuchteFlügeController() { this.gebuchteFlügeDao = new GebuchteFlügeDao(); }
	
	public List<GebuchteFlüge> listAllFlughäfen() { return gebuchteFlügeDao.listAllFlughäfen(); }

	
}
