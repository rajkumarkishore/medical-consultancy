$(document).ready(function() {

	$('#book_appointment').click(function() {
		//remove all validation messages -- work here 
		$('.error-label').text('');
		
		var data = {};
		data.idOfPatient = $('#idOfPatient').val();
		data.nameOfPatient = $('input[name=nameOfPatient]').val();
		data.referredBy = $('input[name=referredBy]').val();
		data.dateOfAppointment = $('input[name=dateOfAppointment]').val();
		data.nameOfDoctor = $('input[name=nameOfDoctor]').val();
		data.regnRenewalFee = $('input[name=regnRenewalFee]').val();
		data.validRegistration = $('input[name=validRegistration]').val();
		
		var uri="/mcs/appointment/book/"+data.idOfPatient+"/"+data.nameOfPatient;
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : uri,
			data : JSON.stringify(data),
			dataType : 'json',
			timeout : 600000,
			success : function(data) {
				if ($.isEmptyObject(data.errors)) {
					alert('APPOINTMENT BOOKED!');
					window.location.replace("");	
				}else{
					$.each(data.errors, function(key, value) {
						$('#error-'+key).text(value);
					});
				}
				
			},
			error : function(e) {
				alert('Hi - ' + e.statusText);
			}
		});

	});
		
});