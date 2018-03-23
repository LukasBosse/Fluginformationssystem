package com.fis.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.GebuchteFlüge;

@Stateless
public class GebuchteFlügeDao extends AbstractDao {

	public GebuchteFlügeDao() { super(); }
	
	@SuppressWarnings("unchecked")
	public List<GebuchteFlüge> listAllFlughäfen() {
		List<Object[]> results = entityManager.createNativeQuery("SELECT fZ.fluglinie, fZ.hersteller, fZ.type, fH.Bezeichnung As Startort, fHZ.Bezeichnung As Zielort, f.flugzeit, f.km, Count(b.flugnr) As Auslastung, fZ.sitze As Kapazität from Flugzeuge As fZ, Flug As f, Fluglinien As fL INNER JOIN Flughäfen As fH ON fH.ID = fL.Startort INNER JOIN Flughäfen As fHZ ON fHZ.ID = fL.Zielort INNER JOIN Buchung As b ON fL.Fluglinie = b.flugnr WHERE f.flugnr = fZ.fluglinie AND f.flugnr = fL.Fluglinie GROUP BY f.flugnr").getResultList();
		List<GebuchteFlüge> liste = new ArrayList<GebuchteFlüge>();
		for(Object[] result : results) {
			GebuchteFlüge gF = new GebuchteFlüge();
			gF.setFlugLinie(result[0].toString());
			gF.setHersteller(result[1].toString());
			gF.setType(result[2].toString());
			gF.setStartOrt(result[3].toString());
			gF.setZielOrt(result[4].toString());
			gF.setFlugZeit(result[5].toString());
			gF.setDistanz(result[6].toString());
			gF.setAuslastung(Integer.parseInt(result[7].toString()));
			gF.setKapazität(Integer.parseInt(result[8].toString()));
			liste.add(gF);
		}
		return liste;
	}
}
