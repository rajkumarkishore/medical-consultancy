<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
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
<script src="/mcs/assets/js/billing.js"></script>
</head>
<body>
	<div class="container">
		<f:form modelAttribute="billing">
			<div class="row">
				<div class="col-sm-4">
					<h6>
						Appointment # :
						<c:out value="${appointmentNo}" />
						<input type="hidden" id="hid_appointment_no"
							value="${appointmentNo}" />
					</h6>
					<h6>
						Patient Name :
						<c:out value="${nameOfPatient}" />
					</h6>
					<h6>
						Appointment Date:
						<c:out value="${dateOfAppointment}" />
					</h6>
					<br>

					<div class="controls readonly">
						<strong><c:out value="${defaultservice} - ${doctor}" /></strong>
					</div>
					<input type="checkbox" name="index0" id="index0" value="0"
						class="row-index" style="visibility: hidden;" /> 
					<input type="hidden" name="type0" id="type0"
						class="form-control input-sm" value="${defaultservice}" />
					<input type="hidden" name="name0" id="name0"
						class="form-control input-sm" value="${defaultservice} - ${doctor}" />
					<div class="form-group">
						<input type="text" name="serviceFee0" id="serviceFee0"
							class="form-control input-sm" placeholder="Doctor's Fee(&#8377 0.00)" /><span id="error-serviceFee0" class="error-label"></span>
					</div>
				</div>
				<div class="col-sm-8" style="border-left: 1px solid #eee">
					<div id="div-services-requested">
						<input type="hidden" id="hid_row_index" value="1" />
						<table class="table" style="width: 60%">
							<thead>
								<tr>
									<th><input type="checkbox" name="all" /></th>
									<th>Service particulars</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-default" id="add-service">
							<strong>+</strong>
						</button>
						<button type="button" class="btn btn-default"
							id="remove-service">
							<strong>-</strong>
						</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<button type="button" class="btn btn-success" id="save-bill">
							Submit</button>
							<button type="button" class="btn btn-warning" data-dismiss="modal">
							Cancel</button>
					</div>
				</div>
			</div>
		</f:form>
	</div>
</body>
</html>