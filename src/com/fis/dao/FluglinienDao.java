/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface FluglinienDao {

	public void createRelation(PrintWriter pw, String fluglinie, int startOrt, int zielOrt);
	
	public List<Object[]> listRelationen();
	
}
