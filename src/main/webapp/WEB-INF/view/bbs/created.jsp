<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
	function sendOk() {
		var f = document.createdForm;
		
		f.action = "<%=cp%>/bbs/${mode}";
		
		f.submit();
	}
</script>

<div>
	<div>
		<h3>게시판</h3>
	</div>
	
	<div>
		<form name="createdForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${dto.subject}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${sessionScope.member.userName}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="12">${dto.content}</textarea></td>
				</tr>
				<tr>
					<td>첨부</td>
					<td><input type="file" name="upload"></td>
				</tr>
				
				<c:if test="${mode == 'update'}">				
					<tr>
						<td>첨부 된 파일</td>
						<td>
							<c:if test="${not empty dto.saveFileName}">
								<a href="<%=cp%>/bbs/deleteFile?num=${dto.num}&page=${page}"></a>
							</c:if>
							${dto.originalFileName}
						</td>
					</tr>
				</c:if>
			</table>
			
			<table>
				<tr>
					<td>
						<button type="button" onclick="sendOk();">${mode == 'update' ? '수정하기' : '등록하기'}</button>
						<button type="reset">다시입력</button>
						<button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list';">${mode == 'update' ? '수정취소' : '등록취소'}</button>
						
						<c:if test="${mode == 'update'}">
							<input type="hidden" name="num" value="${dto.num}">
							<input type="hidden" name="saveFileName" value="${dto.saveFileName}">
							<input type="hidden" name="originalFileName" value="${dto.originalFileName}">
							<input type="hidden" name="page" value="${page}">
						</c:if>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>