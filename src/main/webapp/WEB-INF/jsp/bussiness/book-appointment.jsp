<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/mcs/assets/js/appointment.js"></script>

<!-- date picker -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
</head>

<script>
	$(document).ready(
			function() {
				var date_input = $('input[name="dateOfAppointment"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'dd-mm-yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
			})
</script>
<body>
	<div class="container">
		<f:form modelAttribute="appointment">
			<input type="hidden" id="idOfPatient" value="${patientId}" />
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="nameOfPatient" class="control-label">Patient
							Name</label>
						<f:input type="text" path="nameOfPatient" class="form-control"
							readonly="true" />
					</div>
					<div class="form-group">
						<div class='input-group date' id='datetimepicker1'>
							<label for="dateOfAppointment" class="control-label">Date
								of Appointment</label><span id="error-dateOfAppointment"
								class="error-label"></span>
							<f:input type="text" path="dateOfAppointment"
								class="form-control" placeholder="dd-mm-yyyy" />
						</div>
					</div>
					<div class="form-group">
						<label for="referredBy" class="control-label">Referred By</label><span
							id="error-referredBy" class="error-label"></span>
						<f:input type="text" path="referredBy" class="form-control" />
					</div>
					<div class="form-group">
						<label for="nameOfDoctor" class="control-label">Doctor
							Name</label><span id="error-nameOfDoctor" class="error-label"></span>
						<f:input type="text" path="nameOfDoctor" class="form-control" />
					</div>
				</div>
				<div class="col-sm-3">
					<f:input type="hidden" path="validRegistration"
						value="${patient.isRegistrationValid}" />
					<%-- <input type="text" name="isRegistrationValid"
							value="${patient.isRegistrationValid}" /> --%>
					<div class="jumbotron">
						<c:choose>
							<c:when test="${not patient.isRegistrationValid}">
								<span style="color: red;">
									<strong>Registration has expired!</strong>
									 Collect renewal fee below.
									</span>
									<div class="form-group">
										<label class="control-label">Regn. Date</label>
										<fmt:formatDate pattern="dd-MM-yyyy"
											value="${patient.registrationDate}" />
									</div>
									<div class="form-group">
										<label class="control-label">Valid Upto</label>
										<fmt:formatDate pattern="dd-MM-yyyy"
											value="${patient.validUptoDate}" />
									</div>
									<div class="form-group">
										<label for="regnRenewalFee" class="control-label">Renewal
											Fee</label><span id="error-regnRenewalFee" class="error-label"></span>
										<f:input type="text" path="regnRenewalFee"
											class="form-control" htmlEscape="false"
											placeholder="&#x20B9; 0.00" />
									</div>
							</c:when>
							<c:otherwise>
								<h5>Registration Valid!</h5>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="col-sm-6"></div>
			</div>
		</f:form>
		<div class="form-group">
			<button type="button" class="btn btn-success" id="book_appointment">Book</button>
			<button class="btn btn-warning" name="cancel" data-dismiss="modal">Cancel</button>
		</div>
	</div>
</body>
</html>