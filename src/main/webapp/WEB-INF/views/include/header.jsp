<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<header class="p-3 bg-dark text-white">
	<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/task/mytask"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
				<i class="fas fa-dice-d20 fa-3x"></i> <span class="fs-4">&nbsp;
					TaskMS</span>
			</a>

			<form action="/task/search" method="post"
				class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
				<input name="keyword" value="${param.keyword}" type="search"
					class="form-control form-control-dark" placeholder="Tìm kiếm..."
					aria-label="Search">
			</form>

			<c:choose>
				<c:when test="${empty sessionScope.account}">
					<div class="text-end">
						<a href="/user/login" class="btn btn-outline-light me-2">Đăng
							nhập</a> <a href="/user/register" class="btn btn-warning">Đăng ký</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="dropdown">
						<a class="btn btn-warning dropdown-toggle" href="#"
							role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
							aria-expanded="false"> Chào ${account.firstName} <i class="fas fa-user-cog"></i></a>

						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li><a class="dropdown-item" href="/user/update">Quản lý thông tin</a></li>
							<li><a class="dropdown-item" href="/user/change">Đổi mật khẩu</a></li>
							<li><a class="dropdown-item" href="/user/logout">Đăng xuất</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>