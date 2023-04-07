<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table tr td { text-align: left }
</style>
</head>
<body>
<h3>신규사원등록</h3>
<form method='post' action='insert.hr'>
<table class='w-px600'>
<tr><th class='w-px140'>사원명</th>
	<td><input type='text' class='w-px160' placeholder="성" name='last_name' >
		<input type='text' class='w-px160' placeholder="명" name='first_name' >
	</td>
</tr>
<tr><th>이메일</th>
	<td><input type='text' name='email'  ></td>
</tr>
<tr><th>전화번호</th>
	<td><input type='text' name='phone_number' ></td>
</tr>
<tr><th>급여</th>
	<td><input type='text' name='salary' ></td>
</tr>
<tr><th>입사일자</th>
	<td><input type='text' readonly class='date' name='hire_date' ></td>
</tr>
<tr><th>부서</th>
	<td><select name='department_id' class='w-px250'>
		<option value='-1'>부서선택</option>
		<c:forEach items='${departments}' var='d'>
		<option value='${d.department_id}'>${d.department_name}</option>
		</c:forEach>		
		</select>
	</td>
</tr>
<tr><th>업무</th>
	<td><select name='job_id' class='w-px300'>
		<c:forEach items='${jobs}' var='j'>		
		<option value='${j.job_id}'>${j.job_title }</option>
		</c:forEach>		
		</select>
	</td>
</tr>
<tr><th>관리자</th>
	<td><select name='manager_id' class='w-px300'>
		<option value='-1'>관리자선택</option>
		<c:forEach items='${managers}' var='m'>
		<option value='${m.employee_id}'>${m.name}</option>
		</c:forEach>
		</select>
	</td>
</tr>
</table>
</form>

<div class='btnSet'>
<a class='btn-fill btn-save'>저장</a>
<a class='btn-empty' href='list.hr'>취소</a>
</div>
<script>
$(function(){
	$('[name=hire_date]').val( new Date().toJSON().substring(0, 10) );
	
});

$('.btn-save').on('click', function(){
	$('form').submit();	
});
</script>

</body>
</html>



