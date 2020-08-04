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
	function sendOk() {
		var f = document.createdForm;
		
		f.action = "<%=cp%>/bbs/${mode}";
		
		f.submit();
	}
</script>

<div class="header">
    <jsp:include page="/WEB-INF/view/layout/header.jsp"/>
</div>

<div class="container">
	<div class="body-container" style="width: 800px; margin-top: 50px;">
		<div style="font-weight: bold; font-size: 20px; text-align: left;">
			<span>| 글 작성</span>
		</div>

		<form name="createdForm" method="post" enctype="multipart/form-data">
			<table style="border-collapse: 0px; border-spacing: 0px; margin: 0 auto; margin-top: 20px;">
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