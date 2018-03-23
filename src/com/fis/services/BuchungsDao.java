package com.fis.services;


import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Buchung;

@Stateless
public class BuchungsDao extends AbstractDao {

	public BuchungsDao() { super(); }
	
	public List<Buchung> findUnbestätigteBuchung() {
		return entityManager.createQuery("Select b from Buchung b where b.bestaetigt = 0", Buchung.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Buchung> findBuchungById(String flugNr) {
		query = entityManager.createQuery("SELECT b FROM Buchung b WHERE b.flugnr = :id", Buchung.class);
		query.setParameter("id", flugNr);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Buchung> findBuchungByName(String name) {
		query = entityManager.createQuery("SELECT b FROM Buchung b WHERE b.name = :name", Buchung.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	public void updateBuchung(PrintWriter pw, int id) {
		try {
			Buchung b = entityManager.find(Buchung.class, id);
			entityManager.getTransaction().begin();
			b.setBestaetigt(!b.getBestaetigt());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Die Buchung konnte nicht bestätigt werden.", "alert-danger", "right");
			return;
		}
		writeAlert(pw, "Erfolg!", "Die Buchung wurde bestätigt.", "alert-success", "right");
	}

}
