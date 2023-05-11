<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>한울핏</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" ></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>

<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
	<div class="container px-4 px-lg-5">
       <a class="navbar-brand" href="/"><img src='imgs/HanulFit-white.png'></a>
       <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
           메뉴
           <i class="fas fa-bars"></i>
       </button>
       </div>
       <div class="container navbar-collapse" id="navbarResponsive">
           <ul class="navbar-nav ms-auto py-4 py-lg-0">
              
    		<!--       은지 로그인 부분 	-->

			<!-- 로그인하지 않은 경우 -->
			<c:if test='${empty loginInfo}'>
	   		<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="login">로그인</a></li>
	        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="member">회원가입</a></li>
	        </c:if>
	        
			<!-- 로그인된 경우 -->
			<c:if test='${not empty loginInfo}'>
				<c:choose>
					<c:when test='${empty loginInfo.profile}'>
						<i class="font-profile fa-regular fa-circle-user"></i>
					</c:when>
					<c:otherwise>
						<img class='profile' src='${loginInfo.profile}'>
					</c:otherwise>
				</c:choose>
			
				<li class="nav-item"><a  class="nav-link px-lg-3 py-3 py-lg-4"><strong>${loginInfo.name}님</strong> </a></li>
	       		<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="changepw">비밀번호변경</a></li>
	       		<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="logout">로그아웃</a></li>
	       		<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="mypage">마이페이지</a></li>

			</c:if>
			 

			 <li class="nav-item"><a  class="${category eq 'ch' ? 'active' : ''} nav-link px-lg-3 py-3 py-lg-4" href="gym.ch">센터찾기</a></li>
             <li class="nav-item"><a class="${category eq 'ch' ? 'active' : ''} nav-link px-lg-3 py-3 py-lg-4" href="trainer.ch">강사찾기</a></li>
             <li class="nav-item"><a class="${category eq 'bo' ? 'active' : ''} nav-link px-lg-3 py-3 py-lg-4" href="list.bo">커뮤니티</a></li>
             <li class="nav-item"><a  class="${category eq 'no' ? 'active' : ''} nav-link px-lg-3 py-3 py-lg-4" href="<c:url value="/"/>list.no">공지사항</a></li>
			
		</ul>
	</div>
          
 </nav>
        

<style>
.font-profile { font-size: 50px; }
.profile { width: 50px; height: 50px; border-radius: 50%; }

</style>







