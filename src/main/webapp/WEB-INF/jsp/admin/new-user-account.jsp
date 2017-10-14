<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/mcs/assets/css/main.css">
<title>MCS-Login</title>
<!-- date picker -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
</head>
<script>
	$(document).ready(
			function() {
				var date_input = $('input[name="dateOfBirth"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'dd-mm-yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
			});
</script>
<body>
	<%@ include file="../header.html"%>

	<div class="container">
		<br />
		<f:form modelAttribute="account" method="post">

			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
						<label class="control-label" for="firstName">First Name<sup>*</sup></label>
						<f:input path="firstName" class="form-control" />
					</div>

					<div class="form-group">
						<label class="control-label" for="middleName">Middle Name</label>
						<f:input path="middleName" class="form-control" />
					</div>

					<div class="form-group">
						<label class="control-label" for="lastName">Last Name<sup>*</sup></label>
						<f:input path="lastName" class="form-control" />
					</div>

					<div class="form-group">
						<label for="address" class="control-label">Address<sup>*</sup></label>
						<f:textarea path="address" class="form-control" />
					</div>
				</div>
				<div class="col-sm-4">

					<div class="form-group">
						<label class="control-label" for="dateOfBirth">Date Of
							Birth</label>
						<f:input path="dateOfBirth" class="form-control" placeholder="DD-MM-YYYY"/>
					</div>

					<div class="form-group">
						<label for="gender" class="control-label">Gender<sup>*</sup></label>
						<f:select path="gender" class="form-control">
							<f:option value="">-</f:option>
							<f:option value="Male">Male</f:option>
							<f:option value="Female">Female</f:option>
							<f:option value="Other">Other</f:option>
						</f:select>
					</div>

					<div class="form-group">
						<label for="mobileNo" class="control-label">Mobile No</label>
						<f:input type="text" path="mobileNo" class="form-control"
							maxlength="10" placeholder="" />
					</div>

					<div class="form-group">
						<label for="emailId" class="control-label">Email </label>
						<f:input type="text" path="emailId" class="form-control"
							placeholder="" />
					</div>

				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="userName" class="control-label">User Name<sup>*</sup></label>
						<f:input type="text" path="userName" class="form-control" />
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="password" class="control-label">Password<sup>*</sup></label>
						<f:password path="password" class="form-control" />
					</div>

					<div class="form-group">
						<label for="confirmPassword" class="control-label">Confirm
							Password<sup>*</sup>
						</label>
						<f:password path="confirmPassword" class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success" id="save">Save</button>
				<button type="submit" class="btn btn-warning" id="cancel">Cancel</button>
			</div>
		</f:form>
	</div>
	<c:if test="${success}">
		<script type="text/javascript">
			alert("Account created successfully!");
			window.location.replace("");
		</script>
	</c:if>
	<%@ include file="../footer.html"%>
</body>
</html>