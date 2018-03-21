package com.fis.services;

import java.util.ArrayList;
import java.util.List;

import com.fis.model.GebuchteFl�ge;

public class GebuchteFl�geDao extends AbstractDao {

	public List<GebuchteFl�ge> listAllFlugh�fen() {
		List<Object[]> results = entityManager.createNativeQuery("SELECT fZ.fluglinie, fZ.hersteller, fZ.type, fH.Bezeichnung As Startort, fHZ.Bezeichnung As Zielort, f.flugzeit, f.km , COUNT(b.flugnr) As Auslastung, fZ.sitze As Kapazit�t FROM Flugzeuge As fZ INNER JOIN Flug As f ON fZ.fluglinie = f.flugnr INNER JOIN Flugh�fen As fH ON fH.ID = f.start INNER JOIN Flugh�fen As fHZ ON fHZ.ID = f.ziel INNER JOIN Buchung As b ON b.flugnr = fZ.fluglinie GROUP BY fZ.fluglinie").getResultList();
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
