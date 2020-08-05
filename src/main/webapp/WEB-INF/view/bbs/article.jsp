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
	function updateBoard() {
		<c:if test="${sessionScope.member.userId == dto.userId}">
			var q = "num=${dto.num}&page=${page}";
			var url = "<%=cp%>/bbs/update?" + q;
	
			location.href=url;
		</c:if>
	
		<c:if test="${sessionScope.member.userId!=dto.userId}">
			alert("게시물을 수정할 수  없습니다.");
		</c:if>
	}
	
	function deleteBoard(num) {
		<c:if test="${sessionScope.member.userId == 'admin' || sessionScope.member.userId == dto.userId}">
			if(confirm("위 자료를 삭제 하시겠습니까 ?")) {
				var url="<%=cp%>/bbs/delete?num="+num+"&${query}";
				location.href=url;
			}
		</c:if>
	}
</script>

<div class="header">
    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
</div>

<div class="container">
	<div class="body-container" style="width: 800px; margin-top: 50px;">
		<div style="font-weight: bold; font-size: 20px; text-align: left;">
			<span>| 게시글</span>
		</div>
	
		<table style="border-collapse: 0px; border-spacing: 0px; margin: 0 auto; margin-top: 20px; width: 800px;">
			<tr>
				<td colspan="1" style="height: 50px; background: #EBE2D9; border-bottom: 1px solid black; text-align: center; border-top: 1px solid black;">제목</td>
				<td colspan="5" style="border-bottom: 1px solid black; padding-left: 10px; border-top: 1px solid black;">${dto.subject}</td>
			</tr>
			
			<tr style="height: 50px;">
				<td style="border-bottom: 1px solid black; text-align: center; background: #EBE2D9;">작성자</td>
				<td style="border-bottom: 1px solid black; padding-left: 10px;">${dto.userName}</td>
				<td style="border-bottom: 1px solid black; text-align: center; background: #EBE2D9;">날짜</td>
				<td style="border-bottom: 1px solid black; padding-left: 10px;">${dto.created}</td>
				<td style="border-bottom: 1px solid black; text-align: center; background: #EBE2D9;">조회수</td>
				<td style="border-bottom: 1px solid black; padding-left: 10px;">${dto.hitCount}</td>
			</tr>
			
			<tr>
				<td colspan="6" style="height: 50px; background: #EBE2D9; text-align: center; border-bottom: 1px solid black;">내용</td>
			</tr>
			
			<tr>
				<td colspan="6" style="height: 300px; border-bottom: 1px solid black;">${dto.content}</td>
			</tr>
			
			<tr>
				<td colspan="1" style="height: 50px; border-bottom: 1px solid black; background: #EBE2D9; text-align: center;">첨부</td>
				<td colspan="5" style="border-bottom: 1px solid black; padding-left: 10px; width: 450px;">
					<c:if test="${not empty dto.saveFileName}">
						<a href="<%=cp%>/bbs/download?num=${dto.num}">${dto.originalFileName}</a>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" style="height: 50px; border-bottom: 1px solid black; background: #EBE2D9; text-align: center;">이전글</td>
				<td colspan="5" style="border-bottom: 1px solid black; padding-left: 10px;">
					<c:if test="${not empty preReadBoard}">
						<a href="<%=cp%>/bbs/article?${query}&num=${preReadBoard.num}">${preReadBoard.subject}</a>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" style="height: 50px; border-bottom: 1px solid black; background: #EBE2D9; text-align: center;">다음글</td>
				<td colspan="5" style="border-bottom: 1px solid black; padding-left: 10px;">
					<c:if test="${not empty nextReadBoard}">
						<a href="<%=cp%>/bbs/article?${query}&num=${nextReadBoard.num}">${nextReadBoard.subject}</a>
					</c:if>
				</td>
			</tr>
		</table>
		
		<table style="float: right; margin-top: 20px;">
			<tr>
				<td>
					<c:if test="${sessionScope.member.userId == dto.userId}">
						<button type="button" onclick="updateBoard();" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">수정</button>
					</c:if>
					<c:if test="${sessionScope.member.userId == dto.userId || sessionScope.member.userId == 'admin'}">
						<button type="button" onclick="deleteBoard(${dto.num});" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">삭제</button>
					</c:if>
					<button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list?${query}';" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">목록</button>
				</td>
			</tr>
		</table>
	</div>
</div>