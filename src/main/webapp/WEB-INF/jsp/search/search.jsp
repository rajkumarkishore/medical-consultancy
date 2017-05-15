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
<script src="/assets/js/billing.js"></script>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li><a href="/appointment/book">Registration</a></li>
			<li><a href="#">Appointment</a></li>
			<li class="active"><a href="search">Search</a></li>
			<li><a href="/transactions">Transactions</a></li>
		</ul>

		<f:form modelAttribute="search" class="form-inline">
			<div class="form-group">
				<label for="patientName">Patient Name</label>
				<f:input path="patientName" class="form-control" />
			</div>
			<div class="form-group">
				<label for="appointmentId">Reference No</label>
				<f:input path="appointmentId" class="form-control" />
			</div>
			<button class="btn btn-default">Search</button>
		</f:form>

		<table class="table">
			<thead>
				<tr>
					<th>Ref. #</th>
					<th>Patient Details</th>
					<th>Date of Appointment</th>
					<th>Doctor</th>
					<th>Generate Bill</th>
					<th>Bill Copy</th>
					<th>Ack. Slip</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${appointments}" var="app" varStatus="status">
					<tr>
						<td><c:out value="${app.uniqueId}" /></td>
						<td>Name: <c:out value="${app.patient.name}" /><br>
							Age: <c:out value="${app.patient.age}" /><br> Gender: <c:out
								value="${app.patient.gender}" /><br> Address: <c:out
								value="${app.patient.address}" />
						</td>
						<td><c:out value="${app.appointmentDate}" /></td>
						<td><c:out value="${app.doctor.name}" /></td>
						<td><c:choose>
								<c:when test="${app.isBilled}">
									<span class="glyphicon glyphicon-lg glyphicon-ok text-success"></span>
								</c:when>
								<c:otherwise>
									<button class="btn btn-info" data-toggle="modal"
										data-target="#myModal" id="bill-generate-btn"
										value="${app.uniqueId}">generate</button>
								</c:otherwise>
							</c:choose></td>
						<td><a href="#" class="btn btn-danger btn-md"><span
								class="glyphicon glyphicon-print"></span></a></td>
						<td><a href="#" class="btn btn-danger btn-md"><span
								class="glyphicon glyphicon-print"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--Modal-->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-md">
			<!--Modal Content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">BILLING</h4>
					<h6>
						Reference # : <span id="appRefNo"></span>
					</h6>
					<h6>
						Patient Name : <span id="nameOfPatient"></span>
					</h6>
					<h6>
						Appointment Date: <span id="dateOfAppointment"></span>
					</h6>

				</div>
				<div class="modal-body">
					<p>
					<div id="div-services"></div>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Save</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="footer navbar-fixed-bottom1">
		<div class="container">AROGYA</div>
	</div>
</body>
</html>