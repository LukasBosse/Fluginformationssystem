package com.fis.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flugzeuge database table.
 * 
 */
@Entity
@NamedQuery(name="Flugzeuge.findAll", query="SELECT f FROM Flugzeuge f")
public class Flugzeuge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int fluggesellschaft;

	private String fluglinie;

	private String hersteller;

	private int sitze;

	private String type;

	public Flugzeuge() {}
	
	public Flugzeuge(String hersteller, String type, int sitze, int fluggesellschaft) {
		this.hersteller = hersteller;
		this.type = type;
		this.sitze = sitze;
		this.fluggesellschaft = fluggesellschaft;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFluggesellschaft() {
		return this.fluggesellschaft;
	}

	public void setFluggesellschaft(int fluggesellschaft) {
		this.fluggesellschaft = fluggesellschaft;
	}

	public String getFluglinie() {
		return this.fluglinie;
	}

	public void setFluglinie(String fluglinie) {
		this.fluglinie = fluglinie;
	}

	public String getHersteller() {
		return this.hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public int getSitze() {
		return this.sitze;
	}

	public void setSitze(int sitze) {
		this.sitze = sitze;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}