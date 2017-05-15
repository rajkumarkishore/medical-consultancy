<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="/mcs/assets/js/register-patient.js"></script>
</head>
<body>
	<div class="container">
		<f:form modelAttribute="register-patient">
			<div class="row">
				<div class="col-sm-5">
					<div class="form-group">
						<label for="nameOfPatient">Name</label><span
							id="error-nameOfPatient" class="error-label"></span>
						<f:input path="nameOfPatient" class="form-control"
							placeholder="patient name" />
					</div>
					<div class="form-group">
						<label for="addressOfPatient" class="control-label">
							Address</label><span id="error-addressOfPatient" class="error-label"></span>
						<f:textarea path="addressOfPatient" class="form-control"
							placeholder="patient address"></f:textarea>
					</div>

					<div class="form-group">
						<label for="mobile" class="control-label">Mobile No</label><span
							id="error-mobile" class="error-label"></span>
						<f:input type="text" path="mobile" class="form-control"
							maxlength="10" placeholder="patient mobile" />
					</div>
					<div class="form-group">
						<label for="email" class="control-label">Email </label>
						<f:input type="text" path="email" class="form-control"
							placeholder="patient email" />
					</div>

				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="ageOfPatientStr" class="control-label"> Age</label><span
							id="error-ageOfPatientStr" class="error-label"></span>
						<f:input type="text" path="ageOfPatientStr" min="0" max="120"
							class="form-control" placeholder="patient age" />
					</div>
					<div class="radio">
						<label class="control-label"> <f:radiobutton
								path="genderOfPatient" value="Male" checked="true" /> <strong>Male</strong>
						</label> <label class="control-label"> <f:radiobutton
								path="genderOfPatient" value="Female" /> <strong>Female</strong>
						</label> <label class="control-label"> <f:radiobutton
								path="genderOfPatient" value="Other" /> <strong>Other</strong>
						</label>
					</div>

					<div class="form-group well">
						<label for="regnFee" class="control-label">Registration
							Fee </label><span
							id="error-regnFee" class="error-label"></span>
						<f:input type="text" path="regnFee" class="form-control"
							placeholder="&#x20B9; 0.00" htmlEscape="false" />
					</div>
					<div class="form-group"
						style="padding-top: 30%; text-align: right;">
						<button type="button" class="btn btn-success" id="save-patient">Save</button>
						<button class="btn btn-warning" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</f:form>
	</div>
</body>
</html>