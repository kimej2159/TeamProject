<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>로그인 처리 화면</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
#under {
 color: gray;
}
#userid {
border-radius: 5px;
}

#origin {
	border-radius: 5px;
	border: none;
	background-color: black;
}
#naver { 
/* 	background: url('imgs/naver_login.png') center;  */
	background-color: #03c75a;
	background-size: cover;
	border-radius: 5px;
	border: none;
}
#kakao{
/* 	background: url('imgs/kakao_login.png') center; */
	background-color: #FEE500;
    color: #000000;
	background-size: cover;
	border-radius: 5px;
	border: none;
}
</style>
</head>

<body>
<div class="container">
      <div class="row justify-content-center mt-5">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
	           	<div class="navbar justify-content-center">
					<a href='<c:url value="/"/>'><img src='imgs/HanulFit-black.png'></a>
				</div>
            </div>
            
            <div class="p-5">
              <form>
                <div class="form-group">
                  <label for="userid" >아이디</label>
                  <input type="text" class="form-control" id="userid" placeholder="아이디를 입력하세요">
                </div>
                <div class="form-group">
                  <label for="userpw">비밀번호</label>
                  <input type="password" class="form-control" id="userpw" placeholder="비밀번호를 입력하세요">
                </div>
                <div class="form-group">
	                <button type="button" class="btn btn-primary btn-block" id='origin' onclick='fn_login()'>로그인</button>
					<button type="button" class="btn btn-primary btn-block" id="naver">네이버로 로그인하기</button>
					<button type="button" class="btn btn-primary btn-block" id="kakao">카카오로 로그인하기</button>
	        	</div>	
					<hr>	
				<div class="navbar justify-content-center">
					<ul>
						<li>
							<a id='under' href='findpw' >비밀번호 찾기</a>
							<div class="vr"></div>
							<a id='under' href='member' >회원가입</a>
						</li>
					</ul>
				</div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script>


//네이버, 카카오 로그인
$('#naver, #kakao').click(function(){
	location =  $(this).attr('id')+ 'login';
});


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






