package com.fis.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import com.fis.model.Passagier;
import com.fis.services.PassagierDao;

public class PassagierController {

	@EJB
	private PassagierDao passagierDao;
	
	public PassagierController() { passagierDao = new PassagierDao(); }
	
	public List<Passagier> listPassagier() { return passagierDao.listPassagier(); }
	
	public void createPassagier(PrintWriter pw, String name, String ort, String geburtsdatum, boolean geschlecht) {
		passagierDao.createPassagier(pw, name, ort, geburtsdatum, geschlecht);
	}
	
}
