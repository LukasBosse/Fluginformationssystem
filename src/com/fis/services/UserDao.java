package com.fis.services;

import java.io.Writer;

import javax.annotation.ManagedBean;

import com.fis.model.User;

@ManagedBean
public class UserDao extends AbstractDao {
				
	public UserDao(Writer oS) { super(oS); }
	
	public User findUser(String username) {
	    query = entityManager.createQuery("Select u from User u where u.username = :username");
	    query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
	
	public void create(User u) {
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();
		verify(u);
	}
	
	private void verify(User u) {
		if(u.getId() != 0) {
			htmlWriter.writeAlert("Erfolg!", "Sie wurden erfolgreich registriert.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Sie konnten leider nicht registriert werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");			
		}
	}
	
}
