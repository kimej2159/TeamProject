<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='<c:url value="/"/>imgs/hanul.logo.png'></a>
	<div class='txt-left'>
		<h3>내부 서버오류가 발생했습니다</h3>
		${msg}
		<h3>랄라</h3>
	</div>
</div>
</body>
</html>