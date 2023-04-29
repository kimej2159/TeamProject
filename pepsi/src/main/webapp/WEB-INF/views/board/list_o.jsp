<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{	border-collapse: collapse;	margin: 0 auto;	}
table th, table td {padding: 6px 10px;	border: 1px solid #b0b0b0}
table th {	background-color: #f6f6f6 }
table tr {	height: 46px}
table.tb-list tr{	height: 38px}
a.btn-empty{background-color:#fff; color: 3367d6}
a.btn-fill{background-color:#3367d6; color: #fff}
</style>

</head>
<body>
<div class='container'>

<div id='list-top' class='w-px1200'>
<ul>
	<!-- 로그인이 된 경우에만 새 글 쓰기 가능 -->
	<c:if test="${not empty loginInfo}">
	<li><a class='btn-fill' href='new.bo'>새글쓰기</a></li>
	</c:if>
</ul>
</div>

<table class='w-px1200 table table-hover'>
<colgroup>
	<col width='100px'>
	<col>
	<col width='140px'>
	<col width='140px'>
</colgroup>
<tr><th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
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
</body>
</html>