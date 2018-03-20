package com.fis.de;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Directory {

	private Context ctx;
	
	public Directory() {
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(String name) {
		if(get(name) != null) {
			return true;
		}
		return false;
	}
	
	public boolean put(String name, Object obj) {
		try {
			ctx.bind(name, obj);
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Object get(String name) {
		try {
			return ctx.lookup(name);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
