<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration Slip</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="/mcs/assets/js/bill-printing.js"></script>

<link rel="stylesheet" href="/mcs/assets/css/main.css">

<style type="text/css" media="all">
@page {
	size: auto;
	margin: 0mm;
}

@media print {
	body {
		color: graytext !important;
		-webkit-print-color-adjust: exact;
	}
}

body {
	color: graytext !important;
}

.footer {
	padding-top: 6%;
	padding-bottom: 2%;
	font-size: 12px;
}

.footer-bottom {
	padding-top: 2%;
	font-size: 12px;
	border-top: 1px solid #eee !important;
}
</style>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<div class="row">

				<div class="col-sm-6" style="text-align: left">

					<img src="/mcs/assets/img/logo-print.png"
						class="img-thumbnail img-responsive" width="25%" />
					<p></p>
					<p>
						<strong><span class="theme-color-scheme">AROGYA
								SOCIETY</span></strong>
					</p>
					<strong>Registration.No: </strong><small>S/2250/Distt.South/2017</small>
				</div>
				<div class="col-sm-6" style="text-align: right">
					<h4>
						<strong>CASH BILL</strong>
					</h4>
					<p>
						Printed on:
						<fmt:formatDate type="both" dateStyle="long" timeStyle="medium"
							value="${curr_date}" />
					</p>
					<p>
						Bill No:
						<c:out value="${appointment.bill.number}" />
					</p>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label">Patient Name</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.name}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Sex</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.gender}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Age</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.age}" />
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label">Patient ID</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.id}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Address</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.address}" />
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label">Mobile No</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.mobile}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Email-Id</label>
					<div class="controls readonly">
						<c:out value="${appointment.patient.email}" />
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-12">
				<c:set value="0" var="totalFee" />

				<table class="table table-bordered">
					<thead>
						<tr>
							<th style="width: 10%;">Sl No.</th>
							<th>Service Particulars</th>
							<th style="width: 20%;">Amount</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${appointment.bill.items}" var="service"
							varStatus="status">
							<tr>
								<td><c:out value="${status.index+1}" /></td>
								<td><c:out value="${service.name}" /></td>
								<td>&#x20B9; <fmt:formatNumber value="${service.fee.total}"
										type="currency" pattern=".00" /></td>
							</tr>
							<c:set value="${totalFee + service.fee.total}" var="totalFee" />
						</c:forEach>
						<tr>
							<td colspan="2" style="text-align: right;"><strong>Total</strong></td>
							<td><strong>&#x20B9; <fmt:formatNumber
										value="${totalFee}" type="currency" pattern=".00" /></strong></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row footer">
			<div class="col-sm-12">
				<h5>
					<i><span class="theme-color-scheme">To know more about
							offer/medical camps, kindly give a missed call at<strong>
								<span class="theme-color-scheme">01203829705</span>
						</strong>.
					</span></i>
				</h5>
			</div>
		</div>
		<div class="row footer-bottom">
			<div class="col-sm-4">
				<label class="control-label">Our Address</label>
				<div class="controls readonly">Arogya Society, 2nd floor of
					Kushan Plaza, Opposite to Assam Oil Petrol Pump, Ganeshguri,
					Guwahati-781006</div>
			</div>
			<div class="col-sm-3">
				<label class="control-label">Email-ID</label>
				<div class="controls readonly">help@arogyasociety.com</div>
			</div>
			<div class="col-sm-2">
				<label class="control-label">Mobile No</label>
				<div class="controls readonly">7086598106</div>
			</div>
			<div class="col-sm-3">
				<label class="control-label">Website</label>
				<div class="controls readonly">arogyasociety.com</div>
			</div>
		</div>
	</div>
</body>
</html>