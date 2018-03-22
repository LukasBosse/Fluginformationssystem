package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fluglinien database table.
 * 
 */
@Entity
@NamedQuery(name="Fluglinien.findAll", query="SELECT f FROM Fluglinien f")
public class Fluglinien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	private String gesellschaft;

	public Fluglinien() {
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