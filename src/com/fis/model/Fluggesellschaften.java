package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Fluggesellschaften database table.
 * 
 */
@Entity
@NamedQuery(name="Fluggesellschaften.findAll", query="SELECT f FROM Fluggesellschaften f")
public class Fluggesellschaften implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	private String gesellschaft;

	public Fluggesellschaften() {
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

	public String getGesellschaft() {
		return this.gesellschaft;
	}

	public void setGesellschaft(String gesellschaft) {
		this.gesellschaft = gesellschaft;
	}

}