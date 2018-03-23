package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Fluggesellschaften;
import com.fis.services.FluggesellschaftenDao;

public class FluggesellschaftenController {

	@EJB
	private FluggesellschaftenDao fluggesellschaftenDao;
	
	public FluggesellschaftenController() { this.fluggesellschaftenDao = new FluggesellschaftenDao(); }
	
	public List<Fluggesellschaften> listAllFlugzeuge() { return fluggesellschaftenDao.listAllFlugzeuge(); }
	
}
