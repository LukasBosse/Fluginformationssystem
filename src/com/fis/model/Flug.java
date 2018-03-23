package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the flug database table.
 * 
 */
@Entity
@NamedQuery(name="Flug.findAll", query="SELECT f FROM Flug f")
public class Flug implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String flugnr;

	private BigDecimal flugzeit;

	private int flugzeug;

	private boolean inklusiveMahlzeit;

	private int km;

	private Time landezeit;

	private int start;

	private Time startzeit;

	private Timestamp timeStamp;

	private int ziel;

	public Flug() {
	}
	
	public String getFlugnr() {
		return this.flugnr;
	}

	public void setFlugnr(String flugnr) {
		this.flugnr = flugnr;
	}

	public BigDecimal getFlugzeit() {
		return this.flugzeit;
	}

	public void setFlugzeit(BigDecimal flugzeit) {
		this.flugzeit = flugzeit;
	}

	public int getFlugzeug() {
		return this.flugzeug;
	}

	public void setFlugzeug(int flugzeug) {
		this.flugzeug = flugzeug;
	}

	public boolean getInklusiveMahlzeit() {
		return this.inklusiveMahlzeit;
	}

	public void setInklusiveMahlzeit(boolean inklusiveMahlzeit) {
		this.inklusiveMahlzeit = inklusiveMahlzeit;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public Time getLandezeit() {
		return this.landezeit;
	}

	public void setLandezeit(Time landezeit) {
		this.landezeit = landezeit;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Time getStartzeit() {
		return this.startzeit;
	}

	public void setStartzeit(Time startzeit) {
		this.startzeit = startzeit;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getZiel() {
		return this.ziel;
	}

	public void setZiel(int ziel) {
		this.ziel = ziel;
	}

}