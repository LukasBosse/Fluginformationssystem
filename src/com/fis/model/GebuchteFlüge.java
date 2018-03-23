package com.fis.model;

public class GebuchteFlüge {

	private String flugLinie;
	private String hersteller;
	private String type;
	private String startOrt;
	private String zielOrt;
	private int auslastung;
	private int kapazität;
	private String flugZeit;
	private String distanz;
	
	public GebuchteFlüge() {}
	
	public String getFlugLinie() {
		return flugLinie;
	}
	public void setFlugLinie(String flugLinie) {
		this.flugLinie = flugLinie;
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
	public String getZielOrt() {
		return zielOrt;
	}
	public void setZielOrt(String zielOrt) {
		this.zielOrt = zielOrt;
	}
	public int getAuslastung() {
		return auslastung;
	}
	public void setAuslastung(int auslastung) {
		this.auslastung = auslastung;
	}
	public int getKapazität() {
		return kapazität;
	}
	public void setKapazität(int kapazität) {
		this.kapazität = kapazität;
	}
	public String getFlugZeit() {
		return flugZeit;
	}
	public void setFlugZeit(String flugZeit) {
		this.flugZeit = flugZeit;
	}
	public String getDistanz() {
		return distanz;
	}
	public void setDistanz(String distanz) {
		this.distanz = distanz;
	}

}
