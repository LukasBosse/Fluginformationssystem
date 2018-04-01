/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.Passagier;

@Remote
public interface PassagierDao {

	public List<Passagier> listPassagier();
	
	public Passagier findPassagier(String name);
	
	public void createPassagier(PrintWriter pw, String name, String ort, String geburtsdatum, boolean geschlecht);
	
}
