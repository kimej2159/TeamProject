<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
#origin {
	border-radius: 5px;
	border: none;
	background-color: black;
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
		<form method='post' action='reset'>
		<div class="form-group">
			<label for="userid" >아이디</label>
			<input type="text" class="form-control" name='id' id="userid" placeholder="아이디를 입력하세요">
        	<label>이메일</label>
			<input type="text" class="form-control" name='email' placeholder="이메일을 입력하세요">
        </div>
		<div class="form-group">
			
			<button type="reset" class="btn btn-primary btn-block" id='origin' >다시입력</button>
			<button type="button" id='new' class="btn btn-primary btn-block" value='비밀번호 재발급' 
						onclick='if( emptyCheck() ) $("form").submit()'>비밀번호 재발급</button>
        </div>       
               
		</form>
	</div> 
</div>
</div>
</div>
</div>

</body>
</html>



