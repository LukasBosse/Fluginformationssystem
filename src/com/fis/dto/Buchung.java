package com.fis.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the buchung database table.
 * 
 */
@Entity
@NamedQuery(name="Buchung.findAll", query="SELECT b FROM Buchung b")
public class Buchung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean bestaetigt;

	private String flugnr;

	private int geschlecht;

	private String name;

	private String ort;

	private BigDecimal preis;

	@Temporal(TemporalType.DATE)
	private java.util.Date tag;

	public Buchung() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getBestaetigt() {
		return this.bestaetigt;
	}

	public void setBestaetigt(boolean bestaetigt) {
		this.bestaetigt = bestaetigt;
	}

	public String getFlugnr() {
		return this.flugnr;
	}

	public void setFlugnr(String flugnr) {
		this.flugnr = flugnr;
	}

	public int getGeschlecht() {
		return this.geschlecht;
	}

	public void setGeschlecht(int geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public java.util.Date getTag() {
		return this.tag;
	}

	public void setTag(java.util.Date tag) {
		this.tag = tag;
	}
	
	public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
	}

}