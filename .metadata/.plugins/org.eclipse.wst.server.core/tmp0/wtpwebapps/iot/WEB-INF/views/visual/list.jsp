<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#tabs { display: flex; border-bottom: 1px solid #3367d6; overflow: hidden; }
#tabs li {
	width: 140px; line-height: 40px; color: #3367d6;
	border: 1px solid #3367d6; border-bottom: none; cursor: pointer;
}
#tabs li:not(:first-child) { border-left: none;  margin-left: 0 }
#tabs li.active { color: #fff; background-color: #3367d6; }
#tab-content { width: 1200px;  height: 550px; margin: 20px auto; }

.c3-chart, .c3-axis{ font-size: 15px }
#legend { display: flex; justify-content: center; }
#legend li {display: flex; align-items:center; }
#legend li:not(:first-child) {margin-left: 30px}
.legend { width: 15px; height: 15px; margin-right: 5px}
</style>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.css'>
</head>
<body>
<h3>사원 정보 분석</h3>
<div class='w-px1200' style='margin: 0 auto'>
	<ul id='tabs'>
		<li>부서원수</li>
		<li>채용인원수</li>
		
	</ul>
</div>
<div id='tab-content'>
	<div class='tab'>
		<label></label><input type='radio' name='graph' checked value='bar'>막대그래프</label>
		<label></label><input type='radio' name='graph' value='donut'>막대그래프</label>
	</div>
	<div class='tab'>
		<label><input type='checkbox' id='top3'>TOP3 부서</label>
		<label><input type='radio' name='unit' value='year' checked>년도별</label>
		<label><input type='radio' name='unit' value='month' >월별</label>
	</div>
	
	
	<div id='chart'></div> 
<!-- 	<div id='graph'></div> -->
	<ul id='legend'>
		<li><span class='legend'></span><sapn>0~9명</sapn></li>
		<li><span class='legend'></span><sapn>10~19명</sapn></li>
		<li><span class='legend'></span><sapn>20~29명</sapn></li>
		<li><span class='legend'></span><sapn>30~39명</sapn></li>
		<li><span class='legend'></span><sapn>40~49명</sapn></li>
		<li><span class='legend'></span><sapn>50명 이상</sapn></li>
	</ul>
</div>

<!-- 탭 선택시 색깔 표시 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.min.js"></script>
<script>
//그래프 형태 변경(막대/도넛)
$('[name=graph]').change(function(){
	init();
	department();
});

function init(){
	$('#legend').css('display', 'none');
	$('#chart').empty();
//	$('#graph').empty;		//div태그 id를 위에서 뭐라고 줬는지에 따라 달라짐

}

$('#tabs li').click(function(){
	if( $(this).hasClass('active') ) return;
	$('#tabs li').removeClass();
	$(this).addClass('active');
	
	var idx = $(this).index();
	$('.tab').css('display', 'none');
	$('.tab').eq(idx).css('display', 'block');
	if( idx==0 )  department();//부서원수
	else if( idx==1 ) hirement(); //채용인원수
	
});

//채용 인원수 그래프
function hirement(){
	init();
	var unit = $('[name=unit]:checked').val();
	if( $('#top3').prop('checked') )
		hirement_top3_chart(unit);
	else hirement_chart(unit);
}

//채용인원수 상위3개 부서에 대한 년도별/월별 채용인원수 그래프
function hirement_top3_chart( unit ){
	$.ajax({
		url: '<c:url value="/visual/hirement/top3/"/>' + unit,
		success: function( response ){
			//console.log( response )
			var info = [];
			if( unit=='year'){
			info.push( ['부서명', '2001년','2002년','2003년','2004년','2005년','2006년','2007년','2008년','2023년'])
			$(response).each(function(){
				info.push(new Array(this.DEPARTMENT_NAME, this.Y2001, this.Y2002, this.Y2003, this.Y2004, this.Y2005, this.Y2006,
						 this.Y2007, this.Y2008, this.Y2023))
			})
			}else{
				info.push( new Array('부서명', '01월','02월','03월','04월','05월','06월',
						'07월','08월','09월','10월','11월','12월'));
				$(response).each(function(){
					info.push([ this.DEPARTMENT_NAME, this.M01, this.M02, this.M03, this.M04, this.M05,
						 this.M06, this.M07, this.M08, this.M09, this.M10, this.M11, this.M12 ]);
				})
			}console.log('info>', info)
			make_chart_hirement_top3(info, unit);
		}
	})
}

function make_chart_hirement_top3(info, unit){
	c3.generate({
		data: { columns:info, x:'부서명', type: unit=='year' ? 'bar' : 'line', labels:true},
		axis: { x: {type: 'category'}
			  , Y: {label:{text: (unit=='year' ? '년도' : '월') + '별 채용인원수', position:'outer-middle'}} },
		size: {height:450},
		bar: {width:20},		
		grid: {y:{show:true}},
		padding: {bottom:50},
		legend: {item: {title:{width:15, height:15} }, padding:40 },
		
	})
	$("c3-legend-item").css('font-size', '15px');
	$('.c3-line').css('stroke-width', '3px');
}



