package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the relationen database table.
 * 
 */
@Entity
@NamedQuery(name="Relationen.findAll", query="SELECT r FROM Relationen r")
public class Relationen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int startort;

	private int zielort;

	public Relationen() {
	}
	
	public Relationen(int startOrt, int zielOrt) {
		this.startort = startOrt;
		this.zielort = zielOrt;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartort() {
		return this.startort;
	}

	public void setStartort(int startort) {
		this.startort = startort;
	}

	public int getZielort() {
		return this.zielort;
	}

	public void setZielort(int zielort) {
		this.zielort = zielort;
	}

}