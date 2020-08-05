<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" type="text/css">

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

<div class="container">
	<div class="body-container" style="width: 800px; margin-top: 50px;">
		<div style="font-weight: bold; font-size: 25px; text-align: center; border-bottom: 1px solid black; padding-bottom: 15px;">
			<span>로그인</span>
		</div>
		
		<form name="loginForm" method="post">
			<table style="margin: 0 auto; margin-top: 40px;">
				<tr>
					<td>
						<label style="font-size: 20px;">아이디&nbsp;&nbsp;&nbsp; </label>
						<input type="text" name="userId" style="height: 30px; margin-bottom: 5px;">
					</td>
				</tr>
				<tr>
					<td>
						<label style="font-size: 20px;">비밀번호 </label>
						<input type="text" name="userPwd" style="height: 30px;">
					</td>
				</tr>
				<tr>
					<td style="padding-top: 20px;">
						<button type="button" onclick="sendLogin();" style="background: #E87A54; border: none; color: white; height: 30px; width: 100%; border-radius: 30px; font-size: 18px;">로그인</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>