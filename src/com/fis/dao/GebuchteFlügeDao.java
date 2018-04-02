/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.GebuchteFlüge;

@Remote
public interface GebuchteFlügeDao {

	public List<GebuchteFlüge> listAllFlüge();
	
}
