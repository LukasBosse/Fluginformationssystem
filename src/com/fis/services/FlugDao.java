package com.fis.services;

import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fis.model.Flug;

public class FlugDao extends AbstractDao {

	public FlugDao(Writer oS) { super(oS); }
	
	public List<Flug> listFlüge() {
		return entityManager.createQuery("SELECT f From Flug f", Flug.class).getResultList();
	}
	
	public Flug findFlugByFlugNr(String flugNr) {
		return entityManager.find(Flug.class, flugNr);
	}
	
	public void create(Flug f) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
			htmlWriter.writeAlert("Warnung!", "Ihr Flug konnte nicht hinzugefügt werden.", "alert-danger", "right");	
			return;
		}
		htmlWriter.writeAlert("Erfolg!", "Ihr Flug wurde erfolgreich hinzugefügt.", "alert-success", "right");	
	}
	
	public Flug generateFlug(String flugnr, String flugzeug, String km, String landezeit, String start, String startzeit,String ziel) {
		Flug flug = new Flug();
		flug.setFlugnr(flugnr);
		flug.setFlugzeug(Integer.parseInt(flugzeug));
		flug.setKm(Integer.parseInt(km));
		flug.setStart(Integer.parseInt(start));
		flug.setZiel(Integer.parseInt(ziel));
		flug.setStartzeit(timeFormatter(startzeit));
		flug.setLandezeit(timeFormatter(landezeit));
		flug.setFlugzeit(BigDecimal.valueOf(getFlugzeit(flug.getStartzeit(), flug.getLandezeit())));
		return flug;
	}

	public void updateFlug(String flugNr) {
		try {
			Flug flug = entityManager.find(Flug.class, flugNr);
			entityManager.getTransaction().begin();
			flug.setInklusiveMahlzeit(!flug.getInklusiveMahlzeit());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			htmlWriter.writeAlert("Warnung!", "Dem Flug konnte keine Mahlzeit hinzugefügt werden.", "alert-danger", "left");
			return;
		}
		htmlWriter.writeAlert("Erfolg!", "Dem Flug wurde eine Mahlzeit hinzugefügt.", "alert-success", "left");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listAllFluglinien() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.flugnr, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel` FROM `flug` As `f` INNER JOIN flughäfen AS `flS` ON flS.ID = f.start INNER JOIN flughäfen As `flD` ON flD.ID = f.ziel").getResultList();
		return obj;
	}

	
	public Date dateFormatter(String time) {
		DateFormat format = new SimpleDateFormat("dd-mm-YYYY");
		try {
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public double getFlugzeit(Date d1, Date d2) {
		long diff = calcDifferenz(d1,d2);
		return Double.parseDouble(calcToHour(diff) + "." + (calcToMin(diff)));
	}
	
	public Time timeFormatter(String time) {
		DateFormat format = new SimpleDateFormat("HH:mm");
		try {
			return new Time(format.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long calcDifferenz(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime());
	}
	
	public long calcToHour(long time) {
		return time / (60 * 60 * 1000) % 24;
	}
	
	public long calcToMin(long time) {
		return time / (60 * 1000) % 60;
	}
	
}
