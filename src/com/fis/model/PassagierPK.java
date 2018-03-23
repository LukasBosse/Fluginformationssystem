package com.fis.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the passagier database table.
 * 
 */
@Embeddable
public class PassagierPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String name;

	private String ort;

	public PassagierPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PassagierPK)) {
			return false;
		}
		PassagierPK castOther = (PassagierPK)other;
		return 
			this.name.equals(castOther.name)
			&& this.ort.equals(castOther.ort);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.name.hashCode();
		hash = hash * prime + this.ort.hashCode();
		
		return hash;
	}
}