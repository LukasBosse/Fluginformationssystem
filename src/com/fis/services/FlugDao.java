package com.fis.services;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fis.model.Flug;

public class FlugDao extends AbstractDao {

	public FlugDao() { super(); }
	
	public Flug findFlugByFlugNr(String flugNr) {
		query = entityManager.createQuery("Select f from Flug f where f.flugnr = :flugNr");
	    query.setParameter("flugNr", flugNr);
		return (Flug) query.getSingleResult();
	}
	
	public void create(Flug f) {
		entityManager.getTransaction().begin();
		entityManager.persist(f);
		entityManager.getTransaction().commit();	
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
