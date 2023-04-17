<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한울핏 회원가입</title>
</head>
<body>
<h3>회원가입</h3>
<div class="container">
      <div class="row justify-content-center mt-5">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
	           	<div class="navbar justify-content-center">
					<a href='<c:url value="/"/>'><img src='imgs/HanulFit-black.png'></a>
				</div>
            </div>
<form method='post' action='join'>
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
</div>
</div>
</div>
</div>



</body>
</html>
