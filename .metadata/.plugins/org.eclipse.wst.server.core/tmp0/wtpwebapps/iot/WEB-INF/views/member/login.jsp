<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#naver { 
	background: url('imgs/naver_login.png') center; 
	background-size: cover;
}
#kakao { 
	background: url('imgs/kakao_login.png') center; 
	background-size: cover;
}

</style>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='imgs/hanul.logo.png'></a>
	<div class='box'>
	<ul>
		<li><input type='text' class='chk' id='userid' placeholder="아이디"></li>
		<li><input type='password' class='chk' id='userpw' placeholder="비밀번호"></li>
		<li><input type='button' value='로그인' onclick='fn_login()'></li>
		<li><hr></li>
		<li><input type='button' id='naver'></li>
		<li><input type='button' id='kakao'></li>
	</ul>
	</div>
	<div><a href='findpw'>비밀번호찾기</a></div>
</div>

<script>
$('#naver, #kakao').click(function(){
	location =  $(this).attr('id')+ 'Login';
});

$('#userpw').keyup(function(e){
	if( e.keyCode==13 )
		fn_login();
});

function fn_login(){
	if( ! emptyCheck() ) return;
	
	$.ajax({
		//type: 'post'
		url: 'iotLogin',
		data: { id:$('#userid').val(), pw:$('#userpw').val() },
		success: function( response ){
			console.log( response )
			if( response ){
				location = '<c:url value="/"/>';
			}else{
				alert('아이디나 비밀번호가 일치하지 않습니다!');
			}
		}
	});
}
</script>

</body>
</html>






