$(document).ready(function() {
	/* Print Appointment Bill */
	$('.print-bill').click(function() {

		var appId = $(this).val();
		var url = "/mcs/billing/" + appId + "/print-bill";
		window.open(url);
	});
});