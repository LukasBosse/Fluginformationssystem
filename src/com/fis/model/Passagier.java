package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the passagier database table.
 * 
 */
@Entity
@NamedQuery(name="Passagier.findAll", query="SELECT p FROM Passagier p")
public class Passagier implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	private String name;

	private String ort;

	private String geburtsdatum;

	private boolean geschlecht;

	public Passagier() {}
	
	public Passagier(String name, String ort, String geburtsdatum, boolean geschlecht) {
		this.name = name;
		this.ort = ort;
		this.geburtsdatum = geburtsdatum;
		this.geschlecht = geschlecht;
	}

	public String getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public boolean getGeschlecht() {
		return this.geschlecht;
	}

	public void setGeschlecht(boolean geschlecht) {
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

}