$(document).ready(
		function() {

			/* load services */
			var newRowIndex = $('#hid_row_index').val();
			var uri = "/mcs/billing/" + $('#hid_appointment_no').val()
					+ "/requested-services/addition/" + newRowIndex;
			$.get(uri, function(row) {
				$("#div-services-requested table tbody").append(row);
			});

			// add new service
			$('#add-service')
					.click(
							function() {
								/* load services */
								var newRowIndex = Number($('#hid_row_index')
										.val()) + 1;
								// set Max rowid
								$('#hid_row_index').val(newRowIndex);

								var uri = "/mcs/billing/"
										+ $('#hid_appointment_no').val()
										+ "/requested-services/addition/"
										+ newRowIndex;

								$.get(uri, function(row) {
									$("#div-services-requested table tbody")
											.append(row);
								});

							});

			// add new service
			$('#remove-service').click(function() {
				$('.row-index:checked').each(function() {
					var row = $('#service-row-' + $(this).val());
					row.remove();
				});
			});

			$('#save-bill').click(
					function() {

						// remove all validation messages -- work here
						$('.error-label').text('');

						var uri = "/mcs/billing/"
								+ $('#hid_appointment_no').val()
								+ "/processing";

						var data = {};
						var jsonServicesArray = [];
						$(".row-index").each(
								function(index) {
									var service = {};
									service.index = $("#index" + $(this).val())
											.val();
									service.name = $("#name" + $(this).val())
											.val();
									service.type = $("#type" + $(this).val())
											.val();
									service.serviceFee = $(
											"#serviceFee" + $(this).val())
											.val();

									jsonServicesArray.push(service);
								});

						data.services = jsonServicesArray;

						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : uri,
							data : JSON.stringify(data),
							dataType : 'json',
							timeout : 600000,
							success : function(data) {
								if ($.isEmptyObject(data.errors)) {
									alert('Bill processed!');
									window.location.replace("");
								} else {
									$.each(data.errors, function(key, value) {
										$('#error-' + key).text(value);
									});
								}
							},
							error : function(e) {
								alert('Hi - ' + e.statusText);
							}
						});

					});

		});