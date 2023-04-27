<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<c:choose>
<c:when test='${category eq "login"}'><c:set var='title' value='로그인'/></c:when>
</c:choose>
<head>
<meta charset="UTF-8">
<title>지능형 IoT ${title}</title>
<link rel='icon' href='<c:url value="/"/>assets/favicon.ico'>
<link rel='stylesheet' type='text/css' 
		href='<c:url value="/"/>css/common.css?<%=new java.util.Date()%>'>
<link rel='stylesheet' type='text/css' 
		href='<c:url value="/"/>css/member.css?<%=new java.util.Date()%>'>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">		
<script src='https://code.jquery.com/jquery-3.6.3.min.js'></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src='<c:url value="/"/>js/common.js?<%=new java.util.Date()%>'></script>
</head>
<body>
<div id='container'>
<tiles:insertAttribute name='container'/>
</div>
</body>
</html>