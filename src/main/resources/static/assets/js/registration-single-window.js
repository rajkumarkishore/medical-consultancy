$(document).ready(
		function(e) {
			/* enable tooltip */
			$('[data-toggle="tooltip"]').tooltip();

			/** ** Book Appointment *** */
			$('#book-appointment').click(
					function() {
						$('#div-modal-dialog').removeClass("modal-lg");
						$('#div-modal-dialog').addClass("modal-md");
						$('.modal-title').text("BOOK APPOINTMENT");
						var uri = "/mcs/appointment/book/"
								+ $('#hid_patient_id').val()
								+ "/"
								+ encodeURIComponent($('#hid_patient_name')
										.val());

						$("#modal-dynamic-content").load(
								uri,
								function(responseTxt, statusTxt, xhr) {
									if (statusTxt == "error")
										alert("Error: " + xhr.status + ": "
												+ xhr.statusText);
								});
					});

			$('.bill').click(
					function() {
						$('#div-modal-dialog').removeClass("modal-md");
						$('#div-modal-dialog').addClass("modal-lg");
						$('.modal-title').text("BILL PROCESSING");
						var appId = $(this).val();
						var uri = "/mcs/billing/" + appId + "/bill";

						$("#modal-dynamic-content").load(
								uri,
								function(responseTxt, statusTxt, xhr) {
									if (statusTxt == "error")
										alert("Error: " + xhr.status + ": "
												+ xhr.statusText);
								});
					});

			/* Print Registration slip */
			$('#print-regn-slip').click(
					function() {
						var url = "/mcs/appointment/" + $('#hid_patient_id').val()
								+ "/ack-slip";
						window.open(url);
					});

			/* Print Appointment Bill */
			$('.print-bill').click(function() {
				var appId = $(this).val();
				var url = "/mcs/billing/" + appId + "/print-bill";
				window.open(url);
			});

			/** ** Renew Registration *** */
			$('#renew-registration').click(
					function() {
						$('.modal-title').text("RENEW REGISTRATION");
						var uri = "/registration/renew/"
								+ $('#hid_patient_id').val()
								+ "/"
								+ encodeURIComponent($('#hid_patient_name')
										.val());

						$("#modal-dynamic-content").load(
								uri,
								function(responseTxt, statusTxt, xhr) {
									if (statusTxt == "error")
										alert("Error: " + xhr.status + ": "
												+ xhr.statusText);
								});
					});

		});

/** ** Process Bill *** */
/*
 * function processBill(obj) { var appId = obj.value;
 * $('.modal-title').text("BILL PROCESSING"); var uri = "/billing/" + appId +
 * "/bill";
 * 
 * $("#modal-dynamic-content").load(uri, function(responseTxt, statusTxt, xhr) {
 * if (statusTxt == "error") alert("Error: " + xhr.status + ": " +
 * xhr.statusText); }); }
 */