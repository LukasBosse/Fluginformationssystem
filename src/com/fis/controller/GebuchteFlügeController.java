package com.fis.controller;

import java.util.List;

import javax.ejb.EJB;

import com.fis.model.GebuchteFl�ge;
import com.fis.services.GebuchteFl�geDao;

public class GebuchteFl�geController {

	@EJB
	private GebuchteFl�geDao gebuchteFl�geDao;
	
	public GebuchteFl�geController() { this.gebuchteFl�geDao = new GebuchteFl�geDao(); }
	
	public List<GebuchteFl�ge> listAllFlugh�fen() { return gebuchteFl�geDao.listAllFlugh�fen(); }

	
}
