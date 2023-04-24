<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.pharmacy, .animal, #list-top { width: 1200px }
.pharmacy td:nth-child(3) { text-align: left; }
#popup { width: 800px; height: 600px; }
table.animal img { width:100%; height:100px }
</style>
</head>
<body>
<h3>공공데이터</h3>
<div class='btnSet api'>
	<a>약국정보</a>
	<a>유기동물정보</a>
</div>
<div id='list-top'>
	<ul class='animal-top'></ul>
	<ul class='common'>
		<li><select class='w-px100' id='pageList'>
				<c:forEach var='i' begin='1' end='5'>
					<option value='${10*i}'>${10*i}개씩</option>
				</c:forEach>
			</select>
		</li>
	</ul>
</div>
<div id='data-list'></div>
<div class='btnSet'>
	<div class='page-list'></div>
</div>
<!-- 로딩 중 나타날 화면  -->
<div class='loading center'><img src='imgs/1495.gif'></div>
<div id='popup-background'></div>
<div id='popup' class='center'></div>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d086d8bcae1c61813cde8b3708fd6fe">
</script>

<!-- API key : 6d086d8bcae1c61813cde8b3708fd6fe -->
	
<script>

$(window).scroll(function(){
	center( $('#popup'), $('#popup-background'))
});

$(window).resize(function(){
	center( $('#popup'), $('#popup-background'))
});

$('.api a').click(function(){
	$('.api a').removeClass();
	$(this).addClass('btn-fill');
	$('.api a').not( $(this)).addClass('btn-empty');
	
	if( $(this).index()==0 )	pharmacy_list( 1 ); //처음들어가면 1페이지
	else						animal_list( 1 );
})

//유기동물 시도 조회
function animal_sido(){
	$.ajax({
		url:'data/animal/sido',
		success: function( response ){
				console.log(response)
				$('.animal-top').html( response );
				animal_type();
		}
	})
}

//축종
function animal_type(){
	var tag
	= "<li>"
	+ "<select class='w-px100' id='upkind'>"
	+ "<option value=''>축종선택</option>"
	+ "<option value='417000'>개</option>"
	+ "<option value='422400'>고양이</option>"
	+ "<option value='429900'>기타</option>"
	+ "</select>"
	+ "</li>";
	
	//축종은 시군구와 아무런 관련이 없으므로 화면에 바로 나올 수 있도록 아이디 animal-top에 붙임
	$('.animal-top').append( tag );
}



//유기동물 정보 조회
function animal_list( page ){
	if($('#sido').length==0 ) animal_sido();
	
	$('#data-list').html('');
	$('.page-list').html('');
	loading(true);
	var animal = {};
	animal.pageNo = page;
	animal.rows = pageList;
	animal.sido = $('#sido').length > 0 ? $('#sido').val():'';
	animal.sigungu = $('#sigungu').length > 0 ? $('#sigungu').val():'';		//시군구에 따른 조회
	animal.shelter = $('#shelter').length > 0 ? $('#shelter').val():'';		//보호소에 따른 조회
	animal.upkind = $('#upkind').length > 0 ? $('#upkind').val():'';		//축종에 따른 조회
	animal.kind = $('#kind').length > 0 ? $('#kind').val():'';		//품종에 따른 조회
	
	
	$.ajax({
		type: 'post',
		contentType: 'application/json',
		url: 'data/animal/list',
		data: JSON.stringify( animal ),
		success: function( response ){
			$('#data-list').html( response );
			loading(false);
		},error: function(){
			
			loading(false);
		}
	});
}

$('#pageList').change(function(){
	pageList = $(this).val();
	pharmacy_list( 1 );
});

//약국정보 조회
function pharmacy_list( page ){
	$('.animal-top').empty();
	
	loading(true);
	var tag
	= "<table class='tb-list pharmacy'>"
		+ "<thead><tr><th class='w-px300'>약국명</th>"
		+ " <th class='w-px120'>전화번호</th>"
		+ " <th>주소</th>"
		+ " </tr>" 
		+ " </thead>"
		+ " </table>";
		$('#data-list').html( tag );
		$('.page-list').html( '' );
		
		tag = '';
		$.ajax({
			data: {pageNo: page, rows:pageList},
			url: 'data/pharmacy',
			success: function( response ){
				console.log( response );
				$(response.item).each(function(){
					tag += "<tr><td><a class='map' data-x='"
									+ this.XPos +"' data-y='"+ this.YPos +"'>"+ this.yadmNm +"</a></td>"
							+  "<td>"+ (this.telno ? this.telno : '-') +"</td>"
							+  "<td>"+ this.addr +"</td>"
							+  "</tr>";	
				});
				$('#data-list table thead').append( tag );
				makePage( response.count , page);
				
				loading(false);
			},error: function(){
				loading(false);
			}
		});
	}
	
