<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script src="/mcs/assets/js/registration-single-window.js"></script>
<link rel="stylesheet" href="/mcs/assets/css/main.css">
<style type="text/css">
.gen-action {
	width: 150px;
	height: 40px;
}
</style>
</head>
<body>
	<%@ include file="../header.html"%>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="/mcs/registration">Registration</a></li>
			<li><a href="/mcs/transactions">Transactions</a></li>
		</ul>
		<br>
		<!-- outer most  -->
		<div class="row well" style="width: 100%; margin-left: 1px;">
			<!-- left  -->
			<div class="col-sm-12">
				<div class="row">
					<input type="hidden" id="hid_patient_id" value="${patient.id}" />
					<input type="hidden" id="hid_patient_name" value="${patient.name}" />
					<div class="col-sm-3">
						<label>Name:</label>
						<p class="form-control-static">
							<c:out value="${patient.name}" />
						</p>
					</div>
					<div class="col-sm-3">
						<label>Address:</label>
						<p class="form-control-static">
							<c:out value="${patient.address}" />
						</p>
					</div>
					<div class="col-sm-1">
						<label>Gender:</label>
						<p class="form-control-static">
							<c:out value="${patient.gender}" />
						</p>
					</div>
					<div class="col-sm-1">
						<label>Age:</label>
						<p class="form-control-static">
							<c:out value="${patient.age}" />
						</p>
					</div>
					<div class="col-sm-2">
						<label>Regn. Date:</label>
						<p class="form-control-static">
							<fmt:formatDate pattern="dd-MM-yyyy"
								value="${patient.registrationDate}" />
						</p>

					</div>
					<div class="col-sm-2">
						<label>Valid Upto:</label>
						<p class="form-control-static">
							<fmt:formatDate pattern="dd-MM-yyyy"
								value="${patient.validUptoDate}" />
						</p>
					</div>
				</div>

			</div>
			<!-- right  -->
			<!-- <div class="col-sm-3"></div> -->
		</div>
		<div class="row">
			<div class="col-sm-12" style="text-align: right;">
				<c:choose>
					<c:when test="${patient.isRegistrationValid}">
						<button class="gen-action btn btn-warning btn-sm"
							id="print-regn-slip">
							<span class="glyphicon glyphicon-print"></span> Registration Slip
						</button>
						<button class="gen-action btn btn-success btn-sm"
							data-toggle="modal" data-target="#myModal" id="book-appointment">Book
							Appointment</button>
					</c:when>
					<c:otherwise>
						<span style="color: red"><i>Registration validity has
								expired!</i></span>
						<button class="gen-action btn btn-sm" data-toggle="tooltip"
							title="Cannot print as registration period has expired!" disabled>
							<span class="glyphicon glyphicon-print"></span> Registration Slip
						</button>
						<button class="gen-action btn btn-success btn-sm"
							data-toggle="modal" data-target="#myModal" id="book-appointment">Renewal
							& Appointment</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-12">
				<jsp:useBean id="dateValue" class="java.util.Date" />
				<div style="overflow: auto; height: 270px;">
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th width="11%">Appointment #</th>
								<th width="13%">Appointment Date</th>
								<th width="20%">Doctor</th>
								<th width="34%">Service particulars</th>
								<th width="12%">Billing</th>
								<th width="10%">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty patient.appointments}">
									<tr>
										<td colspan="6"><i>No previous appointment found!</i></td>
									</tr>
								</c:when>
								<c:otherwise>
									<fmt:formatDate pattern="dd-MM-yyyy"
										value="<%=new java.util.Date()%>" var="now" />
									<c:forEach items="${patient.appointments}" var="app">
										<tr>
											<td><c:out value="${app.uniqueId}" /></td>
											<td><fmt:formatDate pattern="dd-MM-yyyy"
													value="${app.appointmentDate}" var="appDate" /> <c:choose>
													<c:when test="${appDate eq now}">
											<div class="label label-default" style="font-size: 100%;">Today</div>
											</c:when>
													<c:otherwise>
														<fmt:formatDate pattern="dd-MM-yyyy"
															value="${app.appointmentDate}" />
													</c:otherwise>
												</c:choose></td>
											<td><c:out value="${app.doctor.name}" /></td>
											<td><c:forEach items="${app.bill.items}" var="service"
													varStatus="status">
													<c:if test="${service.type != 'Consulting'}">
														<c:out value="${service.name}" />
														<c:if
															test="${status.index+1 lt fn:length(app.bill.items)}">, </c:if>
													</c:if>
												</c:forEach></td>
											<td><c:choose>
													<c:when test="${app.isBilled}">
													PROCESSED
												</c:when>
													<c:otherwise>
													NOT PROCESSED
												</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${app.isBilled}">
														<button
															class="print-bill action-btn btn btn-warning btn-sm"
															value="${app.uniqueId}">
															<span class="glyphicon glyphicon-print"></span> Bill
														</button>
													</c:when>
													<c:otherwise>
														<button class="bill action-btn btn btn-success btn-sm"
															data-toggle="modal" data-target="#myModal"
															value="${app.uniqueId}">Process Bill</button>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--Modal-->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-md" id="div-modal-dialog">
				<!--Modal Content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div id="modal-dynamic-content"></div>
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../footer.html"%>
</body>
</html>