<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>TaskMS | Add Task</title>
<jsp:include page="../include/head.jsp" />
</head>

<body>
	<!-- Header -->
	<jsp:include page="../include/header.jsp" />

	<!-- Content -->
	<section style="background: url(/static/img/bg.jpg) fixed no-repeat;"
		class="py-5 text-center container-fluid">
		<div class="row py-lg-5">
			<div class="col-lg-6 col-md-8 mx-auto">
				<h1 class="fw-normal">THÊM MỘT CÔNG VIỆC MỚI</h1>
				<p class="lead text-muted fw-normal">
					Ở đây bạn có thể thêm một công việc mới vào danh sách<br>
					..đừng làm việc quá sức nhé
				</p>
				<p>
					<a href="/task/mytask" class="btn btn-warning my-2">Xem công việc hiện tại</a>
					<a href="/task/important" class="btn btn-danger my-2">Xem các công việc quan trọng</a>
				</p>
			</div>
		</div>
	</section>

	<!-- Form -->
	<div class="album py-5 bg-light">
		<div class="container col-lg-4">

			<form:form action="/task/create" modelAttribute="entity">
				<div class="form-group">
					<label for="name">Tên công việc</label>
					<form:input path="name" type="text" class="form-control" id="name" placeholder="Quét nhà cho mẹ..."/>
				</div>
				<div class="mt-3 form-group">
					<label for="priority">Mức ưu tiên</label>
					<form:input path="priority" type="text" class="form-control" id="priority" placeholder="Cực cao ( ಠ ͜ʖಠ)"/>
				</div>
				<div class="mt-3 form-group">
					<label for="due-date">Ngày hết hạn</label>
					<form:input path="due_date" type="date" class="form-control" id="due-date"/>
				</div>
				<div class="mt-3 form-group">
					<label for="description">Mô tả chi tiết</label>
					<form:textarea path="description" class="form-control" id="description" rows="3" placeholder="Không quét là ăn đòn"></form:textarea>
				</div>
				<div class="form-group">
					<form:hidden path="account.username" value="${account.username}"/>
				</div>
				<div class="form-group">
					<button type="submit" class="mt-3 btn btn-dark form-control">Tạo công việc</button>
				</div>
			</form:form>

		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="../include/footer.jsp" />
</body>

</html>