<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="stylesheet" href="/mcs/assets/css/main.css">
<script src="/mcs/assets/js/transactions.js"></script>
<!-- date picker -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<script>
	$(document).ready(
			function() {
				var fromDate_input = $('input[name="fromDate"]'); //from date input has the name "fromDate"
				var toDate_input = $('input[name="toDate"]'); //from date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'dd-mm-yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				fromDate_input.datepicker(options);
				toDate_input.datepicker(options);
			})
</script>
</head>
<body>
	<%@ include file="../header.html"%>
	<div class="container">
		<ul class="nav nav-tabs">
			<li><a href="registration">Registration</a></li>
			<li class="active"><a href="transactions">Transactions</a></li>
		</ul>
		<br>
		<f:form modelAttribute="transaction">
			<div class="row well" style="width: 100%; margin-left: 1px;">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="fromtDate">From</label>
						<f:input path="fromDate" class="form-control input-sm"
							placeholder="dd-mm-yyyy" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="toDate">To</label>
						<f:input path="toDate" class="form-control input-sm"
							placeholder="dd-mm-yyyy" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="doctor">Doctor</label>
						<f:input path="doctor" class="form-control input-sm"
							placeholder="filter by doctor" />
					</div>
				</div>
				<div class="col-sm-3" style="padding-top: 2%;">
					<button class="btn btn-success">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
				</div>
			</div>
		</f:form>

		<div class="row">
			<div class="col-sm-12">
				<div style="overflow: auto; height: 298px;">
					<c:set var="totalFee" value="0.00" />
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th width="11%">Appointment #</th>
								<th width="20%">Patient Details</th>
								<th width="21%">Patient Address</th>
								<th width="13%">Appointment Date</th>
								<th width="18%">Doctor</th>
								<th width="9%">Bill Amount</th>
								<th width="8%">Print Bill</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${appointments}" var="app" varStatus="status">
								<tr>
									<td><c:out value="${app.uniqueId}" />
									<td><strong>Name:</strong> <c:out
											value="${app.patient.name}" /><br> <strong>Age:</strong>
										<c:out value="${app.patient.age}" /> &nbsp;&nbsp; <strong>Gender:</strong>
										<c:out value="${app.patient.gender}" /></td>
									<td><c:out value="${app.patient.address}" /></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${app.appointmentDate}" /></td>
									<td><c:out value="${app.doctor.name}" /></td>
									<td><c:choose>
											<c:when test="${app.isBilled}">
								&#x20B9; <fmt:formatNumber value="${app.bill.billAmount}"
													type="currency" pattern=".00" />
												<c:set var="totalFee"
													value="${totalFee+app.bill.billAmount}" />
											</c:when>
											<c:otherwise>
								 -
								</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${app.isBilled}">
												<button class="print-bill action-btn btn btn-warning btn-sm"
													value="${app.uniqueId}">
													<span class="glyphicon glyphicon-print"></span> Bill
												</button>
											</c:when>
											<c:otherwise>
								BILL NOT PROCESSED
								</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
							<c:if test="${empty appointments}">
								<tr>
									<td colspan="7"><i>No transactions found!</i></td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" style="text-align: right;">
				<label>Transactions:
						<c:out value="${fn:length(appointments)}" />
				 &nbsp;&nbsp; <span class="label label-default">Total Amount:
						&#x20B9; <fmt:formatNumber value="${totalFee}" type="currency"
							pattern=".00" />
				</span></label>
			</div>
		</div>
	</div>
	<%@ include file="../footer.html"%>
</body>
</html>