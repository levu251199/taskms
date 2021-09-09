<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>TaskMS | Important Task</title>
<jsp:include page="../include/head.jsp" />
</head>

<body>
	<!-- Header -->
	<jsp:include page="../include/header.jsp" />

	<!-- Content -->
	<!-- Hero -->
	<section style="background: url(/static/img/bg.jpg) fixed no-repeat;"
		class="py-5 text-center container-fluid">
		<div class="row py-lg-5">
			<div class="col-lg-6 col-md-8 mx-auto">
				<h1 class="fw-normal">CÔNG VIỆC QUAN TRỌNG</h1>
				<p class="lead text-muted fw-normal">Ở đây chứa tất cả các công
					việc quan trọng được đánh dấu bởi bạn.</p>
				<p>
					<a href="/task/add" class="btn btn-success my-2">Tạo một công
						việc mới</a> <a href="/task/mytask" class="btn btn-warning my-2">Xem
						công việc hiện tại</a>
				</p>
			</div>
		</div>
	</section>

	<!-- Task -->
	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

				<c:forEach var="task" items="${importantTasks}">
					<!-- #1 Task -->
					<div class="col">
						<div class="card shadow-sm">
							<div class="card">
								<div class="card-header">
									<span style="text-transform: uppercase; font-weight: bolder"
										class="card-header-text">${task.name}</span>
								</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><strong>Mức ưu tiên:</strong>
										${task.priority}</li>
									<li class="list-group-item"><strong>Deadline:</strong>
										${task.due_date}</li>
								</ul>
							</div>

							<div class="card-body">
								<p class="card-text">${task.description}.</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="d-grid gap-2 d-md-block" data-id="${task.id}">
										<a href="/task/important-done/${task.id}"
											class="btn btn-sm btn-outline-dark"><i
											class="fas fa-check-circle"></i> Xong</a> <a
											href="/task/edit/${task.id}"
											class="btn btn-sm btn-outline-dark"><i
											class="fas fa-tools"></i> Sửa</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="../include/footer.jsp" />
</body>

</html>