<%@page import="javax.swing.plaf.basic.BasicTreeUI.TreeIncrementAction"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="gym.GymDTO"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="trainer.TrainerDTO"%>
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

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4f8925f47d95a0edcd16d4d3487bff6e&libraries=services"></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/trainer/trainer_header.jsp">
		<jsp:param value="강사찾기" name="subtitle" />
	</jsp:include>
	<!-- Main Content--123>


	<main class="mb-4 gym-detail-contain">
		<%
		ArrayList<TrainerDTO> trainerList = (ArrayList<TrainerDTO>) request.getAttribute("TrainerList");
		for (TrainerDTO trainer : trainerList) {
			
			
		%>

		<!-- Main Content-->
	<div>
		<h1 style="margin: 20 auto;">트레이너 정보 한눈에 보기</h1>
		<div class="gym-detail-all">
			<div class="gym-detail" style="height: 1000px">

				<div class="gym-detail-image">

					<div class="gym-detail-image-main">
						<img id="main-image"
							src="images/<%=trainer.getTrainer_picture().split(",")[0]%>"
							alt="<%=trainer.getTrainer_name()%>" class="main-image">
						<div class="gym-detail-image-overlay"></div>
					</div>
					<div class="gym-detail-image-sub">
						<% String[] images = trainer.getTrainer_picture().split(",");
    					for (int i = 0; i < images.length; i++) { %>
						<img src="images/<%=images[i].trim()%>"
							alt="<%=trainer.getTrainer_name()%>" class="sub-image"
							onclick="changeMainImage(this.src)">
						<% } %>
					</div>

					<p style="margin:20px 0 5px; font-size: 30px"><%= trainer.getTrainer_name() %> 소속 헬스장</p>
					
					<div class="gym-detail-iamge-trainer" >
						
							<%
							ArrayList<GymDTO> gymList = (ArrayList<GymDTO>) request.getAttribute("GymList");
							for (GymDTO gym : gymList) {%>
							<div class="gym-detail-iamge-trainer-list" style="width: 500px; height: 300px;">
								<img src="images/<%=gym.getGym_picture().split(",")[0]%>"
									 class="trianer-image"
									 style="width: 500px; height: 250px;">
									<p style="margin:10px 0 5px; font-size: 20px"><%=gym.getGym_name() %> (<%=gym.getAddress() %> )</p>
							</div>

						

					</div>

				</div>

				<script>
  const mainImage = document.getElementById("main-image");
  function changeMainImage(src) {
    mainImage.src = src;

   
  }

</script>
				<div class="gym-detail-content" style="height: 550px">
					<table>
						<tr>
							<th>이름</th>
							<td>
								<div>
									<%=trainer.getTrainer_name()%>
								</div>
								<div>
									(<%=gym.getGym_name()%>)
								</div>
							</td>
						</tr>
						<tr>
							<th>가격(월)</th>
							<td><%=trainer.getPrice()%>원</td>
						</tr>
		
						<tr>
							<th class="location-th">위치</th>
							<td class="location-td">
								<div class="map-style">
									<div id="map"></div>
								</div>
							</td>

						</tr>

					</table>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(35.450701, 127.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다  123  
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('<%=gym.getAddress()%>'
		, function(result, status) {
	
    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });


        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
    content: '<div style="width:150px;text-align:center;padding:6px 0;">' + '<%= gym.getGym_name() %>'
													+ '</div>'
										});
								infowindow.open(map, marker);

								// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
								map.setCenter(coords);
							}
						});
						
						
	</script>
	

						<% } %>

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