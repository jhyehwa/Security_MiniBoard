<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" type="text/css">

</head>
<body>
	<div class="header">
	    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
	</div>

	<div>
		<div>
			<span>로그인</span>
		</div>
		
		<form method="post">
			<table>
				<tr>
					<td>
						<label>아이디</label>
						<input type="text">
					</td>
				</tr>
				<tr>
					<td>
						<label>패스워드</label>
						<input type="text">
					</td>
				</tr>
				<tr>
					<td>
						<label>로그인</label>
						<input type="text">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>