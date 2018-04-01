/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import java.util.List;

import javax.ejb.Remote;

import com.fis.dto.GebuchteFl�ge;

@Remote
public interface GebuchteFl�geDao {

	public List<GebuchteFl�ge> listAllFl�ge();
	
}