//회사의 년도별/월별 채용인원수 그래프
function hirement_chart( unit ){
	$.ajax({
		url: '<c:url value="/visual/hirement/"/>'+ unit,
		success: function( response ){
			//console.log( response )
			var name = [unit], count=['채용인원수']
			$(response).each(function(idx, item){
				name.push(this.UNIT)
				count.push(this.COUNT)
			})
			 make_chart_hirement( [name, count] )
		}
	})
}
 
function make_chart_hirement( info ){
	console.log( info )
	c3.generate({
		//bindto: '#graph',
		data: { color: function(c, d){return colors[ Math.floor(d.value/10) ] }
			, labels: true, columns: info, type: 'bar', x: $('[name=unit]:checked').val() },
		axis: {x:{type: 'category'}, y:{ label: {text: info[1][0], position:'outer-top'}}},
		size: {height: 450},	//높이 설정
		bar: {width:30},		//바 사이즈
		grid: {y:{show:true}},	//y축 선 보이게 하는, x축도 표현 가능  		
		legend: {hide:true},	//x축 기본 범례 숨김
	})
	$('#legend').css('display', 'flex');
}


$('[name=unit], #top3').change(function(){
	hirement();
})


function department(){
	init();
	$.ajax({
		url:'<c:url value="/visual/department"/>',
		success: function( response ){
			console.log( response )
			var count = ['부서원수'], name = ['부서명'], info=[];
			$(response).each(function(){
				count.push(this.COUNT);
				name.push(this.DEPARTMENT_NAME);
				info.push( new Array(this.DEPARTMENT_NAME, this.COUNT));
			})
			console.log( 'count: ', count)
			console.log( 'name: ', name)
			
			if( $('[name=graph]:checked').val()=='bar')
				bar_chart( [ name, count ] );	//선/ 막대 그래프에 사용한 정보
			else 
				donut_chart( info );					//파이 그래프 형태
			
					
			//[['data1', 30], ['data2', 120], ]
		}
	});
	
	
	
}

function make_chart( info ){
	//1.기본-선그래프
	//line_chart( info );
	//2.파이 그래프
	//pie_chart( info );
	//3.도넛 그래프
	//donut_chart( info );
	//4. 막대그래프
	bar_chart( info );
}
var colors = ['#32a852','#3f36eb','#3ba7eb','#0afa96','#8f388d','#cc6eca','#f7f557','#05acfa', '#f77957','#de7957','#8f6d3d','#f2aa44','#d406a0','#e3e29d','#3bebcd','#3ba7eb'];
function bar_chart( info ){
	c3.generate({
		//bindto: '#graph',
		data: {columns: info, type: 'bar', x:'부서명', labels: true,
				color: function(color, data){
					//retrun colors[data.index];		//각 부서별 색상을 다르게
					return colors[ Math.floor(data.value/10)];		//사원수 범위별로 색상을 다르게
				}
			},
		
		size: {height: 450},
		padding: {bottom:50},
		axis: {
			x: {type:'category'},
			y: {label:{text:'부서원수', position: 'outer-middle' }},
		},
		bar: {width: 30},				//막대굵기
		grid: {y: {show: true}},		//배경 Y축 보이도록 세로줄 긋기
		legend: {hide: true },			//범례가 보이지 않게
	})
	$('#legend').css('display', 'flex');
}


function donut_chart( info ){
	c3.generate({
		//bindto: '#graph',
		data: {columns: info, type: 'donut'},
		size: {height: 450},
		padding: {bottom:50},
		donut: {
			label: {
				format: function(v,r,id){
					return (r*100).toFixed(2) + '%';
				}
			},
			width: 100,			//도넛의 두께
			title: '부서원수'		//도넛 내부에 타이틀 표현
		}
	})	
}

function pie_chart( info ){
	c3.generate({
		//bindto: '#graph',
		data: {columns: info, type: 'pie'},
		size: {height: 450},
		padding: {bottom:50 },		//차트와 범례간 여백
		pie:{
			label:{
				format: function(value, ratio, id ){
					return (ratio*100).toFixed(1) + '%('+ value+'명)';
				}
			}
		}
	})
}

function line_chart( info ){
	c3.generate({
		//bindto: '#graph',
	    data: {
	        columns: info,
	        type: 'line',
	        x: '부서명', 
	    },
	    axis: {
	    	x: {type: 'category'}
	    },
	    size: {height: 450 }
	});	
}

//강제 이벤트(트리거) 강제 발생으로 첫화면에 선택된 화면 발생
$(function(){
	$('#tabs li').eq(1).trigger( 'click' );
	$('.legend').each(function(idx){
		$(this).css('background-color', colors[idx])
	})
});
</script>
</body>
</html>








