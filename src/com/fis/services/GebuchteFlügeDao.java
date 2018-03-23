package com.fis.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.GebuchteFl�ge;

@Stateless
public class GebuchteFl�geDao extends AbstractDao {

	public GebuchteFl�geDao() { super(); }
	
	@SuppressWarnings("unchecked")
	public List<GebuchteFl�ge> listAllFlugh�fen() {
		List<Object[]> results = entityManager.createNativeQuery("SELECT fZ.fluglinie, fZ.hersteller, fZ.type, fH.Bezeichnung As Startort, fHZ.Bezeichnung As Zielort, f.flugzeit, f.km, Count(b.flugnr) As Auslastung, fZ.sitze As Kapazit�t from Flugzeuge As fZ, Flug As f, Fluglinien As fL INNER JOIN Flugh�fen As fH ON fH.ID = fL.Startort INNER JOIN Flugh�fen As fHZ ON fHZ.ID = fL.Zielort INNER JOIN Buchung As b ON fL.Fluglinie = b.flugnr WHERE f.flugnr = fZ.fluglinie AND f.flugnr = fL.Fluglinie GROUP BY f.flugnr").getResultList();
		List<GebuchteFl�ge> liste = new ArrayList<GebuchteFl�ge>();
		for(Object[] result : results) {
			GebuchteFl�ge gF = new GebuchteFl�ge();
			gF.setFlugLinie(result[0].toString());
			gF.setHersteller(result[1].toString());
			gF.setType(result[2].toString());
			gF.setStartOrt(result[3].toString());
			gF.setZielOrt(result[4].toString());
			gF.setFlugZeit(result[5].toString());
			gF.setDistanz(result[6].toString());
			gF.setAuslastung(Integer.parseInt(result[7].toString()));
			gF.setKapazit�t(Integer.parseInt(result[8].toString()));
			liste.add(gF);
		}
		return liste;
	}
}
