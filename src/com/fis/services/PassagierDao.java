package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Passagier;

@ManagedBean
public class PassagierDao extends AbstractDao {

	public PassagierDao(Writer oS) { super(oS); }
	
	public List<Passagier> listPassagier() {
		return entityManager.createQuery("SELECT p FROM Passagier p", Passagier.class).getResultList();
	}
	
	public void createPassagier(String name, String ort, String geburtsdatum, boolean geschlecht) {
		try {
			Passagier p = new Passagier(name,ort,geburtsdatum,geschlecht);
			entityManager.getTransaction().begin();
			entityManager.persist(p);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			htmlWriter.writeAlert("Warung!", "Der Passagier konnte nicht hinzugefügt werden.", "alert-danger", "right");
			return;
		}
		htmlWriter.writeAlert("Erfolg!", "Der Passagier wurde erfolgreich hinzugefügt.", "alert-success", "right");
	}

}
