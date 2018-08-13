<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<jsp:include page="/WEB-INF/views/header.jsp" flush="false" />

	<div class="container">
		<h1>일정관리</h1>
		<div>
			<h3>
				${name } Profile
				
			</h3>
			<hr>
			
		</div>
	</div>

<script>
	if ('${param.msg}' == 'success') {
		alert("일정 작성 완료");
	}
</script>
<jsp:include page="/WEB-INF/views/footer.jsp" flush="false" />
