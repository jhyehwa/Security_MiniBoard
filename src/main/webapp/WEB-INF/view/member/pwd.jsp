<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

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

<div>
	<div>
		<div>
			<span>비밀번호 확인</span>
		</div>
		
		<form name="pwdForm" method="post">
			<table>
				<tr>
					<td>정보 보호를 위해 비밀번호를 다시 한 번 입력 해 주세요.</td>				
				</tr>
				
				<tr>
					<td><input type="text" name="userId" value="${sessionScope.member.userId}" readonly="readonly"></td>
				</tr>
				
				<tr>
					<td>
						<label>비밀번호</label>
						<input type="password" name="userPwd">
					</td>
				</tr>
				
				<tr>
					<td>
						<button type="button" onclick="sendOk();">확인</button>
						<input type="hidden" name="mode" value="${mode}">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>