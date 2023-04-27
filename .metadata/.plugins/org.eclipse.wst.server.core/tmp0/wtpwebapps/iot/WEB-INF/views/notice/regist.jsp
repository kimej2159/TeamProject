<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글쓰기</h3>
<form method='post' action='insert.no' enctype='multipart/form-data'>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='title' class='full chk' title='제목'></td>
</tr>
<tr><th>내용</th>
	<td><textarea name='content' class='full chk' title='내용'></textarea>
	</td>
</tr>
<tr><th>첨부파일</th>
	<td class='txt-left'>
		<div class='align'>
		<label>
			<input type='file' name='file' id='attach-file'>
			<a><i class="font-img-b fa-solid fa-file-circle-plus"></i></a>
		</label>
		<span id='file-name'></span>
		<span id='preview'></span>
		<a id='delete-file'><i class="font-img-r fa-regular fa-trash-can"></i></a>
		</div>
	</td>
</tr>
</table>
<input type='hidden' name='writer' value='${loginInfo.id}'>
</form>
<div class='btnSet'>
<a class='btn-fill btn-save'>저장</a>
<a class='btn-empty' href='list.no'>취소</a>
</div>

<script>
$('.btn-save').click(function(){
	if( emptyCheck() ){
		$('form').submit();
	}
});
</script>

</body>
</html>