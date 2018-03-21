package com.fis.services;

import java.util.List;

import com.fis.model.Flughäfen;

public class FlughafenDao extends AbstractDao {

	public FlughafenDao() { super(); }
	
	public List<Flughäfen>listAllFlughäfen() {
		return entityManager.createNamedQuery("Flughäfen.findAll", Flughäfen.class).getResultList();
	}
	
}
