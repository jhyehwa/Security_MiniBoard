<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring</title>

<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/resource/js/util.js"></script>
</head>

<body>

	<div class="header">
		<jsp:include page="/WEB-INF/view/layout/header.jsp" />
	</div>

	<div class="container">
		<div class="body-container" style="width: 700px; margin-top: 20px;">
			메인 화면 입니다.
		</div>
	</div>

	<div class="footer">
		<jsp:include page="/WEB-INF/view/layout/footer.jsp" />
	</div>

</body>
</html>