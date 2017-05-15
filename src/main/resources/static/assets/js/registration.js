$(document).ready(
		function(e) {

			$('#searchForm').submit(function() {
				var searchInput = $('#searchInput').val();
				
				if ($.trim(searchInput).length > 0) {
					return true;
				}
				
				return false;
			});

			$('#new-registration').click(
					function() {
						$(".modal-body").load(
								"/mcs/registration/patient",
								function(responseTxt, statusTxt, xhr) {
									if (statusTxt == "error")
										alert("Error: " + xhr.status + ": "
												+ xhr.statusText);
								});
					});

		});