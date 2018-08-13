<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<jsp:include page="/WEB-INF/views/header.jsp" flush="false" />

<div class="container">
	<h1>일정 관리</h1>
	<div>
		<h3>${user.name } 일정 작성</h3>

		<div style="color:red">
			${error }
		</div>
		<form action="${pageContext.request.contextPath }/apply" method="post">
			<table class="table">
				
				<tr>
					<td>할일:</td>
					<td><input type="text" name="PLAN" id="PLAN"></td>
				</tr>
				<tr>
					<td>날짜및 장소:</td>
					<td><input type="text" name="PLANDESC" id="PLANDESC"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="제출">
					</td>
				</tr>
			</table>

		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" flush="false" />
