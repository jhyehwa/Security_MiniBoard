<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
	function memberOk() {
		var f = document.memberForm;
		
		f.action = "<%=cp%>/member/${mode}";
		
		f.submit();
	}
</script>

<div>
	<div>
		<h3>${mode == "member" ? "회원가입" : "회원정보수정"}</h3>
	</div>
	
	<div>
		<form name="memberForm" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userId" value="${dto.userId}"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="userPwd"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="userName" value="${dto.userName}"></td>
				</tr>
			</table>
			
			<table>
				<tr>
					<td>
						<button type="button" name="sendButton" onclick="memberOk();">${mode == "member" ? "회원가입" : "정보수정"}</button>
						<button type="reset">다시입력</button>
						<button type="button" onclick="javascript:location.href='<%=cp%>/';">${mode == "member" ? "가입취소" : "수정취소"}</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>