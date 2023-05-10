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
<link href="css/style-gym.css" rel="stylesheet" />
</head>
<body>
	<!-- Page Header-->
	<jsp:include page="/WEB-INF/views/gym/gym_header.jsp">
		<jsp:param value="센터찾기" name="subtitle" />
	</jsp:include>
	<!-- Main Content-->
	<main class="mb-4">
		<div class="card-container">
			<%
			ArrayList<GymDTO> gymList = (ArrayList<GymDTO>) request.getAttribute("gymlist");
			for (GymDTO gym : gymList) {
			%>
			<a href="gym-detail.ch?gym_id=<%=gym.getGym_id()%>">
				<form action="gym-detail.ch" method="post">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/images/<%=gym.getGym_picture().split(",")[0]%>"
							alt="image">
						<div class="card-text">
							<h2><%=gym.getGym_name()%></h2>
							<p class="adress"><%=gym.getAddress()%></p>
							<%
						int price = gym.getGym_price();
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

</body>
</html>
