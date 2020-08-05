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
	function sendOk() {
		var f = document.pwdForm;
		
		var str = f.userPwd.value;
		if(!str) {
			alert("비밀번호를 입력해주세요.");
			f.userPwd.focus();
			return;
		}
		
		f.action = "<%=cp%>/member/pwd";
		
		f.submit();
	}
</script>

<div class="header">
    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
</div>

<div class="container">
	<div class="body-container" style="width: 800px; margin-top: 50px;">
		<div style="font-weight: bold; font-size: 25px; text-align: center; border-bottom: 1px solid black; padding-bottom: 15px;">
			<span>비밀번호 확인</span>
		</div>
		
		<form name="pwdForm" method="post">
			<table style="width: 800px;">
				<tr style="float: left; margin-top: 20px; margin-bottom: 20px;">
					<td style="font-size: 18px; margin-top: 20px;">| 정보 보호를 위해 비밀번호를 다시 한 번 입력 해 주세요.</td>				
				</tr>
				
				<tr style="text-align: center;">
					<td><input type="text" name="userId" value="${sessionScope.member.userId} 님" readonly="readonly" style="border: none; font-size: 20px; margin-bottom: 10px; width: 300px; text-align: center; height: 50px;"></td>
				</tr>
				
				<tr>
					<td style="text-align: center;">
						<label>비밀번호</label>
						<input type="password" name="userPwd" style="font-size: 18px;">
					</td>
				</tr>
				
				<tr>
					<td style="text-align: center;">
						<button type="button" onclick="sendOk();" style="background: #E87A54; border: none; color: white; height: 30px; width: 300px; border-radius: 30px; font-size: 18px; margin-top: 20px;">확인</button>
						<input type="hidden" name="mode" value="${mode}">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>