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
			<table style="border-collapse: 0px; border-spacing: 0px; margin: 0 auto; margin-top: 20px; width: 800px;">
				<tr>
					<td style="width: 100px; height: 50px; background: #EBE2D9; text-align: center; border-bottom: 1px solid black;">제목</td>
					<td style="border-bottom: 1px solid black;"><input type="text" name="subject" value="${dto.subject}" style="width: 650px; height: 30px; margin-left: 10px;"></td>
				</tr>
				<tr>
					<td style="width: 100px; height: 50px; background: #EBE2D9; text-align: center; border-bottom: 1px solid black;">작성자</td>
					<td style="width: 650px; height: 30px; border-bottom: 1px solid black; padding-left: 10px;">${sessionScope.member.userName}</td>
				</tr>
				<tr>
					<td style="width: 100px; height: 50px; background: #EBE2D9; text-align: center; border-bottom: 1px solid black;">내용</td>
					<td style="border-bottom: 1px solid black;"><textarea name="content" rows="12" style="width: 650px; margin-left: 10px; margin-top: 10px; margin-bottom: 10px;">${dto.content}</textarea></td>
				</tr>
				<tr>
					<td style="width: 100px; height: 50px; background: #EBE2D9; text-align: center; border-bottom: 1px solid black;">첨부</td>
					<td style="border-bottom: 1px solid black;"><input type="file" name="upload" style="width: 650px; height: 30px; margin-left: 10px;"></td>
				</tr>
				
				<c:if test="${mode == 'update'}">				
					<tr>
						<td style="width: 100px; height: 50px; background: #EBE2D9; text-align: center;">첨부 된 파일</td>
						<td style="padding-left: 10px;">
							<c:if test="${not empty dto.saveFileName}">
								<a href="<%=cp%>/bbs/deleteFile?num=${dto.num}&page=${page}"></a>
							</c:if>
							${dto.originalFileName}
						</td>
					</tr>
				</c:if>
			</table>
			
			<table style="margin: 0 auto; margin-top: 20px;">
				<tr>
					<td>
						<button type="button" onclick="sendOk();" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">${mode == 'update' ? '수정하기' : '등록하기'}</button>
						<button type="reset" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">다시입력</button>
						<button type="button" onclick="javascript:location.href='<%=cp%>/bbs/list';" style="color: white; background: #E87A54; border: none; height: 30px; width: 80px;">${mode == 'update' ? '수정취소' : '등록취소'}</button>
						
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