<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="trainer.TrainerDTO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>한울핏 - 강사찾기</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/style-trainer.css" rel="stylesheet" />
    </head>
    <body>

	<jsp:include page="/WEB-INF/views/trainer/trainer_header.jsp">
		<jsp:param value="강사찾기" name="subtitle" />
	</jsp:include>
	<!-- Post Content-->
        <article class="mb-4">
        
        <form action="trainer-search.ch" method="post">
			<div class="search-form"
				style="display: flex; justify-content: center; align-items: center; width: 800px; margin: 0 auto;">
				<div class="search-form-edit"
					style="display: flex; flex-direction: row; align-items: center;">
					<input type="text" class="form-control" id="searchKeyword"
						name="searchKeyword" placeholder="트레이너 이름을 입력하세요">
				</div>
				<div class="search-form-btn"
					style="display: flex; align-items: center;">
					<button type="submit" class="btn btn-primary">
						검색 <span class="sr-only">검색</span>
					</button>
				</div>
			</div>
		</form>
        
            
		<div class="card-container">
			<%
			ArrayList<TrainerDTO> trainerList = (ArrayList<TrainerDTO>) request.getAttribute("trainerlist");
			for (TrainerDTO trainer : trainerList) {
			%>
			<a href="trainer-detail.ch?trainer_name=<%=trainer.getTrainer_name()%>">
				<form action="trainer-detail.ch" method="post">
			
			<div class="card">
				<img
					src="<%=request.getContextPath()%>/images/<%=trainer.getTrainer_picture().split(",")[0]%>"
					alt="image">
				<div class="card-text">
					<h2><%=trainer.getTrainer_name()%></h2>
					<p class="adress"><%= trainer.getGym_name() %></p>
					<%
						int price = trainer.getPrice();
						DecimalFormat formatter = new DecimalFormat("###,###");
						String formattedPrice = formatter.format((double) price);
					%>
					<h1 class="price">
						월
						<%=formattedPrice%>원
					</h1>
				</div>
			</div>
			<%
			}
			%>
				</form>
			</a>
			
		</div>
        </article>
       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
