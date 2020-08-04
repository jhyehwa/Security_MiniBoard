<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" type="text/css">

<div class="header">
    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
</div>

<div class="container">
	<div class="body-container" style="width: 800px; margin-top: 50px;">
		<div style="font-weight: bold; font-size: 20px; text-align: left;">
			<span>| 게시판</span>&nbsp;<span style="font-size: 18px; font-weight: normal;">${dataCount}개 (${page}/${total_page} 페이지)</span>
		</div>
	
		<table style="border-collapse: 0px; border-spacing: 0px; margin: 0 auto; margin-top: 20px;">
			<tr align="center">
				<td style="border-bottom: 1px solid #A6522B; width: 50px; padding-bottom: 5px;">번호</td>
				<td style="border-bottom: 1px solid #A6522B; width: 200px; padding-bottom: 5px;">제목</td>
				<td style="border-bottom: 1px solid #A6522B; width: 150px; padding-bottom: 5px;">작성자</td>
				<td style="border-bottom: 1px solid #A6522B; width: 150px; padding-bottom: 5px;">작성일</td>
				<td style="border-bottom: 1px solid #A6522B; width: 100px; padding-bottom: 5px;">조회수</td>
				<td style="border-bottom: 1px solid #A6522B; width: 100px; padding-bottom: 5px;">첨부</td>
			</tr>
			
			<c:forEach var="dto" items="${list}">
				<tr align="center">
					<td style="padding-top: 10px; padding-bottom: 10px;">${dto.listNum}</td>
					<td style="padding-top: 10px; padding-bottom: 10px;">
						<a href="${articleUrl}&num=${dto.num}">${dto.subject}</a>
					</td>
					<td style="padding-top: 10px; padding-bottom: 10px;">${dto.userName}</td>
					<td style="padding-top: 10px; padding-bottom: 10px;">${dto.created}</td>
					<td style="padding-top: 10px; padding-bottom: 10px;">${dto.hitCount}</td>
					<td style="padding-top: 10px; padding-bottom: 10px;">
						<c:if test="${not empty dto.saveFileName}">
							<a href="<%=cp%>/bbs/download?num=${dto.num}">파일</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<table style="margin: 0 auto; margin-top: 20px;">
			<tr>
				<td>${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}</td>
			</tr>
		</table>
		
		<table style="margin: 0 auto; margin-top: 20px;">
			<tr>
				<td><button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list';" style="background: #E87A54; border: none; color: white; height: 30px; width: 100px;">새로고침</button></td>
				<td><button type="button" onclick="javascript:location.href='<%=cp%>/bbs/created';" style="margin-left: 15px; background: #E87A54; border: none; color: white; height: 30px; width: 100px;">게시글 등록</button></td>
			</tr>
		</table>
	</div>
</div>