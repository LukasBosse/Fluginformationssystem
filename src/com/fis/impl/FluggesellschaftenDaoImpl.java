/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.FluggesellschaftenDao;
import com.fis.dto.Fluggesellschaften;

/**
 * Klasse: FluggesellschaftenDaoImpl
 * Beschreibung: Dao zum Zugriff auf die 'Fluggesellschaften'-Datenbanktabelle.
 */

@Stateless
public class FluggesellschaftenDaoImpl extends AbstractDaoImpl implements FluggesellschaftenDao {
	
	/** Constructor **/
	public FluggesellschaftenDaoImpl() { super(); }
	
	/**
	 * Listest sämtliche hinterlegten Fluggesellschaften auf.
	 * @return List<Fluggesellschaften>
	 */
	public List<Fluggesellschaften> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Fluggesellschaften.findAll", Fluggesellschaften.class).getResultList();
	}
		
}
