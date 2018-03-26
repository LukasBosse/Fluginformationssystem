package com.fis.services;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.DetailFlug;
import com.fis.model.Flug;

@Stateless
public class FlugDao extends AbstractDao {

	public FlugDao() { super(); }
	
	public List<Flug> listFlüge() {
		return entityManager.createQuery("SELECT f From Flug f", Flug.class).getResultList();
	}
	
	public Flug findFlugByFlugNr(String flugNr) {
		return entityManager.find(Flug.class, flugNr);
	}
	
	public void create(PrintWriter pw, Flug f) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Ihr Flug konnte nicht hinzugefügt werden.", "alert-danger", "right");	
			return;
		}
		writeAlert(pw, "Erfolg!", "Ihr Flug wurde erfolgreich hinzugefügt.", "alert-success", "right");	
	}
	
	public Flug generateFlug(PrintWriter pw, String flugnr, String flugzeug, String km, String landezeit, String startzeit) {
		Flug flug = new Flug();
		flug.setFlugnr(flugnr);
		flug.setFlugzeug(Integer.parseInt(flugzeug));
		flug.setKm(Integer.parseInt(km));
		flug.setStartzeit(timeFormatter(startzeit));
		flug.setLandezeit(timeFormatter(landezeit));
		BigDecimal flugzeit = BigDecimal.valueOf(getFlugzeit(pw,flug.getStartzeit(), flug.getLandezeit()));
		BigDecimal compareFlugzeit = BigDecimal.valueOf(0.0);
		if(flugzeit.compareTo(compareFlugzeit) == 0) {
			return null;
		}
		flug.setFlugzeit(flugzeit);
		create(pw, flug);
		return flug;
	}

	public void updateFlug(PrintWriter pw, String flugNr) {
		try {
			Flug flug = entityManager.find(Flug.class, flugNr);
			entityManager.getTransaction().begin();
			flug.setInklusiveMahlzeit(!flug.getInklusiveMahlzeit());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Dem Flug konnte keine Mahlzeit hinzugefügt werden.", "alert-danger", "left");
			return;
		}
		writeAlert(pw, "Erfolg!", "Dem Flug wurde eine Mahlzeit hinzugefügt.", "alert-success", "left");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listAllFluglinien() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.flugnr, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel` FROM `flug` As `f`, fluglinien As `fl` INNER JOIN flughäfen AS `flS` ON flS.ID = fl.Startort INNER JOIN flughäfen As `flD` ON flD.ID = fl.Zielort WHERE f.flugnr = fl.Fluglinie").getResultList();
		return obj;
	}
	
	public DetailFlug findFlugByIdWithDetails(String flugNr) {
		query = entityManager.createNativeQuery("SELECT f.flugnr, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel`, f.Startzeit, f.Landezeit, f.flugzeit, f.km, f.inklusiveMahlzeit FROM `flug` As `f`, fluglinien As `fl` INNER JOIN flughäfen AS `flS` ON flS.ID = fl.Startort INNER JOIN flughäfen As `flD` ON flD.ID = fl.Zielort INNER JOIN flugzeuge As `fZ` ON fZ.id = f.flugzeug WHERE f.flugnr = fl.Fluglinie AND f.flugnr = :flugnr");
		query.setParameter("flugNr", flugNr);
		return (DetailFlug) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Flug> listFlightsByTime(String ersteZeit, String zweiteZeit) {
		Time first = timeFormatter(ersteZeit);
		Time second = timeFormatter(zweiteZeit);
		query = entityManager.createQuery("SELECT f from Flug f WHERE f.startzeit Between :first  AND :second", Flug.class);
		query.setParameter("first", first);
		query.setParameter("second", second);
		return query.getResultList();
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

	public double getFlugzeit(PrintWriter pw, Date d1, Date d2) {
		if(d1.getTime() > d2.getTime()) {
			writeAlert(pw, "Warnung!", "Die Startzeit darf nicht nach der Landezeit liegen.", "alert-danger", "left");
			return 0.0;
		}
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
