<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
<link href="css/style-gym-detail.css" rel="stylesheet" />

</head>
<body>

	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('assets/img/about-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="page-heading">
						<h1>
							<span class="main_color">센터찾기</span>
						</h1>
						<span class="subheading">내근처 헬스장 찾기!</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content-->
	<main class="mb-4 gym-detail-contain">
		<%
		ArrayList<GymDTO> gymList = (ArrayList<GymDTO>) request.getAttribute("gymlist");
		for (GymDTO gym : gymList) {
		%>
		<!-- Main Content-->
		<div>
<!-- 		<div></div> -->
					<h1 style=" margin: 0 auto;">● 헬스장 정보 한눈에 보기</h1>
					<div class="gym-detail-all">
						<div class="gym-detail">
							<img
								src="<%=request.getContextPath()%>/images/<%=gym.getGym_picture()%>"
								class="img-fluid" alt="Gym Image">
							<div class="gym-detail-content">
								<table>
							<tr>
								<th>이름</th>
								<td>
									<div>
										<%=gym.getGym_name()%>
									</div>
									<div>
										(주소)
									</div>

								</td>
							</tr>
							<tr>
								<th>가격(월)</th>
								<td><%=gym.getGym_price()%>원</td>
							</tr>
							<tr>
								<th>편의<br>시설</th>
								<td>
									<div>첫 번째 줄</div>
									<div>두 번째 줄</div>
									<div>세 번째 줄</div>
								</td>
							</tr>
							<tr>
								<th>부가<br>서비스</th>
								<td>
									<div>개인락커(월) : 20000원</div>
								</td>
							</tr>
							
						</table>
							</div>
						</div>
						<div class="gym-detail-plus"></div>
					</div>
		
			
		</div>
		<%
		}
		%>

	</main>
	<!-- Footer-->
	<footer class="border-top">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<ul class="list-inline text-center">
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-twitter fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-github fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
					</ul>
					<div class="small text-center text-muted fst-italic">Copyright
						&copy; HANULFIT</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>