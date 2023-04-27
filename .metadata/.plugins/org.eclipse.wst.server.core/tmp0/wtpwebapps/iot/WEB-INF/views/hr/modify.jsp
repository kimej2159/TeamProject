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
<h3>사원정보수정</h3>
<form method='post' action='update.hr'>
<input type='hidden' name='employee_id' value='${vo.employee_id}'>
<table class='w-px600'>
<tr><th class='w-px140'>사번</th>
	<td>${vo.employee_id }</td>
</tr>
<tr><th>사원명</th>
	<td><input type='text' class='w-px160' placeholder="성" name='last_name' value='${vo.last_name}'>
		<input type='text' class='w-px160' placeholder="명" name='first_name' value='${vo.first_name}'>
	</td>
</tr>
<tr><th>이메일</th>
	<td><input type='text' name='email' value='${vo.email }' ></td>
</tr>
<tr><th>전화번호</th>
	<td><input type='text' name='phone_number' value='${vo.phone_number }' ></td>
</tr>
<tr><th>급여</th>
	<td><input type='text' name='salary' value='${vo.salary }' ></td>
</tr>
<tr><th>입사일자</th>
	<td><input type='text' readonly class='date' name='hire_date' value='${vo.hire_date }' ></td>
</tr>
<tr><th>부서</th>
	<td><select name='department_id' class='w-px250'>
		<option value='-1'>부서선택</option>
		<c:forEach items='${departments}' var='d'>
		<option ${vo.department_id eq d.department_id ? 'selected' : ''}  value='${d.department_id}'>${d.department_name}</option>
		</c:forEach>		
		</select>
	</td>
</tr>
<tr><th>업무</th>
	<td><select name='job_id' class='w-px300'>
		<c:forEach items='${jobs}' var='j'>		
		<option ${vo.job_id eq j.job_id ? 'selected' : ''} value='${j.job_id}'>${j.job_title }</option>
		</c:forEach>		
		</select>
	</td>
</tr>
</table>
</form>

<div class='btnSet'>
<a class='btn-fill btn-save'>저장</a>
<a class='btn-empty' href='javascript:history.go(-1)'>취소</a>
<%-- <a class='btn-empty' href='info.hr?id=${vo.employee_id }'>취소</a> --%>
</div>
<script>
$('.btn-save').on('click', function(){
	$('form').submit();	
});
</script>

</body>
</html>



