<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="assets/js/appointment.js"></script>
</head>

<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="book">Appointment</a></li>
			<li><a href="/search">Search</a></li>
			<li><a href="/transactions">Transactions</a></li>
		</ul>
	</div>
	<div class="container">
		<f:form modelAttribute="appointment">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="nameOfPatient" class="control-label">Patient
							Name</label>
						<f:input type="text" path="nameOfPatient" class="form-control" />
					</div>
					<div class="form-group">
						<label for="ageOfPatient" class="control-label">Patient
							Age</label>
						<f:input type="text" path="ageOfPatient" min="0" max="120"
							class="form-control" />
					</div>
					<div class="radio">
						<label class="control-label"> <f:radiobutton
								path="genderOfPatient" value="Male" /> Male
						</label>
					</div>
					<div class="radio">
						<label class="control-label"> <f:radiobutton
								path="genderOfPatient" value="Female" /> Female
						</label>
					</div>
					<div class="form-group">
						<label for="addressOfPatient" class="control-label">Patient
							Address</label>
						<f:textarea path="addressOfPatient" class="form-control"></f:textarea>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="dateOfAppointment" class="control-label">Date
							of Appointment</label>
						<f:input type="text" path="dateOfAppointment" class="form-control" />
					</div>
					<div class="form-group">
						<label for="referredBy" class="control-label">Referred By</label>
						<f:input type="text" path="referredBy" class="form-control" />
					</div>
					<div class="form-group">
						<label for="nameOfDoctor" class="control-label">Doctor
							Name</label>
						<f:input type="text" path="nameOfDoctor" class="form-control" />
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-4">
					<table class="table">
						<thead>
							<tr>
								<th><input type="checkbox" name="all" /></th>
								<th>Service particulars</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${appointment.services}" var="service"
								varStatus="status">
								<tr>
									<td>
										<div class="form-group">
											<f:checkbox path="services[${status.index}].index"
												value="${status.index}" />
										</div>
									</td>
									<td>
										<div class="form-group">
											<f:input type="text" path="services[${status.index}].type"
												class="form-control" />
										</div>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="2" style="text-align: right">
									<div class="form-group">
										<button class="btn btn-default" name="add">+</button>
										<button class="btn btn-default" name="remove">-</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-default" name="save">Book</button>
				<button class="btn btn-default" name="cancel">Cancel</button>
			</div>
		</f:form>
	</div>
	<div class="footer navbar-fixed-bottom">
		<div class="container">AROGYA</div>
	</div>
</body>
</html>