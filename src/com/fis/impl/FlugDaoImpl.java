/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.fis.dao.FlugDao;
import com.fis.dto.DetailFlug;
import com.fis.dto.Flug;

/**
 * Klasse: FlugDao
 * Beschreibung: Dao zum Zugriff auf die 'Flug'-Datenbanktabelle.
 */

@Stateless
public class FlugDaoImpl extends AbstractDaoImpl implements FlugDao {

	/** Constructor **/
	public FlugDaoImpl() { super(); }
	
	/**
	 * Auflistung sämtlicher registrierter Flüge.
	 * @return List<Flug>
	 */
	public List<Flug> listFlüge() {
		return entityManager.createQuery("SELECT f From Flug f", Flug.class).getResultList();
	}
	
	/**
	 * Ermittlung eines spezifischen Fluges anhand seiner Flugnummer.
	 * @param flugNr
	 * @return Flug
	 */
	public Flug findFlugByFlugNr(String flugNr) {
		return entityManager.find(Flug.class, flugNr);
	}
	
	/**
	 * Speichern eines neuen Fluges.
	 * @param pw
	 * @param f
	 */
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
	
	/**
	 * Erstellt ein neues Flug-Objekt.
	 * @param pw
	 * @param flugnr
	 * @param flugzeug
	 * @param km
	 * @param landezeit
	 * @param startzeit
	 * @return
	 */
	public Flug generateFlug(PrintWriter pw, String flugnr, String flugzeug, String km, String landezeit, String startzeit) {
		Flug flug = new Flug();
		flug.setFlugnr(flugnr);
		flug.setFlugzeug(Integer.parseInt(flugzeug));
		flug.setFlugdatum(new java.sql.Date(new Date().getTime()));
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

	/**
	 * Aktualisierung eines bestehenden Flugs.
	 * @param pw
	 * @param flugNr
	 */
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
	
	/**
	 * Auflistung sämtlicher Flüge mit zusätzlichen spezifischen Details.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> listAllFluglinien() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.flugnr, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel` FROM `flug` As `f`, fluglinien As `fl` INNER JOIN flughäfen AS `flS` ON flS.ID = fl.Startort INNER JOIN flughäfen As `flD` ON flD.ID = fl.Zielort WHERE f.flugnr = fl.Fluglinie").getResultList();
		return obj;
	}
	
	/**
	 * Ermittlung eines Fluges mit sämtlichen Details anhand seiner Flugnummer.
	 * @param flugNr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DetailFlug findFlugByIdWithDetails(String flugNr) {
		query = entityManager.createNativeQuery("SELECT f.flugnr , fZ.hersteller, fZ.type, flS.Bezeichnung As `Start`, flD.Bezeichnung As `Ziel`, f.Startzeit, f.Landezeit, f.flugzeit, f.km, f.inklusiveMahlzeit FROM `flug` As `f`, flugzeuge As `fZ`, fluglinien As `fl` INNER JOIN flughäfen AS `flS` ON flS.ID = fl.Startort INNER JOIN flughäfen As `flD` ON flD.ID = fl.Zielort WHERE fZ.id = f.flugzeug AND f.flugnr = fl.Fluglinie AND f.flugnr = ?1");
		query.setParameter(1, flugNr);
		try {
			List<Object[]> objs = query.getResultList();
			Object[] obj = objs.get(0);
			DetailFlug dF = new DetailFlug(obj[0].toString(),BigDecimal.valueOf(Double.parseDouble(obj[7].toString())),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),Boolean.getBoolean(obj[9].toString()),Integer.parseInt(obj[8].toString()),timeFormatter(obj[6].toString()),timeFormatter(obj[5].toString()));
			return dF;
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Auflistung sämtlicher Flüge in einer bestimmten Zeitspanne.
	 * @param ersteZeit
	 * @param zweiteZeit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Flug> listFlightsByTime(String ersteZeit, String zweiteZeit) {
		Time first = timeFormatter(ersteZeit);
		Time second = timeFormatter(zweiteZeit);
		java.sql.Date heute = new java.sql.Date(new Date().getTime());
		query = entityManager.createQuery("SELECT f from Flug f WHERE f.startzeit Between :first  AND :second AND f.flugdatum = :heute", Flug.class);
		query.setParameter("first", first);
		query.setParameter("second", second);
		query.setParameter("heute", heute);
		return query.getResultList();
	}
	
	/**
	 * Date-Formatter (String->Date).
	 * @param time
	 * @return
	 */
	public Date dateFormatter(String time) {
		DateFormat format = new SimpleDateFormat("dd-mm-YYYY");
		try {
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ermittlung der Flugdauer anhand Start- und Landezeitpunkt.
	 * @param pw
	 * @param d1
	 * @param d2
	 * @return
	 */
	public double getFlugzeit(PrintWriter pw, Date d1, Date d2) {
		if(d1.getTime() > d2.getTime()) {
			writeAlert(pw, "Warnung!", "Die Startzeit darf nicht nach der Landezeit liegen.", "alert-danger", "left");
			return 0.0;
		}
		long diff = calcDifferenz(d1,d2);
		return Double.parseDouble(calcToHour(diff) + "." + (calcToMin(diff)));
	}
	
	/**
	 * Time-Formatter (String->Time):
	 * @param time
	 * @return
	 */
	public Time timeFormatter(String time) {
		DateFormat format = new SimpleDateFormat("HH:mm");
		try {
			return new Time(format.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ermittelt Differenz zwischen zwei Daten.
	 * @param d1
	 * @param d2
	 * @return
	 */
	public long calcDifferenz(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime());
	}
	
	/**
	 * Zeitumrechnung (Millisekunden->Stunden)
	 * @param time
	 * @return
	 */
	public long calcToHour(long time) {
		return time / (60 * 60 * 1000) % 24;
	}
	
	/**
	 * Zeitumrechnung (Millisekunden->Minuten).
	 * @param time
	 * @return
	 */
	public long calcToMin(long time) {
		return time / (60 * 1000) % 60;
	}
	
}
