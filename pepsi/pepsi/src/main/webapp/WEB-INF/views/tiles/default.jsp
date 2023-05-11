<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<c:choose>
<c:when test='${category eq "login"}'><c:set var='title' value='로그인'/></c:when>
<c:when test='${category eq "changepw"}'><c:set var='title' value='비밀번호 변경'/></c:when>
<c:when test='${category eq "findpw"}'><c:set var='title' value='비밀번호 찾기'/></c:when>
<c:when test='${category eq "join"}'><c:set var='title' value='회원가입'/></c:when>
<c:when test='${category eq "mypage"}'><c:set var='title' value='마이페이지'/></c:when>
</c:choose>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>한울핏 ${title}</title>
    <link rel="icon" type="image/x-icon" href='<c:url value="/assets/favicon.ico"/>' />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" ></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href='<c:url value="/css/styles.css"/>' rel="stylesheet" />
    <link href='<c:url value="/css/common.css?<%=new java.util.Date()%>"/>' rel="stylesheet" />
    <link href='<c:url value="/css/member.css?<%=new java.util.Date()%>"/>' rel="stylesheet" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">		
	<script src='https://code.jquery.com/jquery-3.6.3.min.js'></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script src='<c:url value="/"/>js/common.js?<%=new java.util.Date()%>'></script>
</head>
<body>
<!-- 타일 조각 붙이는 것 : 헤더, 푸터가 붙지 않는 부분-->
<div id='container'>
<tiles:insertAttribute name='container'/>
</div>
</body>
</html>