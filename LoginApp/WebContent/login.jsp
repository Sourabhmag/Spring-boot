<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./login/css/util.css">
	<link rel="stylesheet" type="text/css" href="./login/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/images/img-01.jpg');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form class="login100-form validate-form" action="login" method="post">
					<div class="login100-form-avatar">
						<img src="">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">
						User Login
					</span>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Username is required">
						<input class="input100" type="text" name="username" placeholder="Username">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

					<div class="text-center w-full p-t-25 p-b-230">
						<a href="forgotPassword.jsp" class="txt1">
							Forgot Password
						</a>
					</div>

					<div class="text-center w-full">
						<a class="txt1" href="register.jsp">
							Create new account
							<i class="fa fa-long-arrow-right"></i>						
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/bootstrap/js/popper.js"></script>
	<script src="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/bootstrap/jsbootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/home/user/Documents/workspace-sts-3.9.10.RELEASE/LoginApp/WebContent/login/js/main.js"></script>

</body>
</html>