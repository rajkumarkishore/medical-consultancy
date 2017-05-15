<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	margin-top: 90px; 
	padding-top: 3%;
	padding-bottom: 20%;
	background-color: #f6f6f6;
	height: 30%;
	border:1px solid #ccc;
}
</style>
</head>
<body>
	<%@ include file="../header-login.html"%>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<!-- <h4>
					<label style="font-weight: normal;">AROGYA SOCIETY</label>
				</h4> -->
			</div>
			<div class="col-sm-4 login-box">
				<form name="authentication" action="login" method="post">
					<div class="form-group">
						<label class="control-label" for="username">Username</label> <input
							type="text" name="username" class="form-control" />
					</div>
					<div class="form-group">
						<label class="control-label" for="username">Password</label> <input
							type="password" name="password" class="form-control" />
					</div>
					<div class="form-group">
						<button class="btn btn-success">Login</button>
					</div>
					<c:if test="${error == true }">
						<div class="label label-danger">Login failed. Bad
							Credentials!</div>
					</c:if>
				</form>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
	<%@ include file="../footer.html"%>
</body>
</html>