<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<div>
	<div>
		<h3>게시글 등록</h3>
	</div>
	
	<div>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>첨부</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>첨부 된 파일</td>
					<td><input type="text"></td>
				</tr>
			</table>
			
			<table>
				<tr>
					<td>
						<button type="button">등록하기</button>
						<button type="reset">다시입력</button>
						<button type="button">등록취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>