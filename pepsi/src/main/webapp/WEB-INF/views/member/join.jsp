<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>Bootstrap Example</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<meta charset="UTF-8">
<title>한울핏 회원가입</title>
<style>
#btn-delete,  #delete-file 
,  .delete-file { display: none }

#preview img {max-height: 50px; }
.ej{
 width: 200px;
}
.eu{
 font-size: 13px;
 text-align: right;
}
#userid {
border-radius: 5px;
}
.origin {
	border-radius: 5px;
	border: none;
	background-color: black;
	margin: 4px;
}

span { color:#ff0000; margin-right: 5px; }
</style>

</head>
<body>
<div class="container">
      <div class="row justify-content-center mt-5">
        <div class="col-md-6 mb-5">
          <div class="card">
            <div class="card-header">
	           	<div class="navbar justify-content-center">
					<a href='<c:url value="/"/>'><img src='imgs/HanulFit-black.png'></a>
				</div>
            </div>

	
	<div class="p-5">
	<div class='eu'><span>*</span>는 필수 입력 항목입니다.</div>
	<form method="post" action="join" enctype="multipart/form-data">
		<div  class="mb-3">
          <label for="name" ><span>*</span>이름</label>
          <input type="text" id="name" class="chk form-control" name='name' placeholder="이름을 입력하세요">
        </div>
		<div class="mb-3">
			<label><span>*</span>아이디</label>
			<input type="text" class="chk form-control"  name='id' placeholder="아이디를 입력하세요">
       		<button type="button" class="my-2 btn-id btn btn-primary btn-block origin" >아이디중복확인</button>
        	<div class='alert alert-success valid'>아이디를 입력하세요<br></div>
        </div>
         <div class="mb-3">
           <label for="userpw" ><span>*</span>비밀번호</label>
           <input type="password" id="userpw" class="chk form-control" name='pw' placeholder="새 비밀번호를 입력하세요">
           <div class='alert alert-success valid'>비밀번호를 입력하세요<br>(영문대/소문자,숫자 모두 포함)</div>
         </div>
         <div class="mb-3">
            <label ><span>*</span>비밀번호 확인</label>
            <input type="password" class="chk form-control"  name='pw_ck'  placeholder="비밀번호를 다시 입력하세요">
            <div class='alert alert-success invalid'>비밀번호를 다시 입력하세요</div>
         </div>
		<div  class="mb-3">
			<label>성별</label>
			<div class="form-check">
			  <input class="form-check-input" type="radio" value="남" name="gender" id="flexRadioDefault1" checked >
			  <label class="form-check-label" for="flexRadioDefault1">남</label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" value="여" name="gender" id="flexRadioDefault2">
			  <label class="form-check-label" for="flexRadioDefault2">여</label>
			</div>
		</div>
		<div class="mb-3">
			<label>이메일</label>
			<input type="text"  class="chk form-control"  name='email' placeholder="이메일을 입력하세요">
        	<div class='alert alert-success valid'>이메일을 입력하세요<br>(영문대/소문자,숫자 모두 포함)</div>
        </div>
        <div class="mb-3">
		<label>프로필 이미지</label>
			<div class="row">
				<label>
					<input type='file' class="form-control" name='file' accept="image/*" id='attach-file'>
				</label>
			</div>
			<div class="d-flex">
				<span id='preview' class='mt-4'></span>
				<div id="delete-file" class='mt-5'>
					<a><i class="font-img-r fa-regular fa-trash-can"></i></a>
				
				</div>	
			</div>
		</div>
		<div  class="mb-3">
			<label>생년월일</label>
			<input type='text' name='birth' class='date' readonly>
			<a id='btn-delete'><i class="font-img-r fa-regular fa-circle-xmark"></i></a>
			
				
		</div>			

		<div class="mb-3">
			<label  class="form-label">전화번호</label>
				<input type='text' name='phone' maxlength="13">
		</div>
		<div class="mb-3">
		<label>주소</label>
			<div class="form-group">
				<button type="button" class="my-2 btn btn-primary btn-block btn-post origin" >우편번호찾기</button>
				<input type='text' name='post' class='form-control' readonly>
				<input type='text' name='address' class='form-control' readonly>
				
				<input type='text' name='address' class='form-control' >
			</div>
		</div>
	</form>
		<div class="position-relative">
			<div class="position-absolute top-100 start-50 translate-middle ">
<!-- 			<button class="mt-5 btn btn-primary btn-block origin btn-join" >회원가입</button> -->
			<button type="button" class="mt-5 btn btn-primary btn-lg origin btn-join">회원가입</button>
			</div>
		</div>
		
</div>
</div>
</div>
</div>
</div>
<script src='js/member_check.js?<%=new java.util.Date() %>'></script>
<script>
//아이디중복확인 버튼을 눌렀을 때 처리될 함수
$('.btn-id').click(function(){
	id_check();
});

//아이디 중복확인
function id_check(){
	//입력의 유효성 확인
	var $id = $('[name=id]');
		
	if( $id.hasClass('chked') ) return;
	
	var status = member.tag_status( $id );
	if( status.code=='invalid' ){
		alert('아이디 중복확인 불필요\n' + status.desc );
		$id.focus();
		return;
	}
	
	//해당 아이디가 DB에 존재하는지 확인
	$.ajax({
		url: 'id_check',
		data : { id: $id.val() },
		success: function( response ){
			//false: DB에 존재하지 않는 아이디
			console.log( response );
			response = response ? member.id.unUsable : member.id.usable;
			$id.siblings('div').text( response.desc )
				.removeClass().addClass( response.code );
			$id.addClass('chked');
			
		},error: function(req, text){
			alert(text+':'+req.status)
		}
	});
}

$('.chk').on('keyup', function( e ){
	if( $(this).attr('name')=='id' ){
		if( e.keyCode==13 ) id_check();
		$(this).removeClass('chked');
	}
	var status = member.tag_status( $(this) );
	$(this).siblings('div').text( status.desc )
				.removeClass().addClass( status.code );
});

$('.btn-join').click(function(){
	if( $('[name=name]').val()=='' ){
		alert('이름을 입력하세요!');
		$('[name=name]').focus();
	}
	
	//중복확인O + 이미 사용중
	var _id = $('[name=id]');
	if( _id.hasClass('chked') ){
		if( _id.siblings('div').hasClass('invalid') ){
			alert('회원가입 불가\n' + member.id.unUsable.desc);
			_id.focus();
			return;
		}		
	}else{
	//중복확인X
		if( tagIsInvalid( _id ) ) return;
		else{
			alert('회원가입 불가\n' + member.id.valid.desc );
			_id.focus();
			return;
		}
	}
	
	if( tagIsInvalid( $('[name=pw]') ) ) return;
	if( tagIsInvalid( $('[name=pw_ck]') ) ) return;
	if( tagIsInvalid( $('[name=email]') ) ) return;
	
	$('form').submit();
});

//태그의 입력값이 유효하지 않는지 확인할 함수
function tagIsInvalid( tag ){
	var status = member.tag_status( tag );
	if( status.code=='invalid' ){
		alert( '회원가입 불가\n' +  status.desc );
		tag.focus();
		return true;
	}else
		return false;
}



/* 만13세이상 회원가입하도록 생년월일 선택 날짜를 제한  */
var today = new Date();
var endDay = new Date( today.getFullYear()-13, today.getMonth()
						, today.getDate()-1 );
var range = today.getFullYear() - 100 + ":" + endDay.getFullYear();						
$('.date').datepicker({
	maxDate: endDay,
	yearRange: range
});			
 
$('.date').change(function(){
	$(this).next().css('display', 'inline')
});
$('#btn-delete').click(function(){
	$('.date').val('');
	$(this).css('display', 'none')
});

//주소 가져오기
$('.btn-post').on('click', function(){
	new daum.Postcode({
        oncomplete: function(data) {
        	console.log(data);
        	$('[name=post]').val( data.zonecode );
        	
        	var address
        		= data.userSelectedType=='R' 
        		? data.roadAddress : data.jibunAddress;
        	if( data.buildingName!='' )
        		address += ' ('+data.buildingName + ')';
        	
			$('[name=address]:eq(0)').val( address );
        	
        }
    }).open();
});

</script>
</body>
</html>
