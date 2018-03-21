package com.fis.services;

import java.util.List;

import com.fis.model.Flugh�fen;

public class FlughafenDao extends AbstractDao {

	public FlughafenDao() { super(); }
	
	public List<Flugh�fen>listAllFlugh�fen() {
		return entityManager.createNamedQuery("Flugh�fen.findAll", Flugh�fen.class).getResultList();
	}
	
}
