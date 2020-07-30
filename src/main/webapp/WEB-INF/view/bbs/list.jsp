<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<div>
	<div>
		<h3>게시판</h3>
	</div>
	
	<div>
		<table>
			<tr>
				<td>${dataCount}개 (${page}/${total_page} 페이지)</td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>첨부</td>
			</tr>
			
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.listNum}</td>
					<td>
						<a href="${articleUrl}&num=${dto.num}">${dto.subject}</a>
					</td>
					<td>${dto.userName}</td>
					<td>${dto.created}</td>
					<td>${dto.hitCount}</td>
					<td>
						<c:if test="${not empty dto.saveFileName}">
							<a href="<%=cp%>/bbs/download?num=${dto.num}"></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<table>
			<tr>
				<td><button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list';">새로고침</button></td>
				<td><button type="button" onclick="javascript:location.href='<%=cp%>/bbs/created';">게시글 등록</button></td>
			</tr>
		</table>
	</div>
</div>