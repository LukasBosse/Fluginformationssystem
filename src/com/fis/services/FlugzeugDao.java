package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Flugzeuge;

@ManagedBean
public class FlugzeugDao extends AbstractDao {

	public FlugzeugDao(Writer oS) { super(oS); }
	
	public List<Flugzeuge> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Flugzeuge.findAll", Flugzeuge.class).getResultList();
	}
	
	public Flugzeuge findFlugzeug(int id) {
		return entityManager.find(Flugzeuge.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findFlugzeugWithDetails() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.id, f.hersteller, f.type, f.sitze, f.fluglinie, g.Gesellschaft FROM flugzeuge As `f`, fluglinien As `g` WHERE g.ID = f.fluggesellschaft").getResultList();
		return obj;
	}
	
	public void create(Flugzeuge f) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
	  		htmlWriter.writeAlert("Warnung!", "Das Flugzeug konnte nicht hinzugefügt werden.", "alert-danger", "left");
	  		return;
		}
  		htmlWriter.writeAlert("Erfolg!", "Das Flugzeug wurde erfolgreich hinzugefügt.", "alert-success", "left");
	}
	
	public void updateFluglinie(int id, String fluglinie) {
		try {
			Flugzeuge f = findFlugzeug(id);
			entityManager.getTransaction().begin();
			f.setFluglinie(fluglinie);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
	  		htmlWriter.writeAlert("Warung!", "Das Flugzeug konnte nicht zugewiesen werden.", "alert-danger", "left");
	  		return;
		}
  		htmlWriter.writeAlert("Erfolg!", "Das Flugzeug wurde erfolgreich zugewiesen.", "alert-success", "left");
	}

}
