package com.fis.services;

import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Fluggesellschaften;
@Stateless
public class FluggesellschaftenDao extends AbstractDao {
	
	public FluggesellschaftenDao() { super(); }
	
	public List<Fluggesellschaften> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Fluggesellschaften.findAll", Fluggesellschaften.class).getResultList();
	}
		
}
