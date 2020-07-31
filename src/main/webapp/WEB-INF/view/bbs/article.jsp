<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
	function deleteBoard() {
		<c:if test="${sessionScope.member.userId == 'admin' || sessionScope.member.userId == dto.userId}">
			var q = "num=${dto.num}&${query}";
			var url = "<%=cp%>/bbs/delete?" + q;
			
			if(comfirm("위 자료를 삭제하시겠습니까?")) {
				location.href = url;
			}
		</c:if>
		
		<c:if test="${sessionScope.member.userId != 'admin' && sessionScope.member.userId != dto.userId}">
			alert("게시물을 삭제할 수  없습니다.");
		</c:if>
	}
	
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
</script>

<div>
	<div>
		<h3>게시글</h3>
	</div>
	
	<table>
		<tr>
			<td>제목: ${dto.subject}</td>
		</tr>
		
		<tr>
			<td>이름: ${dto.userName}</td>
		</tr>
		
		<tr>
			<td>날짜: ${dto.created}</td>
		</tr>
		
		<tr>
			<td>조회수: ${dto.hitCount}</td>
		</tr>
		
		<tr>
			<td>내용: ${dto.content}</td>
		</tr>
		
		<tr>
			<td>
				첨부:
				<c:if test="${not empty dto.saveFileName}">
					<a href="<%=cp%>/bbs/download?num=${dto.num}">${dto.originalFileName}</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				이전글:
				<c:if test="${not empty preReadDto}">
					<a href="<%=cp%>/bbs/article?${query}&num=${dto.preReadDto.num}">${preReadDto.subject}</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				다음글:
				<c:if test="${not empty nextReadDto}">
					<a href="<%=cp%>/bbs/article?${query}&num=${dto.nextReadDto.num}">${nextReadDto.subject}</a>
				</c:if>
			</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td>
				<c:if test="${sessionScope.member.userId == dto.userId}">
					<button type="button" onclick="updateBoard();">수정</button>
				</c:if>
				<c:if test="${sessionScope.member.userId == dto.userId || sessionScope.member.userId == 'admin'}">
					<button type="button" onclick="deleteBoard();">삭제</button>
				</c:if>
			</td>
			
			<td>
				<button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list?${query}';">목록</button>
			</td>
		</tr>
	</table>
</div>