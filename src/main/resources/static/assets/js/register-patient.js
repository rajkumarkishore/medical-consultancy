$(document).ready(function(e) {

	$('#save-patient').click(function() {
		//remove all validation messages -- work here 
		$('.error-label').text('');

		var data = {};
		data.nameOfPatient = $('input[name=nameOfPatient]').val();
		data.addressOfPatient = $('textarea[name=addressOfPatient]').val();
		data.ageOfPatientStr = $('input[name=ageOfPatientStr]').val();
		data.genderOfPatient = $('input[name=genderOfPatient]:checked').val();

		data.regnFee = $('input[name=regnFee]').val();
		data.email = $('input[name=email]').val();
		data.mobile = $('input[name=mobile]').val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/mcs/registration/patient",
			data : JSON.stringify(data),
			dataType : 'json',
			timeout : 600000,
			success : function(data) {
				if ($.isEmptyObject(data.errors)) {
					alert('Registration Complete!');
					window.location.replace("registration/" + data.patientId);
				} else {
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
