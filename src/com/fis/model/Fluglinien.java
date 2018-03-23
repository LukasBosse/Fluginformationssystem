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
	private String fluglinie;

	private int startort;

	private int zielort;

	public Fluglinien() {
	}
	
	public Fluglinien(String fluglinie, int startort, int zielort) {
		this.fluglinie = fluglinie;
		this.startort = startort;
		this.zielort = zielort;
	}

	public String getFluglinie() {
		return this.fluglinie;
	}

	public void setFluglinie(String fluglinie) {
		this.fluglinie = fluglinie;
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