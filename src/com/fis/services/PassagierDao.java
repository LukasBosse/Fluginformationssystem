package com.fis.services;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Passagier;

@Stateless
public class PassagierDao extends AbstractDao {

	public PassagierDao() { super(); }
	
	public List<Passagier> listPassagier() {
		return entityManager.createQuery("SELECT p FROM Passagier p", Passagier.class).getResultList();
	}
	
	public void createPassagier(PrintWriter pw, String name, String ort, String geburtsdatum, boolean geschlecht) {
		try {
			Passagier p = new Passagier(name,ort,geburtsdatum,geschlecht);
			entityManager.getTransaction().begin();
			entityManager.persist(p);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warung!", "Der Passagier konnte nicht hinzugefügt werden.", "alert-danger", "right");
			return;
		}
		writeAlert(pw, "Erfolg!", "Der Passagier wurde erfolgreich hinzugefügt.", "alert-success", "right");
	}

}
