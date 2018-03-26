package com.fis.services;

import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.fis.model.User;

@Stateless
public class UserDao extends AbstractDao {
			
	public UserDao() { super(); }
	
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
