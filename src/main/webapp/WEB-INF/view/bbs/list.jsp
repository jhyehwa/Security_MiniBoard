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
					<td>${dto.hitcount}</td>
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
				<td>
					<form name="searchForm" action="<%=cp%>/bbs/list" method="post">
						<select name="condition">
							<option value="all" ${condition == "all" ? "selected = 'selected'" : ""}>모두</option>
							<option value="subject" ${condition == "subject" ? "selected = 'selected'" : ""}>제목</option>
							<option value="content" ${condition == "content" ? "selected = 'selected'" : ""}>내용</option>
							<option value="userName" ${condition == "userName" ? "selected = 'selected'" : ""}>작성자</option>
							<option value="created" ${condition == "created" ? "selected = 'selected'" : ""}>등록일</option>
						</select>
						<input type="text" name="keyword" value="${keyword}">
						<button type="button" onclick="searchList()">검색</button>
					</form>
				</td>
				<td>
					<button type="button" onclick="javascript:location.href='<%=cp%>/bbs/created';">게시글 등록</button>
				</td>
			</tr>
		</table>
	</div>
</div>