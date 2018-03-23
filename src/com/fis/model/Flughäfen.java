package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flugh�fen database table.
 * 
 */
@Entity
@NamedQuery(name="Flugh�fen.findAll", query="SELECT f FROM Flugh�fen f")
public class Flugh�fen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	public Flugh�fen() {
	}
	
	public Flugh�fen(String bezeichnung) {
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