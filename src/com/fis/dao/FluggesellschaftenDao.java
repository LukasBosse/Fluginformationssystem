/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.Fluggesellschaften;

@Remote
public interface FluggesellschaftenDao {

	public List<Fluggesellschaften> listAllFlugzeuge();
	
}
