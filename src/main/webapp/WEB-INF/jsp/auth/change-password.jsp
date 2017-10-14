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
<style type="text/css">
.login-box {
	margin-top: 60px;
	padding-top: 3%;
	padding-bottom: 27%;
	background-color: #f6f6f6;
	height: 40%;
	border: 1px solid #ccc;
}
</style>
</head>
<body>
	<%@ include file="../header.html"%>

	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4 login-box">
				<f:form modelAttribute="changePassword" method="post">
					<div class="form-group">
						<label class="control-label" for="oldPasword">Current Password</label>
						<f:password path="oldPasword" class="form-control"
							autocomplete="off" placeholder="**********" />
						<span id="error-oldPasword" class="error-label"><c:out
								value="${oldPasswordError}" /></span>
					</div>
					<div class="form-group">
						<label class="control-label" for="newPassword1">New
							Password</label>
						<f:password path="newPassword1" class="form-control"
							autocomplete="off" placeholder="**********" />
						<span id="error-newPassword1" class="error-label"><c:out
								value="${newPassword1Error}" /></span>
					</div>
					<div class="form-group">
						<label class="control-label" for="newPassword2">Confirm
							New Password</label>
						<f:password path="newPassword2" class="form-control"
							autocomplete="off" placeholder="**********" />
						<span id="error-newPassword2" class="error-label"><c:out
								value="${newPassword2Error}" /></span>
					</div>
					<div class="form-group">
						<button class="btn btn-success">Submit</button>
					</div>
				</f:form>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>

	<c:if test="${success}">
		<script type="text/javascript">
			alert("Password updated successfully!");
			window.location.replace("registration");
		</script>
	</c:if>

	<%@ include file="../footer.html"%>
</body>
</html>