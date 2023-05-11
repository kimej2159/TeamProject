<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gym.GymDTO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="kr">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>한울핏 - 센터찾기</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/style-gym.css" rel="stylesheet" />
</head>

<style>
.btnSet { margin: 20px auto; }
.page-list{display: flex; justify-content: center; line-height: 36px;}
.page-list a, .page-list span{text-align: center;
	width: 38px; height: 38px; margin: 1px;
	border: 1px solid transparent;
}
.page-list span{border:1px solid #33676d; color: #3367d6;}
.page-list a:hover{cursor: pointer; background-color: rgb(51, 103, 214, 0.1);}
/* .h-title {cursor: pointer;} */

.wd{
width: 100px;

}
.centerr{
text-align: center;
}

.btn {
	color: #ffffff;
}
</style>

<body>
	<!-- Page Header-->
	<jsp:include page="/WEB-INF/views/gym/gym_header.jsp">
		<jsp:param value="센터찾기" name="subtitle" />
	</jsp:include>
	
	<!-- Main Content-->
	
	<article class="mb-4">
		<div class="card-container px-4 px-lg-5">
		 <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 ">
		<form method='post' action='list.no'>          
		    <div id='list-top'>
		  			<select name='search' class='wd'>
				    	<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</opt-ion>
						<option value='title'
							${page.search eq 'title' ? 'selected' : ''}>제목</option>
						<option value='content'
							${page.search eq 'content' ? 'selected' : ''}>내용</option>
		<!-- 				<option value='writer' -->
		<%-- 					${page.search eq 'writer' ? 'selected' : ''}>작성자</option> --%>
				    </select>
			  <input type='text' name='keyword' value='${page.keyword}' class='w-250px'>
			  <a class='btn btn-primary arrow-middle' onclick='$("form").submit()'>검색</a>
		   
					<!--관리자로 로그인이 된 경우에만 새 글 쓰기 가능 -->
					<c:if test="${loginInfo.admin eq 'Y'}">
						<a class="btn btn-primary" href="regist.ch" role="button" style="float: right;">새글쓰기</a>
					</c:if>
				
			</div>
<!-- 			<input type='hidden' name='curPage' value='1'> -->
		</form> 
		
		
		<!--------------------------------------------  -->
		<%
			ArrayList<GymDTO> gymList = (ArrayList<GymDTO>) request.getAttribute("gymlist");
			for (GymDTO gym : gymList) {
		%>
<!-- 		<form action="gym-detail.ch" method="post"> -->
		<div class="card" onclick='javascript:location="gym-detail.ch?gym_id=<%=gym.getGym_id()%>"'>
			<img src="<%=request.getContextPath()%>/images/<%=gym.getGym_picture().split(",")[0]%>"
							alt="image">
			<div class="card-text">
				<h2><%=gym.getGym_name()%></h2>
				<p class="adress">
					<%
						int price = gym.getGym_price();
						DecimalFormat formatter = new DecimalFormat("###,###");
						String formattedPrice = formatter.format((double) price);
					%>				
				<h1 class="price">월 <%=formattedPrice%>원</h1>
			</div>
		</div>
<!-- 		</form> -->
		<%
			}
		%>
		</div>
		</div>
		</div>

	</article>
	

</body>
</html>
