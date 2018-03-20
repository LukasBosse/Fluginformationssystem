package com.fis.de;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class HTMLWriter extends PrintWriter {

	private Writer oS;
	
	public HTMLWriter(Writer oS) {
		super(oS);
		this.oS = oS;
	}

	public void write(String htmlContent) {
		try {
			oS.write(htmlContent);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void writeAlert(String title, String message, String type) {
		write(("<div style='position: absolute; bottom: 0px; left: 15px;' class='alert " + type + "'  role='alert'" +
				"<strong>" + title + "</strong> " + message +
				  "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
				    "<span aria-hidden='true'>&times;</span>" +
				  "</button>" +
				"</div>"));
	}

	
}
