package com.fis.de;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.fis.model.Flug;

public class Statusverwaltung {

	public static String verifyStatus(Flug flug) {
		String randomStatus = generateRandomStatus();
		String status = "<span class='new badge red' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>N/A</span>";
		if(!randomStatus.isEmpty()) {
			return randomStatus;
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Time date = new Time(new Date().getTime());
		String departureTime = flug.getStartzeit().toString();
		Date dateCurrentTime = null;
		Date dateDepartureTime = null;
		try {
			dateCurrentTime = format.parse(date.toString());
			dateDepartureTime = format.parse(departureTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(dateDepartureTime.getTime() > dateCurrentTime.getTime()) {
			status = "<span class='new badge green' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>Scheduled</span>";
		} else if(dateDepartureTime.getTime() < dateCurrentTime.getTime()) {
			if(dateCurrentTime.getTime() - dateDepartureTime.getTime() <= 21600000) {
				status = "<span class='new badge green' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>Landed</span>";
			} else {
				status = "<span class='new badge green' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>Departured</span>";
			}
		}
		return status;
	}
	
	private static String generateRandomStatus() {
		Random rand = new Random();
	    int randomNum = rand.nextInt(10);
	    if(randomNum == 0) {
	    	return "<span class='new badge orange' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>Delayed</span>";
	    } else if(randomNum == 2) {
	    	return "<span class='new badge red' style='margin-left: 0 !important;float: none !important;' data-badge-caption=''>Canceled</span>";
	    }
	    return "";
	}
	
}
