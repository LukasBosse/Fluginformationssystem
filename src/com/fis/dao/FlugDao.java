/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.DetailFlug;
import com.fis.dto.Flug;

@Remote
public interface FlugDao {

	public List<Flug> listFlüge();
	
	public Flug findFlugByFlugNr(String flugNr);
	
	public void create(PrintWriter pw, Flug f);
	
	public Flug generateFlug(PrintWriter pw, String flugnr, String flugzeug, String km, String landezeit, String startzeit);
	
	public void updateFlug(PrintWriter pw, String flugNr);
	
	public List<Object[]> listAllFluglinien();
	
	public DetailFlug findFlugByIdWithDetails(String flugNr);
	
	public List<Flug> listFlightsByTime(String ersteZeit, String zweiteZeit);
	
	public Date dateFormatter(String time);
	
	public double getFlugzeit(PrintWriter pw, Date d1, Date d2);
	
	public Time timeFormatter(String time);
	
	public long calcDifferenz(Date d1, Date d2);
	
	public long calcToHour(long time);
	
	public long calcToMin(long time);
	
}
