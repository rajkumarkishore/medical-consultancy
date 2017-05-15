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
<script src="/mcs/assets/js/registration.js"></script>
</head>
<body>
	<div class="container">
		<f:form modelAttribute="renew-registration">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label >Name</label>
						<input type="text" class="form-control" value="${nameOfPatient}" readonly >
					</div>
					<div class="form-group">
						<label class="control-label">Renewal
							Date </label>
						<input type="text" class="form-control" value="${renewDate}" readonly>
					</div>
					<div class="form-group">
						<label for="renewalFee" class="control-label">Fee</label>
						<f:input path="renewalFee" class="form-control"/>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-default" id="renew">Save</button>
						<button class="btn btn-default">Cancel</button>
					</div>
				</div>
			</div>
		</f:form>
	</div>
</body>
</html>