/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.de;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Klasse: NamingService
 * Beschreibung: Implementierung eines JNDI Services
 */

public class NamingService {

	private Context ctx;
	
	/** Constructor **/
	public NamingService() {
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichern eines Objektes in den Namespace via key/value.
	 * @param key
	 * @param obj
	 */
	public void put(String key, Object obj) {
		try {
			ctx.bind(key, obj);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Auslesen eines Objektes aus dem Namespace via key/value.
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		try {
			return ctx.lookup(key);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Löschen eines Objektes aus dem Namespace via key/value.
	 * @param key
	 */
	public void remove(String key) {
		try {
			ctx.unbind(key);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
