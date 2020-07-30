<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
	function sendLogin() {
		var f = document.loginForm;
		
		f.action = "<%=cp%>/member/login_check";
		f.submit();
	}
</script>

<div class="header">
    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
</div>

<div>
	<div>
		<span>로그인</span>
	</div>
	
	<form name="loginForm" method="post">
		<table>
			<tr>
				<td>
					<label>아이디</label>
					<input type="text" name="userId">
				</td>
			</tr>
			<tr>
				<td>
					<label>패스워드</label>
					<input type="text" name="userPwd">
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" onclick="sendLogin();">로그인</button>
				</td>
			</tr>
		</table>
	</form>
</div>