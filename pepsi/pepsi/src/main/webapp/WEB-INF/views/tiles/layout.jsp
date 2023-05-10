<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
<c:when test="${category eq 'cu'}"><c:set var='title' value='고객관리'/></c:when> 
<c:when test="${category eq 'hr'}"><c:set var='title' value='사원관리'/></c:when>
<c:when test="${category eq 'no'}"><c:set var='title' value='공지사항'/></c:when>
<c:when test="${category eq 'bo'}"><c:set var='title' value='방명록'/></c:when>
<%-- <c:when test="${category eq 'changepw'}"><c:set var='title' value='비밀번호변경'/></c:when>
 --%>
<c:when test="${category eq 'join'}"><c:set var='title' value='회원가입'/></c:when>
<c:when test="${category eq 'mypage'}"><c:set var='title' value='마이페이지'/></c:when>
</c:choose>


<title>한울핏 ${title}</title>
<link rel='icon' href='<c:url value="/"/>assets/favicon.ico'>
<link rel='stylesheet' type='text/css' 
		href='<c:url value="/"/>css/common.css?<%=new java.util.Date()%>'>		
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css'>		

<script src='https://code.jquery.com/jquery-3.6.3.min.js'></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src='<c:url value="/"/>js/common.js?<%=new java.util.Date()%>'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/js/all.min.js'></script>


</head>
<body>
<!-- header부분 -->
<tiles:insertAttribute name="header"/>
<div id='container'>
<tiles:insertAttribute name="container"/>
</div>
<!-- footer부분 -->
<tiles:insertAttribute name="footer"/>
</body>
</html>