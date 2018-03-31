function loadDetailsByID(link) {	
	var flugNr = $(link).text();
	$("#flugDetails").html("");
	$.ajax({
	    url: 'DetailLoader',
	    type: 'GET',
	    data: {
	    	flugNr: flugNr
	    },
	    success: function(data){ 
	    	$("#flugDetails").html(data);
	    },
	    error: function(data) {
	    	$("#flugDetails").html("<tr><td> - Es konnten keine Informationen zu diesem Flug gefunden werden. - </td></tr>");
	    }
	});
}