<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<div>
	<div>
		<h3>게시글 수정</h3>
	</div>
	
	<table>
		<tr>
			<td>${dto.subject}</td>
		</tr>
	</table>
</div>