/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.Buchung;
import com.fis.dto.Passagier;

@Remote
public interface BuchungsDao {

	public List<Buchung> findUnbestätigteBuchung();
	
	public List<Buchung> findBuchungById(String flugNr);
	
	public List<Buchung> findBuchungByName(String name);
	
	public void addBuchung(PrintWriter pw, Passagier p, String flugNr);
	
	public void updateBuchung(PrintWriter pw, int id);
	
}
