<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<jsp:include page="/WEB-INF/views/header.jsp" flush="false" />
<% request.setCharacterEncoding("utf-8");%>
<style>
#logindiv{
  float:left;
  position:fixed;
}

</style>
		<h1>일정관리</h1>
		
		
		<div id='logindiv' class="container">
		<h3>LOGIN</h3>
		<form action='${pageContext.request.contextPath }/login' method='post'>
			<input type="hidden" name="orgReqPath" value="${orgReqPath }" />
			<div style="color: red">${error}</div>
			ID &nbsp;: <input type='text' name='id'><br />
	  		PW: <input type='password' name='pw'><br />
	            <input type='submit' value='Login' />
		</form>
	</div>

<jsp:include page="/WEB-INF/views/footer.jsp" flush="false" />
