<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<title>한울핏 마이페이지</title>

<style>

#btn-delete, #delete-file
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
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>
<!-- Page Header-->
 <jsp:include page="/WEB-INF/views/member/mypage_header.jsp">
	<jsp:param value="마이페이지" name="subtitle"/>
</jsp:include>
<!-- Post Content-->
		
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
	<form method="post" action="mypageSave" enctype="multipart/form-data">
		<div  class="mb-3">
          <label for="name" ><span>*</span>이름</label>
          <input type="text" id="name" class="chk form-control" name='name' placeholder="이름을 입력하세요" value="${loginInfo.name}">
         </div>

		<div  class="mb-3">
			<label>성별</label>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="gender" value="남" id="flexRadioDefault1" <c:if test="${loginInfo.gender eq '남' }">checked</c:if> >
			  <label class="form-check-label" for="flexRadioDefault1">남</label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="gender" value="여" id="flexRadioDefault2" <c:if test="${loginInfo.gender eq '여' }">checked</c:if>>
			  <label class="form-check-label" for="flexRadioDefault2">여</label>
			</div>
		</div>
		<div class="mb-3">
			<label>이메일</label>
			<input type="text"  class="chk form-control"  name='email' placeholder="이메일을 입력하세요" value="${loginInfo.email}">
<!--         	<div class='alert alert-success valid'>비밀번호를 입력하세요<br>(영문대/소문자,숫자 모두 포함)</div> -->
 			
        </div>
        <div class="mb-3">
		<label>프로필 이미지</label>
			<div class="row mb-4">
				<label>
					<a class="form-control px-5 border-light-1 btn btn-light">파일선택</a>
					<input type='file' class="form-control" name='file' accept="image/*" id='attach-file' style='display:none'>
				</label>
			</div>
			<div class="d-flex  align-items-center">
				<span id='preview'></span>
				<div id="delete-file"  class='mt-5' >
					<a><i class="font-img-r fa-regular fa-trash-can"></i></a>
				</div>	
			</div>

		</div>
		<div  class="mb-3">
			<label>생년월일</label>
			<input type='text' name='birth' class='date' readonly value="${loginInfo.birth}">
			<a id='btn-delete'><i class="font-img-r fa-regular fa-circle-xmark"></i></a>
			
				
		</div>			

		<div class="mb-3">
			<label  class="form-label">전화번호</label>
				<input type='text' name='phone' maxlength="13" value="${loginInfo.phone}">
		</div>
		<div class="mb-3">
		<label>주소</label>
			<div class="form-group">
				<button type="button" class="my-2 btn btn-primary btn-block btn-post origin" >우편번호찾기</button>
				<input type='text' name='post' class='form-control' readonly value="${loginInfo.post}">
				
				<c:forEach items="${fn: split(loginInfo.address, ',')}" var='address' varStatus='state'>
				<input type='text' name='address' class='form-control' ${state.first ? 'readonly' : '' } value="${address}">
				</c:forEach>
				
				<!--  
				<input type='text' name='address' class='form-control' readonly  value="${loginInfo.address}">
				<input type='text' name='address' class='form-control'  value="${loginInfo.address}">
				-->
			</div>
		</div>
		<input type="hidden" name='id' value='${loginInfo.id}'>
		<input type="hidden" name='delete' value='0'>
	</form>
		<div class="position-relative">
			<div class="position-absolute top-100 start-50 translate-middle ">
			<button type="button" class="mt-5 btn btn-primary btn-lg origin btn-save ">저장</button>
			<a href=''><button type="button" class="mt-5 btn btn-primary btn-lg origin"  >취소</button></a>
			</div>
		</div>
		
</div>
</div>
</div>
</div>
</div>
		
<script src='js/member_check.js?<%=new java.util.Date() %>'></script>
<script>

$(function(){
	if( '${loginInfo.profile}' != '' )
		$('#preview').html( "<img src='${loginInfo.profile}'>" )
		$('#delete-file').css( 'display', 'block');
})

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


//수정 저장 버튼
$('.btn-save').click(function(){
	if( $('[name=name]').val()=='' ){
		alert('이름을 입력하세요!');
		$('[name=name]').focus();
	}

	if( tagIsInvalid( $('[name=email]') ) ) return;
	if( $('#attach-file').val() != '' ) $('[name=delete]').val( 0 )	
	$('form').submit();
});

$('#delete-file').click(function(){
	$('[name=delete]').val( 1 )	
})

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