<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>TaskMS | Login</title>
<jsp:include page="../include/head.jsp" />
<style type="text/css">
body {
	background: url(/static/img/bg.jpg)
}
</style>
</head>

<body>
	<div style="height: 100vh; backdrop-filter: blur(5px);"
		class="d-flex flex-column justify-content-center align-items-center">

		<form
			style="box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px, rgba(17, 17, 26, 0.05) 0px 8px 32px;"
			class="p-5 mt-3 col-lg-3 text-center" action="/user/login" method="post">
			<h1 style="letter-spacing: 2px;">
				<i class="fas fa-dice-d20"></i> TaskMS
			</h1>
			<h4>${message}</h4>
			<div class="mt-4 form-group">
				<input name="username" type="text" class="form-control" id="username"
					placeholder="Tên người dùng" value="${username}">
			</div>
			<div class="mt-3 form-group">
				<input name="password" type="password" class="form-control" id="password"
					placeholder="Mật khẩu" value="${password}">
			</div>
			<div class="col form-group mt-2">
				<input name="remember" class="form-check-input" type="checkbox" id="rememberMe">
				<label class="form-check-label" for="rememberMe"> Ghi nhớ đăng nhập </label>
			</div>
			<div class="my-2 form-group">
				<a style="text-decoration: none;" href="/user/forgot"><span class="mt-3">Quên
						mật khẩu?</span></a>
			</div>
			<div class="my-4 form-group">
				<span class="mt-3">-- hoặc --</span>
			</div>
			<div class="my-2 row">
				<div class="col"></div>
				<div class="col">
					<a href="#"><i class="fab fa-facebook-square fa-3x"></i></a>
				</div>
				<div class="col">
					<a class="text-danger" href="#"><i
						class="fab fa-google-plus-square fa-3x"></i></a>
				</div>
				<div class="col"></div>
			</div>
			<div class="form-group">
				<button type="submit" class="my-3 btn btn-dark form-control">Đăng
					nhập</button>
				<a style="text-decoration: none; color: black;" href="#"><span>Chưa
						có tài khoản?</span></a>
			</div>
		</form>

	</div>
</body>

</html>