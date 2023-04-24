<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>
<link rel='stylesheet' href='css/member.css'>
</head>
<body>
<div class='center'>
	<div class="navbar justify-content-center">
	<a href='<c:url value="/"/>'><img src='imgs/HanulFit-black.png'></a>
	</div>
	<div class='box'>
		<form method='post' action='change'>
		<ul>
			<li><input class='chk' type='password' name='pw'
						 	placeholder="새 비밀번호">
				<div class='alert alert-success valid'>비밀번호를 입력하세요<br>(영문대/소문자,숫자 모두 포함)</div>
			</li> 
			<li><input class='chk' type='password' name='pw_ck' 
							placeholder="새 비밀번호 확인">
				<div class='alert alert-success invalid'>비밀번호를 다시 입력하세요</div>
			</li>
			<li><input type='reset' value='다시입력'></li>
			<li><input type='button' value='비밀번호 변경' 
					onclick='if( emptyCheck() ) $("form").submit()'></li>
		</ul>
		</form>
	</div>
</div>
	
<script src='js/member_check.js?<%=new java.util.Date()%>'></script>
<script>
$('[type=password]').keyup(function(){
	var status = member.tag_status( $(this) );
	$(this).siblings('div').removeClass('valid invalid')
		.addClass(status.code).text(status.desc);
	
});
</script>
</body>
</html>