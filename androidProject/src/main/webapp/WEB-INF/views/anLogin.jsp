<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>    
<%@ page import="com.hanul.app.dto.MemberDTO" %>

<%
	Gson gson = new Gson();
	String json = gson.toJson((MemberDTO)request.getAttribute("anLogin"));

	out.print(json);
	
%>