<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<jsp:include page="/WEB-INF/views/header.jsp" flush="false" />

<div class="container">
<h1>일정관리</h1>
	<div>
		<h3>회원 가입 </h3>

		<div style="color:red">
			${error }
		</div>
		  <!--<form role="form">-->	
	<form method = "post" action="/calendar/join">
			<table class="table">
			  <tr>
					${msg }
					<td>ID:</td>
					<td><input type="text" name="id" value=${param.id }></td>
			  <td><!--<input type="submit" value="중복체크" id="idcheck">	 -->
					</td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<td>비밀번호 확인:</td>
					<td><input type="password" name="pw_confirm"></td>
				</tr>
				
				<tr>
					<td>Tel:</td>
					<td><input type="text" name="tel"></td>
				</tr>
			
		     	<tr>
					<td>addr:</td>
					<td><input type="text" name="addr"></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="제출" id="submit">
					</td>
				</tr>
			</table>

		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" flush="false" />

<!-- <script>
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$("#idcheck").on("click", function() {
			formObj.attr("action", "/planmanager/idcheck");
			formObj.attr("method", 'post');
			formObj.submit();
		});
		
		$("#submit").on("click", function() {
			if ('${msg}' == 'success') {
				formObj.attr("action", "/planmanager/join");
				formObj.attr("method", 'post');
				formObj.submit();	
			} else {
				alert("idcheck 을 수행하세요");
			}
			
		});
	});
</script>    -->