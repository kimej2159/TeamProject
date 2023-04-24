<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>질문글 새글쓰기</h3>
<form method='post' entype='multipart/form-data' action='insert.qu'>
<input type='hidden' name='writer' value='${loginInfo.id}'>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='title' title='제목' class='chk full'></td>
</tr>
<tr><th>내용</th>
	<td><textarea name='content' title='내용' class='chk full'></textarea></td>
</tr>

</table>
</form>
<div class='btnSet'>
<a class='btn-fill btn-save'>저장</a>
<a class='btn-empty' href='list.qu'>취소</a>
</div>
<script>
$('.btn-save').on('click', function(){
	if( emptyCheck() ) $('form').submit();
});
</script>
</body>
</html>