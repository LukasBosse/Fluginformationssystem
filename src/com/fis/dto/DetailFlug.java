package com.fis.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;


public class DetailFlug implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String flugnr;

	private BigDecimal flugzeit;

	private String hersteller;
	
	private String type;
	
	private String startOrt;
	
	private String landeOrt;
	
	private boolean inklusiveMahlzeit;

	private int km;
	
	private Time landezeit;

	private Time startzeit;

	public DetailFlug(String flugnr, BigDecimal flugzeit, String hersteller, String type, String startOrt,
			String landeOrt, boolean inklusiveMahlzeit, int km, Time landezeit, Time startzeit) {
		super();
		this.flugnr = flugnr;
		this.flugzeit = flugzeit;
		this.setHersteller(hersteller);
		this.setType(type);
		this.setStartOrt(startOrt);
		this.setLandeOrt(landeOrt);
		this.inklusiveMahlzeit = inklusiveMahlzeit;
		this.km = km;
		this.landezeit = landezeit;
		this.startzeit = startzeit;
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

	public Time getStartzeit() {
		return this.startzeit;
	}

	public void setStartzeit(Time startzeit) {
		this.startzeit = startzeit;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartOrt() {
		return startOrt;
	}

	public void setStartOrt(String startOrt) {
		this.startOrt = startOrt;
	}

	public String getLandeOrt() {
		return landeOrt;
	}

	public void setLandeOrt(String landeOrt) {
		this.landeOrt = landeOrt;
	}

}