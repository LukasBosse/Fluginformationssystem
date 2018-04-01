/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.fis.dao.UserDao;
import com.fis.dto.User;

/**
 * Klasse: UserDao
 * Beschreibung: Dao zum Zugriff auf die 'User'-Datenbanktabelle.
 */

@Stateless
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {
			
	/** Constructor **/
	public UserDaoImpl() { super(); }
	
	/**
	 * Ermittelt einen Benutzer anhand seines Benutzernamens.
	 * @param username
	 * @return
	 */
	public User findUser(String username) {
	    try {
			query = entityManager.createQuery("Select u from User u where u.username = :username");
		    query.setParameter("username", username);
			return (User) query.getSingleResult();
	    } catch(NoResultException e) {
	        return null;
	    } catch (NonUniqueResultException nure) {
	    	return null;
	    } 
	}
	
	/**
	 * Speichert einen neuen Benutzer.
	 * @param pw
	 * @param u
	 */
	public void create(PrintWriter pw, User u) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(u);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Ihre Registrierung ist fehlgeschlagen. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
			return;
		} 
		writeAlert(pw, "Erfolg!", "Sie wurden erfolgreich registriert.", "alert-success", "left");
	}
			
}
