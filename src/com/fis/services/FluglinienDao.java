package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Fluglinien;

@ManagedBean
public class FluglinienDao extends AbstractDao {
	
	public FluglinienDao(Writer oS) { super(oS); }
	
	public List<Fluglinien> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Fluglinien.findAll", Fluglinien.class).getResultList();
	}
		
}
