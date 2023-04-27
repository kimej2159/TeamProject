<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr td{ text-align: left }
[name=address] { margin-top: 3px }
th span, p span { color:#ff0000; margin-right: 5px; }
p { margin: 10px auto;  text-align: right;}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<h3>회원가입</h3>
<p class='w-px600'><span>*</span>는 필수입력항목입니다</p>
<!-- 
파일을 전송하는 경우 주의점
1. form태그 전송방식을  반드시 post 로 지정
2. 첨부파일전송형식지정: enctype="multipart/form-data"
 -->
<form method='post' action='join' enctype="multipart/form-data">
<table class='w-px600'>
<tr><th class='w-px140'><span>*</span>이름</th>
	<td><input type='text' name='name'> </td>
</tr>
<tr><th><span>*</span>아이디</th>
	<td><input type='text' class='chk' name='id'>
		<a class='btn-fill btn-id'>아이디중복확인</a>
		<div class='invalid'>아이디를 입력하세요(영문소문자,숫자만 가능)</div>
	</td>
</tr>
<tr><th><span>*</span>비밀번호</th>
	<td><input type='password' class='chk' name='pw'>
		<div class='invalid'>비밀번호를 입력하세요(영문대/소문자,숫자 모두 포함)</div>
	</td>
</tr>
<tr><th><span>*</span>비밀번호 확인</th>
	<td><input type='password' class='chk' name='pw_ck'>
		<div class='invalid'>비밀번호를 다시 입력하세요</div>
	</td>
</tr>
<tr><th><span>*</span>성별</th>
	<td><label>
			<input type='radio' name='gender' checked value='남'>남
		</label>
		<label>
			<input type='radio' name='gender' value='여'>여
		</label>
	</td>
</tr>
<tr><th><span>*</span>이메일</th>
	<td><input type='text' class='chk' name='email'>
		<div class='invalid'>이메일을 입력하세요</div>
	</td>
</tr>
<tr><th>프로필이미지</th>
	<td><div class='align'>
		<label>
			<input type='file' name='file' accept="image/*" id='attach-file'>
			<a><i class="font-img-b fa-solid fa-file-circle-plus"></i></a>
		</label>
		<span id='preview'></span>
		<a id='delete-file'><i class="font-img-r fa-regular fa-trash-can"></i></a>
		</div>
	</td>
</tr>
<tr><th>생년월일</th>
	<td><input type='text' name='birth' class='date' readonly>
		<a id='btn-delete'><i class="font-img-r fa-regular fa-circle-xmark"></i></a>
	</td>
</tr>
<tr><th>전화번호</th>
	<td><input type='text' name='phone' maxlength="13"></td>
</tr>
<tr><th>주소</th>
	<td><a class='btn-fill btn-post'>우편번호찾기</a>
		<input type='text' name='post' class='w-px60' readonly>
		<input type='text' name='address' class='full' readonly>
		<input type='text' name='address' class='full'>
	</td>
</tr>
</table>
</form>
<div class='btnSet'>
<a class='btn-fill btn-join'>회원가입</a>
</div>
<script src='js/member_check.js?<%=new java.util.Date() %>'></script>
<script>
$('.btn-id').click(function(){
	id_check();
});
//아이디 중복확인
function id_check(){
	//입력유효성확인
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
var range = today.getFullYear() - 80 + ":" + endDay.getFullYear();						
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
			//$('[name=address]').eq(0).val( address );
        	
        }
    }).open();
});
</script>

</body>
</html>



