<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>TaskMS | Forgot Password</title>
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

		<form action="/user/forgot" method="post"
			style="box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px, rgba(17, 17, 26, 0.05) 0px 8px 32px;"
			class="p-5 mt-3 col-lg-3 text-center">
			<h1 style="letter-spacing: 2px;">
				<i class="fas fa-dice-d20"></i> TaskMS
			</h1>
			<h4>${message}</h4>
			<div class="mt-4 form-group">
				<input name="username" type="text" class="form-control" id="username"
					placeholder="Tên người dùng" required>
			</div>
			<div class="mt-3 form-group">
				<input name="email" type="email" class="form-control" id="email"
					placeholder="Email" required>
			</div>
			<div class="form-group">
				<button type="submit" class="my-3 btn btn-dark form-control">Lấy mật khẩu</button>
			</div>
		</form>

	</div>
</body>

</html>