<%@ page language="java" pageEncoding="utf-8"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- FavIcon -->
<link rel="shortcut icon" href="/static/icon.ico" type="image/x-icon">
<link rel="icon" href="/static/icon.ico" type="image/x-icon">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
	integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp"
	crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
	integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/"
	crossorigin="anonymous"></script>
<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/726e1f16be.js"
	crossorigin="anonymous"></script>
<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Custom Style -->
<link href="/static/css/task-ms.css" rel="stylesheet">
<!-- Custom Script -->
<script>
	$(document).ready(function() {
		$(".btn-triggered").click(function() {
			var id = $(this).closest("div").attr("data-id")
			$.ajax({
				url:"/task/important/" + id,
				success:function(response) {
					if(response) {
 					alert("Đã đánh dấu task quan trọng")						
					} else {
						alert("Đã đánh dấu rồi")
					}
				}
			})
		});
	});
</script>
