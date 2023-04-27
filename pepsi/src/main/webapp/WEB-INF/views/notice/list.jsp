<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 목록</title>
</head>
<body>
<!-- Page Header-->
 <jsp:include page="/WEB-INF/views/notice/notice_header.jsp">
	<jsp:param value="공지사항" name="subtitle"/>
</jsp:include>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 ">
                
                
    <div id='list-top' class='w-px1200'>
		<ul>
			<!-- 로그인이 된 경우에만 새 글 쓰기 가능 -->
			<c:if test="${not empty loginInfo}">
			<li><a class="btn btn-primary" href="" role="button">새글쓰기</a></li>
			</c:if>
		</ul>
	</div>

	<table class='table table-hover'>
	<colgroup>
		<col width='100px'>
		<col width='300px'>
		<col width='100px'>
		<col width='100px'>
	</colgroup>
	<tr><th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:forEach items='${page.list}' var='vo'>
	<tr><td>${vo.no}</td>
		<td>${vo.title}</td>
		<td>${vo.name}</td>
		<td>${vo.writedate}</td>
	</tr>
	</c:forEach>
	</table>
                        
                        
            </div>
        </div>
    </div>
</article>

</body>
</html>