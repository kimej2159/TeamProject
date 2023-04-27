<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>고객정보</h3>
	<table class='w-px600'>
	<colgroup>
		<col width="140px">
		<col>
	</colgroup>
	<tr><th>고객명</th>
		<td>${vo.name }</td>
	</tr>
	<tr><th>성별</th>
		<td>${vo.gender }</td>
	</tr>
	<tr><th>이메일</th>
		<td>${vo.email }</td>
	</tr>
	<tr><th>전화번호</th>
		<td>${vo.phone }</td>
	</tr>
	</table>

	<div class='btnSet'>
		<a class='btn-fill' href='list.cu'>고객목록</a>
		<a class='btn-fill' href='modify.cu?id=${vo.id}'>정보수정</a>
		<a class='btn-empty' onclick='fn_delete()'>정보삭제</a>
	</div>

<script>
function fn_delete(){
	if( confirm('[ ${vo.name} ] 정말 삭제?') ){
	 	location.href='delete.cu?id=${vo.id}'
	}
}
</script>

</body>
</html>