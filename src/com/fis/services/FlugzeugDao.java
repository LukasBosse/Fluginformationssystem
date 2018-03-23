package com.fis.services;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Flugzeuge;

@Stateless
public class FlugzeugDao extends AbstractDao {

	public FlugzeugDao() { super(); }
	
	public List<Flugzeuge> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Flugzeuge.findAll", Flugzeuge.class).getResultList();
	}
	
	public Flugzeuge findFlugzeug(int id) {
		return entityManager.find(Flugzeuge.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Flugzeuge> findFlugzeugeByFluglinie(String fluglinie) {
		query = entityManager.createQuery("SELECT f From Flugzeuge f WHERE f.fluglinie = :fluglinie", Flugzeuge.class);
		query.setParameter("fluglinie", fluglinie);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findFlugzeugWithDetails() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.id, f.hersteller, f.type, f.sitze, f.fluglinie, g.Gesellschaft FROM flugzeuge As `f`, fluggesellschaften As `g` WHERE g.ID = f.fluggesellschaft").getResultList();
		return obj;
	}
	
	public void create(PrintWriter pw, Flugzeuge f) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
	  		writeAlert(pw, "Warnung!", "Das Flugzeug konnte nicht hinzugefügt werden.", "alert-danger", "left");
	  		return;
		}
  		writeAlert(pw, "Erfolg!", "Das Flugzeug wurde erfolgreich hinzugefügt.", "alert-success", "left");
	}
	
	public void updateFluglinie(PrintWriter pw, int id, String fluglinie) {
		try {
			Flugzeuge f = findFlugzeug(id);
			entityManager.getTransaction().begin();
			f.setFluglinie(fluglinie);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
	  		writeAlert(pw, "Warung!", "Das Flugzeug konnte nicht zugewiesen werden.", "alert-danger", "left");
	  		return;
		}
  		writeAlert(pw, "Erfolg!", "Das Flugzeug wurde erfolgreich zugewiesen.", "alert-success", "left");
	}

}
