/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.70
 * Generated at: 2023-04-19 07:15:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.visual;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/STUDY_SPRING/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/iot/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1676520927963L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("#tabs { display: flex; border-bottom: 1px solid #3367d6; overflow: hidden; }\r\n");
      out.write("#tabs li {\r\n");
      out.write("	width: 140px; line-height: 40px; color: #3367d6;\r\n");
      out.write("	border: 1px solid #3367d6; border-bottom: none; cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("#tabs li:not(:first-child) { border-left: none;  margin-left: 0 }\r\n");
      out.write("#tabs li.active { color: #fff; background-color: #3367d6; }\r\n");
      out.write("#tab-content { width: 1200px;  height: 550px; margin: 20px auto; }\r\n");
      out.write("\r\n");
      out.write(".c3-chart, .c3-axis{ font-size: 15px }\r\n");
      out.write("#legend { display: flex; justify-content: center; }\r\n");
      out.write("#legend li {display: flex; align-items:center; }\r\n");
      out.write("#legend li:not(:first-child) {margin-left: 30px}\r\n");
      out.write(".legend { width: 15px; height: 15px; margin-right: 5px}\r\n");
      out.write("</style>\r\n");
      out.write("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.css'>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h3>사원 정보 분석</h3>\r\n");
      out.write("<div class='w-px1200' style='margin: 0 auto'>\r\n");
      out.write("	<ul id='tabs'>\r\n");
      out.write("		<li>부서원수</li>\r\n");
      out.write("		<li>채용인원수</li>\r\n");
      out.write("		\r\n");
      out.write("	</ul>\r\n");
      out.write("</div>\r\n");
      out.write("<div id='tab-content'>\r\n");
      out.write("	<div class='tab'>\r\n");
      out.write("		<label></label><input type='radio' name='graph' checked value='bar'>막대그래프</label>\r\n");
      out.write("		<label></label><input type='radio' name='graph' value='donut'>막대그래프</label>\r\n");
      out.write("	</div>\r\n");
      out.write("	<div class='tab'>\r\n");
      out.write("		<label><input type='checkbox' id='top3'>TOP3 부서</label>\r\n");
      out.write("		<label><input type='radio' name='unit' value='year' checked>년도별</label>\r\n");
      out.write("		<label><input type='radio' name='unit' value='month' >월별</label>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	<div id='chart'></div> \r\n");
      out.write("<!-- 	<div id='graph'></div> -->\r\n");
      out.write("	<ul id='legend'>\r\n");
      out.write("		<li><span class='legend'></span><sapn>0~9명</sapn></li>\r\n");
      out.write("		<li><span class='legend'></span><sapn>10~19명</sapn></li>\r\n");
      out.write("		<li><span class='legend'></span><sapn>20~29명</sapn></li>\r\n");
      out.write("		<li><span class='legend'></span><sapn>30~39명</sapn></li>\r\n");
      out.write("		<li><span class='legend'></span><sapn>40~49명</sapn></li>\r\n");
      out.write("		<li><span class='legend'></span><sapn>50명 이상</sapn></li>\r\n");
      out.write("	</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- 탭 선택시 색깔 표시 -->\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("//그래프 형태 변경(막대/도넛)\r\n");
      out.write("$('[name=graph]').change(function(){\r\n");
      out.write("	init();\r\n");
      out.write("	department();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function init(){\r\n");
      out.write("	$('#legend').css('display', 'none');\r\n");
      out.write("	$('#chart').empty();\r\n");
      out.write("//	$('#graph').empty;		//div태그 id를 위에서 뭐라고 줬는지에 따라 달라짐\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$('#tabs li').click(function(){\r\n");
      out.write("	if( $(this).hasClass('active') ) return;\r\n");
      out.write("	$('#tabs li').removeClass();\r\n");
      out.write("	$(this).addClass('active');\r\n");
      out.write("	\r\n");
      out.write("	var idx = $(this).index();\r\n");
      out.write("	$('.tab').css('display', 'none');\r\n");
      out.write("	$('.tab').eq(idx).css('display', 'block');\r\n");
      out.write("	if( idx==0 )  department();//부서원수\r\n");
      out.write("	else if( idx==1 ) hirement(); //채용인원수\r\n");
      out.write("	\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("//채용 인원수 그래프\r\n");
      out.write("function hirement(){\r\n");
      out.write("	init();\r\n");
      out.write("	var unit = $('[name=unit]:checked').val();\r\n");
      out.write("	if( $('#top3').prop('checked') )\r\n");
      out.write("		hirement_top3_chart(unit);\r\n");
      out.write("	else hirement_chart(unit);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//채용인원수 상위3개 부서에 대한 년도별/월별 채용인원수 그래프\r\n");
      out.write("function hirement_top3_chart( unit ){\r\n");
      out.write("	$.ajax({\r\n");
      out.write("		url: '");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("' + unit,\r\n");
      out.write("		success: function( response ){\r\n");
      out.write("			//console.log( response )\r\n");
      out.write("			var info = [];\r\n");
      out.write("			if( unit=='year'){\r\n");
      out.write("			info.push( ['부서명', '2001년','2002년','2003년','2004년','2005년','2006년','2007년','2008년','2023년'])\r\n");
      out.write("			$(response).each(function(){\r\n");
      out.write("				info.push(new Array(this.DEPARTMENT_NAME, this.Y2001, this.Y2002, this.Y2003, this.Y2004, this.Y2005, this.Y2006,\r\n");
      out.write("						 this.Y2007, this.Y2008, this.Y2023))\r\n");
      out.write("			})\r\n");
      out.write("			}else{\r\n");
      out.write("				info.push( new Array('부서명', '01월','02월','03월','04월','05월','06월',\r\n");
      out.write("						'07월','08월','09월','10월','11월','12월'));\r\n");
      out.write("				$(response).each(function(){\r\n");
      out.write("					info.push([ this.DEPARTMENT_NAME, this.M01, this.M02, this.M03, this.M04, this.M05,\r\n");
      out.write("						 this.M06, this.M07, this.M08, this.M09, this.M10, this.M11, this.M12 ]);\r\n");
      out.write("				})\r\n");
      out.write("			}console.log('info>', info)\r\n");
      out.write("			make_chart_hirement_top3(info, unit);\r\n");
      out.write("		}\r\n");
      out.write("	})\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function make_chart_hirement_top3(info, unit){\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		data: { columns:info, x:'부서명', type: unit=='year' ? 'bar' : 'line', labels:true},\r\n");
      out.write("		axis: { x: {type: 'category'}\r\n");
      out.write("			  , Y: {label:{text: (unit=='year' ? '년도' : '월') + '별 채용인원수', position:'outer-middle'}} },\r\n");
      out.write("		size: {height:450},\r\n");
      out.write("		bar: {width:20},		\r\n");
      out.write("		grid: {y:{show:true}},\r\n");
      out.write("		padding: {bottom:50},\r\n");
      out.write("		legend: {item: {title:{width:15, height:15} }, padding:40 },\r\n");
      out.write("		\r\n");
      out.write("	})\r\n");
      out.write("	$(\"c3-legend-item\").css('font-size', '15px');\r\n");
      out.write("	$('.c3-line').css('stroke-width', '3px');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//회사의 년도별/월별 채용인원수 그래프\r\n");
      out.write("function hirement_chart( unit ){\r\n");
      out.write("	$.ajax({\r\n");
      out.write("		url: '");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("'+ unit,\r\n");
      out.write("		success: function( response ){\r\n");
      out.write("			//console.log( response )\r\n");
      out.write("			var name = [unit], count=['채용인원수']\r\n");
      out.write("			$(response).each(function(idx, item){\r\n");
      out.write("				name.push(this.UNIT)\r\n");
      out.write("				count.push(this.COUNT)\r\n");
      out.write("			})\r\n");
      out.write("			 make_chart_hirement( [name, count] )\r\n");
      out.write("		}\r\n");
      out.write("	})\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("function make_chart_hirement( info ){\r\n");
      out.write("	console.log( info )\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		//bindto: '#graph',\r\n");
      out.write("		data: { color: function(c, d){return colors[ Math.floor(d.value/10) ] }\r\n");
      out.write("			, labels: true, columns: info, type: 'bar', x: $('[name=unit]:checked').val() },\r\n");
      out.write("		axis: {x:{type: 'category'}, y:{ label: {text: info[1][0], position:'outer-top'}}},\r\n");
      out.write("		size: {height: 450},	//높이 설정\r\n");
      out.write("		bar: {width:30},		//바 사이즈\r\n");
      out.write("		grid: {y:{show:true}},	//y축 선 보이게 하는, x축도 표현 가능  		\r\n");
      out.write("		legend: {hide:true},	//x축 기본 범례 숨김\r\n");
      out.write("	})\r\n");
      out.write("	$('#legend').css('display', 'flex');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("$('[name=unit], #top3').change(function(){\r\n");
      out.write("	hirement();\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function department(){\r\n");
      out.write("	init();\r\n");
      out.write("	$.ajax({\r\n");
      out.write("		url:'");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("		success: function( response ){\r\n");
      out.write("			console.log( response )\r\n");
      out.write("			var count = ['부서원수'], name = ['부서명'], info=[];\r\n");
      out.write("			$(response).each(function(){\r\n");
      out.write("				count.push(this.COUNT);\r\n");
      out.write("				name.push(this.DEPARTMENT_NAME);\r\n");
      out.write("				info.push( new Array(this.DEPARTMENT_NAME, this.COUNT));\r\n");
      out.write("			})\r\n");
      out.write("			console.log( 'count: ', count)\r\n");
      out.write("			console.log( 'name: ', name)\r\n");
      out.write("			\r\n");
      out.write("			if( $('[name=graph]:checked').val()=='bar')\r\n");
      out.write("				bar_chart( [ name, count ] );	//선/ 막대 그래프에 사용한 정보\r\n");
      out.write("			else \r\n");
      out.write("				donut_chart( info );					//파이 그래프 형태\r\n");
      out.write("			\r\n");
      out.write("					\r\n");
      out.write("			//[['data1', 30], ['data2', 120], ]\r\n");
      out.write("		}\r\n");
      out.write("	});\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function make_chart( info ){\r\n");
      out.write("	//1.기본-선그래프\r\n");
      out.write("	//line_chart( info );\r\n");
      out.write("	//2.파이 그래프\r\n");
      out.write("	//pie_chart( info );\r\n");
      out.write("	//3.도넛 그래프\r\n");
      out.write("	//donut_chart( info );\r\n");
      out.write("	//4. 막대그래프\r\n");
      out.write("	bar_chart( info );\r\n");
      out.write("}\r\n");
      out.write("var colors = ['#32a852','#3f36eb','#3ba7eb','#0afa96','#8f388d','#cc6eca','#f7f557','#05acfa', '#f77957','#de7957','#8f6d3d','#f2aa44','#d406a0','#e3e29d','#3bebcd','#3ba7eb'];\r\n");
      out.write("function bar_chart( info ){\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		//bindto: '#graph',\r\n");
      out.write("		data: {columns: info, type: 'bar', x:'부서명', labels: true,\r\n");
      out.write("				color: function(color, data){\r\n");
      out.write("					//retrun colors[data.index];		//각 부서별 색상을 다르게\r\n");
      out.write("					return colors[ Math.floor(data.value/10)];		//사원수 범위별로 색상을 다르게\r\n");
      out.write("				}\r\n");
      out.write("			},\r\n");
      out.write("		\r\n");
      out.write("		size: {height: 450},\r\n");
      out.write("		padding: {bottom:50},\r\n");
      out.write("		axis: {\r\n");
      out.write("			x: {type:'category'},\r\n");
      out.write("			y: {label:{text:'부서원수', position: 'outer-middle' }},\r\n");
      out.write("		},\r\n");
      out.write("		bar: {width: 30},				//막대굵기\r\n");
      out.write("		grid: {y: {show: true}},		//배경 Y축 보이도록 세로줄 긋기\r\n");
      out.write("		legend: {hide: true },			//범례가 보이지 않게\r\n");
      out.write("	})\r\n");
      out.write("	$('#legend').css('display', 'flex');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function donut_chart( info ){\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		//bindto: '#graph',\r\n");
      out.write("		data: {columns: info, type: 'donut'},\r\n");
      out.write("		size: {height: 450},\r\n");
      out.write("		padding: {bottom:50},\r\n");
      out.write("		donut: {\r\n");
      out.write("			label: {\r\n");
      out.write("				format: function(v,r,id){\r\n");
      out.write("					return (r*100).toFixed(2) + '%';\r\n");
      out.write("				}\r\n");
      out.write("			},\r\n");
      out.write("			width: 100,			//도넛의 두께\r\n");
      out.write("			title: '부서원수'		//도넛 내부에 타이틀 표현\r\n");
      out.write("		}\r\n");
      out.write("	})	\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function pie_chart( info ){\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		//bindto: '#graph',\r\n");
      out.write("		data: {columns: info, type: 'pie'},\r\n");
      out.write("		size: {height: 450},\r\n");
      out.write("		padding: {bottom:50 },		//차트와 범례간 여백\r\n");
      out.write("		pie:{\r\n");
      out.write("			label:{\r\n");
      out.write("				format: function(value, ratio, id ){\r\n");
      out.write("					return (ratio*100).toFixed(1) + '%('+ value+'명)';\r\n");
      out.write("				}\r\n");
      out.write("			}\r\n");
      out.write("		}\r\n");
      out.write("	})\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function line_chart( info ){\r\n");
      out.write("	c3.generate({\r\n");
      out.write("		//bindto: '#graph',\r\n");
      out.write("	    data: {\r\n");
      out.write("	        columns: info,\r\n");
      out.write("	        type: 'line',\r\n");
      out.write("	        x: '부서명', \r\n");
      out.write("	    },\r\n");
      out.write("	    axis: {\r\n");
      out.write("	    	x: {type: 'category'}\r\n");
      out.write("	    },\r\n");
      out.write("	    size: {height: 450 }\r\n");
      out.write("	});	\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//강제 이벤트(트리거) 강제 발생으로 첫화면에 선택된 화면 발생\r\n");
      out.write("$(function(){\r\n");
      out.write("	$('#tabs li').eq(1).trigger( 'click' );\r\n");
      out.write("	$('.legend').each(function(idx){\r\n");
      out.write("		$(this).css('background-color', colors[idx])\r\n");
      out.write("	})\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/views/visual/list.jsp(102,8) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/visual/hirement/top3/");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f1_reused = false;
    try {
      _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f1.setParent(null);
      // /WEB-INF/views/visual/list.jsp(146,8) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f1.setValue("/visual/hirement/");
      int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
      if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      _jspx_th_c_005furl_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f1, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f2_reused = false;
    try {
      _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f2.setParent(null);
      // /WEB-INF/views/visual/list.jsp(183,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f2.setValue("/visual/department");
      int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
      if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      _jspx_th_c_005furl_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f2, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f2_reused);
    }
    return false;
  }
}