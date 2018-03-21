package com.fis.services;

import java.util.List;

import com.fis.model.Flugzeuge;

public class FlugzeugDao extends AbstractDao {

	public FlugzeugDao() { super(); }
	
	public List<Flugzeuge> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Flugzeuge.findAll", Flugzeuge.class).getResultList();
	}
	
}
