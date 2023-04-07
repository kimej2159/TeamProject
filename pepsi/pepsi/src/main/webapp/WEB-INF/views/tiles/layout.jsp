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
</c:choose>
<link rel='icon' href='<c:url value="/"/>assets/favicon.ico'>


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