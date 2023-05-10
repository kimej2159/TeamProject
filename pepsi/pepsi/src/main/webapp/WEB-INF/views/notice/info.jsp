<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 확인</title>
<style>
.gray{
	color: gray; font-size: 15px;
}
.h1title{font-size: 26px;}
table td { text-align: left; }
.align{display: flex; align-items: center;}
.align * { margin-right: 5px;}
.btnSet{ 	margin: 20px auto; }
#preview img, .preview img {max-height: 30px;}
.btn {	color: #ffffff;}
</style>
</head>
<body>

<!-- Page Header-->
 <jsp:include page="/WEB-INF/views/notice/notice_header.jsp">
	<jsp:param value="공지사항" name="subtitle"/>
</jsp:include>
<!-- 			<h1 class='h1title'>공지사항 새글쓰기</h1> -->
			
<!-- Post Content-->
<article>
	<div class="container px-4 px-lg-5">
	<table class='table'>
	<colgroup>
		<col width='140px'>
		<col>
		<col width='140px'>
		<col width='140px'>
		<col width='100px'>
		<col width='100px'>
	</colgroup>
<tr><th>제목</th>
	<td colspan='5'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th>작성일자</th>
	<td>${vo.writedate}</td>
	<th>조회수</th>
	<td>${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5'>${fn:replace( vo.content, crlf, '<br>')}</td>
</tr>
<tr><th>첨부파일</th>
	<td colspan='5'>
	<!--  
	${vo.filename}
	<c:if test='${ not empty vo.filename}'>
	<a href='download.no?id=${vo.id}'><i class="fa-solid fa-file-arrow-down"></i></a>
	</c:if>
	<c:if test='${ empty vo.filename}'>
	<span class='gray'>첨부파일이 없습니다.</span>
	</c:if>
	-->
	
	<c:forEach items='${vo.fileInfo}' var='f'>
	<div class='align'>
		<span>${f.filename}
			<a href='download.bo?id=${f.id}'><i class="fa-solid fa-file-arrow-down"></i></a>
		</span>
	</div>
	</c:forEach>
	<c:if test='${ empty vo.fileInfo}'>
	<span class='gray'>첨부파일이 없습니다.</span>
	</c:if>
	</td>
</tr>
</table>

<div class='btnSet text-center'>
<a class='btn btn-primary' href='list.no'>목록으로</a>
<c:if test="${vo.writer eq loginInfo.id}">
<a class='btn btn-primary' id='modify' href='modify.no?id=${vo.id}'>정보수정</a>
<a class='btn btn-primary' id='delete' href='delete.no?id=${vo.id}'>정보삭제</a>
</c:if>
</div>

</div>
</article>
		
<script>

</script>
	
</body>
</html>