var pageList = 10, blockPage=10;
function makePage( totalList, curPage ){
	var tag = '';
	//총 페이지 갯수 = 총 목록 / 페이지 목록(10개씩)
	var totalPage = Math.ceil( totalList / pageList );
	//총 블럭 수 = 총 페이지 수 / 블럭 페이지 수(10개씩)
	var totalBlock = Math.ceil( totalPage / blockPage );
	//현재 블럭 수 
	var curBlock = Math.ceil( curPage /blockPage);
	//페이지 번호
	var endPage = curBlock * blockPage;
	var beginPage = endPage - (blockPage-1);
	
	if(endPage > totalPage ) endPage = totalPage;
	
	if(curBlock > 1 ){
		tag += '<a data-page="1"><i class="fa-solid fa-angle-left"></i></a>'
			+ '<a data-page="'+ (beginPage-blockPage) + '"><i class="fa-solid fa-angles-left"></i></a>';
	}
	
	for(var no=beginPage; no<=endPage; no++){
		if( no==curPage ) tag += '<span>' + no +'</span>';
		else			  tag += '<a data-page="'+ no +'">'+ no + '</a>';
	}
	if( totalBlock > curBlock){
		tag += '<a data-page="'+ (endPage + 1) +'"><i class="fa-solid fa-angle-right"></i></a>'
				+ '<a data-page="'+ totalPage + '"><i class="fa-solid fa-angles-right"></i></a>';
	}
	
	$('.page-list').html( tag );
}

//첫 화면 선택해논 강제 발생 트리거 0: 약국 , 1: 유기정보
$(function(){
	$('.api a').eq(0).trigger('click');
});


//동적인 페이지로 나누어 표시한 것임으로 함수를 걸어줌
$(document).on('click', '.page-list a', function(){
	if( $('.pharmacy').length > 0 ) pharmacy_list( $(this).data('page') );
	else if( $('.animal').length > 0 ) animal_list( $(this).data('page') );
	
}).on('click', '.map', function(){
	if( $(this).data('x')=='undefind' || $(this).data('y')=='undefined'){
		alert('위경도가 없어 위치를 표시할 수 없습니다!')
		return;
	}
	
	$('#popup, #popup-background').css('display', 'block');
	//latitude(위도): y, longitude(경도):x
	var x = $(this).data('x'), y=$(this).data('y');
	var xy = new kakao.maps.LatLng(y, x);
	
	//지도를 담을 영역의 DOM 레퍼런스
	var container = document.getElementByld('popup');
	var options = {//지도를 성성할 떄 필요한 기본 옵션 
			center: xy,	//지도의 중심 좌표
			level: 3	//지도의 레벨 (확대 출소 정도)
	};
	
	var map = new kakao.maps.Map(container, options);	//지도 생성 및 객체 리턴
	

	//마커를 생성합니다 
	var marker = new kakao.maps.Marker({
		position: xy		//마커가 표시될 위치
	});
	
	//마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	//인포 윈도우를 생성합니다
	var infowindow = new kakao.maps.infoWindow({
		position: xy,
		content: '<div style="padding:5px;">'+$(this).text()+'</div>'
	});
	
	//마커 위에 인포윈도우를 표시합니다. 두 번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다 
	infowindow.open(map, marker);
	
}).on('click', '#popup-background', function(){
	$('#popup, #popup-background').css('display', 'none');
	
}).on('change', '#upkind', function(){
	animal_kind();
	animal_list(1);
});


//축종에 대한 품종 조회
function animal_kind(){
	$('#kind').closest('li').remove();
	if( $('#upkind').val()=='' )return;
	
	$.ajax({
		url: 'data/animal/kind',
		data: {upkind:$('#upkind').val()},
		success: function( response ){
			$('#upkind').closest('li').after( response )
		}
	});
}
</script>
</body>
</html>