<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리 화면</title>
</head>
<body>

<div class='center'>
	<div class="navbar justify-content-center">
	<a href='<c:url value="/"/>'><img src='imgs/HanulFit-black.png'></a>
	</div>
	<div class='box'>
	<ul>
		<li><input type='text' class='chk' id='userid' placeholder="아이디"></li>
		<li><input type='password' class='chk' id='userpw' placeholder="비밀번호"></li>
		<li><input type='button' value='로그인' onclick='fn_login()'></li>
		
	</ul>
	</div>
	<div><a href='findpw' >비밀번호 찾기</a></div>
</div>
<script>


//id 입력 후 enter키로 pw칸으로 넘어가기
$('#userid').keyup(function(e){
	if( e.keyCode==13 )
		userpw;
});



// pw입력 후 enter키로 로그인 처리 
$('#userpw').keyup(function(e){
	if( e.keyCode==13 )
		fn_login();
});


function fn_login(){
	if( ! emptyCheck() ) return;
	
	$.ajax({
		//type: 'post'
		url: 'pepsiLogin',
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






