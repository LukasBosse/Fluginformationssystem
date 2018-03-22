package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flughäfen database table.
 * 
 */
@Entity
@NamedQuery(name="Flughäfen.findAll", query="SELECT f FROM Flughäfen f")
public class Flughäfen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	public Flughäfen() {
	}
	
	public Flughäfen(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}