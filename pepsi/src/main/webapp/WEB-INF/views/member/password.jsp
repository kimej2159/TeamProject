<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>

#re {
	background-color: #03c75a;
	background-size: cover;
	border-radius: 5px;
	border: none;
}


#origin {
	border-radius: 5px;
	border: none;
	background-color: #dee2e6;
	color: black;
}

#chg{
	background-color: black;
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
              <form method='post' action='change'>
                <div class="form-group">
                  <label for="userpw" >새 비밀번호</label>
                  <input type="password" class="form-control" id="userpw"  name='pw' placeholder="새 비밀번호를 입력하세요">
                  <div class='alert alert-success valid'>새 비밀번호를 입력하세요<br>(영문대/소문자,숫자 모두 포함)</div>
                </div>
                <div class="form-group">
                  <label for="userpw">비밀번호 확인</label>
                  <input type="password" class="form-control" id="userpw" name='pw_ck'  placeholder="비밀번호를 다시 입력하세요">
                  <div class='alert alert-success invalid'>비밀번호를 다시 입력하세요</div>
               
	                <button type="reset" class="btn btn-primary btn-block" id='origin' >다시입력</button>
					<button type="button" class="btn btn-primary btn-block" id="chg" onclick='if( emptyCheck() ) $("form").submit()'>비밀번호 변경</button>
					
	        	</div>	

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

	
<script src='js/member_check.js?<%=new java.util.Date()%>'></script>


<script>
<!-- 비밀번호 스크립트 -->
$('[type=password]').keyup(function(){
	var status = member.tag_status( $(this) );
	$(this).siblings('div').removeClass('valid invalid')
		.addClass(status.code).text(status.desc);
	
});
</script>
</body>
</html>