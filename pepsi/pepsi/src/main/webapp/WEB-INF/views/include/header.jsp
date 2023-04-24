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
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>

<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
	<div class="container px-4 px-lg-5">
       <a class="navbar-brand" href="home"><img src='imgs/HanulFit-white.png'></span></a>
       <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
           메뉴
           <i class="fas fa-bars"></i>
       </button>
       <div class="collapse navbar-collapse" id="navbarResponsive">
           <ul class="navbar-nav ms-auto py-4 py-lg-0">
              
    		<!--       은지 로그인 부분 	-->

			<!-- 로그인하지 않은 경우 -->
			<c:if test='${empty loginInfo}'>
	   		<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="login">로그인</a></li>
	        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="join">회원가입</a></li>
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

			</c:if>
			 
			 <li class="nav-item"><a ${category eq '' ? "class='active'" : ''} class="nav-link px-lg-3 py-3 py-lg-4" href="">센터찾기</a></li>
             <li class="nav-item"><a ${category eq '' ? "class='active'" : ''} class="nav-link px-lg-3 py-3 py-lg-4" href="">강사찾기</a></li>
             <li class="nav-item"><a ${category eq '' ? "class='active'" : ''} class="nav-link px-lg-3 py-3 py-lg-4" href="">커뮤니티</a></li>
			
		</ul>
	</div>
          
 </nav>
        
<header class="masthead" style="background-image: url('${pageContext.request.contextPath}/resources/assets/img/home-bg.JPG')">
	<div class="container position-relative px-4 px-lg-5">
	    <div class="row gx-4 gx-lg-5 justify-content-center">
	        <div class="col-md-10 col-lg-8 col-xl-7">
	            <div class="site-heading"  data-aos="fade-up">
	                <h1><span class="main_color">한울핏</span>과 함께 건강한 운동을!</h1>
	                <span class="subheading">강사매칭, 센터찾기, 커뮤니티까지!</span>
	            </div>
	        </div>
	    </div>
	</div>
           

</header>








