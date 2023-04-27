<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>고객정보수정</h3>
	<form method="post" action='update.cu'>
	<table class='w-px600'>
	<colgroup>
		<col width="140px">
		<col>
	</colgroup>
	<tr><th>고객명</th>
		<td><input type='text' name='name' value='${vo.name }'></td>
	</tr>
	<tr><th>성별</th>
		<td>
			<label><input type='radio' ${vo.gender eq '남' ? 'checked' : ''} name='gender' value='남'>남</label>
			<label><input type='radio' ${vo.gender eq '여' ? 'checked' : ''} name='gender' value='여'>여</label>
		</td>
	</tr>
	<tr><th>이메일</th>
		<td><input type='text' name='email' value='${vo.email }'></td>
	</tr>
	<tr><th>전화번호</th>
		<td><input type='text' name='phone' value='${vo.phone }'></td>
	</tr>
	</table>
	<input type='hidden' name='id' value='${vo.id}'>
	</form>

	<div class='btnSet'>
		<a class='btn-fill' onclick="$('form').submit()">저장</a>
		<a class='btn-empty' href='info.cu?id=${vo.id}'>취소</a>
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