 $(document).ready(function() {
			   $('select').material_select();
			   $('.timepicker').pickatime({
			    default: 'now', 
			    fromnow: 0,      
			    twelvehour: false,
			    donetext: 'OK',
		        format: "HH:ii:SS",
		        cleartext: 'Clear',
			    canceltext: 'Cancel',
			    autoclose: false,
			    ampmclickable: true,
			    aftershow: function(){}
			  });
		 });
	    $('.timepicker').on('change', function() {
	        let receivedVal = $(this).val();
	        $(this).val(receivedVal + ":00");
	    });