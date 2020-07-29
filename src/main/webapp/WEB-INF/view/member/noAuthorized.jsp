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
</head>
<body>
	<div class="header">
    	<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
	</div>
	
	<div>
		<div>
			<div>
				<div>
					<span><strong>경고</strong></span>
				</div>
				
				<div>
					<div>
						해당 정보를 접근 할 수 있는 권한이 없습니다.
					</div>
					<div>
						<button type="button" onclick="javascript:location.href='<%=cp%>/';">메인으로 이동</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>