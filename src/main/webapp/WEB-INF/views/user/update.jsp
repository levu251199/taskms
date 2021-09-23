<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>TaskMS | Profile</title>
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

		<form:form action="/user/update" modelAttribute="form"
			style="box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px, rgba(17, 17, 26, 0.05) 0px 8px 32px;"
			class="p-5 mt-3 col-lg-5 text-center">
			<h1 style="letter-spacing: 2px;">
				<i class="fas fa-dice-d20"></i> TaskMS
			</h1>
			<div class="mt-3 row">
				<div class="col form-group">
					<form:input path="username" type="text" class="form-control"
						id="username" placeholder="Tên người dùng" required="required"/>
				</div>
				<div class="col form-group">
					<form:input path="email" type="email" class="form-control"
						id="email" placeholder="Email" required="required"/>
				</div>
			</div>
			<div class="mt-3 row">
				<div class="col form-group">
					<form:input path="lastName" type="text" class="form-control"
						id="last-name" placeholder="Họ" required="required"/>
				</div>
				<div class="col form-group">
					<form:input path="firstName" type="text" class="form-control"
						id="first-name" placeholder="Tên" required="required"/>
				</div>
			</div>
			<div class="mt-3 row">
				<div class="col form-group">
					<form:input path="birth" type="date" class="form-control"
						id="birth" required="required"/>
				</div>
				<div class="col form-group">
				<c:choose>
				<c:when test="${empty account.activated}">
					<i class="fas fa-user-check"></i><span> Tài khoản chưa xác
						thực</span>
				</c:when>
				<c:otherwise>
					<i class="fas fa-user-check"></i><span> Tài khoản đã xác
						thực</span>					
				</c:otherwise>
			</c:choose>
				</div>
			</div>
			
			<div class="form-group">
				<form:hidden path="password" />
				<form:hidden path="activated" />
			</div>

			<div class="row form-group">
				<div class="col"></div>
				<div class="col">
					<button type="submit" class="mt-3 btn btn-dark form-control">Cập
						nhật</button>
				</div>
				<div class="col"></div>
			</div>
		</form:form>

	</div>
</body>

</html>