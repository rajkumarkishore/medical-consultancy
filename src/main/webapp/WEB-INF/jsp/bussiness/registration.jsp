<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>MCS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/mcs/assets/js/registration.js"></script>
<link rel="stylesheet" href="/mcs/assets/css/main.css">
</head>
<body>
	<%@ include file="../header.html"%>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="registration">Registration</a></li>
			<li><a href="transactions">Transactions</a></li>
		</ul>
		<br>
		<div class="row well" style="width: 100%; margin-left: 1px;">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<f:form modelAttribute="registration" id="searchForm">
					<label class="control-label">Search Patient</label>
					<div class="input-group">
						<f:input path="searchInput" class="form-control"
							placeholder="Patient Registration No / Patient Name" />
						<span class="input-group-btn">
							<button class="btn btn-default" name="search">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</f:form>
			</div>
			<div class="col-sm-3"></div>
		</div>
		<c:choose>
			<c:when test="${not empty patients}">
				<div class="row">
					<div class="col-sm-12" style="text-align: right;">
						<label><strong><i>Search Results: </i></strong><span><c:out
									value="${fn:length(patients)}" /></span> record(s) found!</label>
					</div>
				</div>
				<div style="overflow: auto; height: 310px;">
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th width="5%">ID</th>
								<th width="24%">Patient Details</th>
								<th width="27%">Patient Address</th>
								<th width="12%">Regn. Date</th>
								<th width="12%">Regn. Valid Upto</th>
								<th width="10%">Regn. Status</th>
								<th width="10%">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${patients}" var="patient">
								<tr>
									<td><c:out value="${patient.id}" /></td>
									<td><strong>Name:</strong> <c:out value="${patient.name}" /><br>
										<strong>Age:</strong> <c:out value="${patient.age}" />
										&nbsp;&nbsp; <strong>Gender:</strong> <c:out
											value="${patient.gender}" /></td>
									<td><c:out value="${patient.address}" /></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${patient.registrationDate}" /></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${patient.validUptoDate}" /></td>
									<td><c:choose>
											<c:when test="${patient.isRegistrationValid}">
											VALID
										</c:when>
											<c:otherwise>
											EXPIRED
										</c:otherwise>
										</c:choose></td>
									<td><a href="registration/${patient.id}">
											<button type="button"
												class="btn action-btn btn-warning btn-sm">Select</button>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${not initLoad}">
					<div class="alert alert-danger alert-dismissable">
						<strong>No record found!</strong>
					</div>
					<button class="btn btn-success" data-toggle="modal"
						data-target="#myModal" id="new-registration">New
						Registration</button>
				</c:if>
			</c:otherwise>
		</c:choose>

		<!--Modal-->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">
				<!--Modal Content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">REGISTER PATIENT</h4>
					</div>
					<div class="modal-body">
						<p>Some text in the modal...</p>
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../footer.html"%>
</body>
</html>