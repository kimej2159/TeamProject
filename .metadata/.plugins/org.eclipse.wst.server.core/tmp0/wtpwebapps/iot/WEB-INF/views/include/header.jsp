<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<header>
	<nav>
		<ul>
			<li><a href='<c:url value="/"/>'><img src='<c:url value="/"/>imgs/hanul.logo.png'></a></li>
			<li><a ${category eq 'cu' ? "class='active'" : ''} href='<c:url value="/"/>list.cu'>고객관리</a></li>
			<li><a ${category eq 'hr' ? "class='active'" : ''} href='<c:url value="/"/>list.hr'>사원관리</a></li>
			<li><a ${category eq 'no' ? "class='active'" : ''} href='<c:url value="/"/>list.no'>공지사항</a></li>
			<li><a ${category eq 'bo' ? "class='active'" : ''} href='<c:url value="/"/>list.bo'>방명록</a></li>
			<li><a ${category eq 'qu' ? "class='active'" : ''} href='<c:url value="/"/>list.qu'>질문과 답변</a></li>
			<li><a ${category eq 'da' ? "class='active'" : ''} href='<c:url value="/"/>list.da'>공공데이터</a></li>
			<li><a ${category eq 'vi' ? "class='active'" : ''} href='<c:url value="/"/>visual/list'>시각화</a></li>
			
		</ul>
	</nav>
	<div>
		<ul>
		<!-- 로그인하지 않은 경우 -->
		<c:if test='${empty loginInfo}'>
			<li><a class='btn-fill' href='login'>로그인</a></li>
			<li><a class='btn-fill' href='member'>회원가입</a></li>
		</c:if>
		<!-- 로그인된 경우 -->
		<c:if test='${not empty loginInfo}'>
			<c:choose>
				<c:when test='${empty loginInfo.profile}'>
					<i class="font-profile fa-regular fa-circle-user"></i>
				</c:when>
				<c:otherwise>
					<img class='profile' src='${loginInfo.profile}'>
				</c:otherwise>
			</c:choose>
		
			<li><strong>${loginInfo.name}</strong> 님</li>
			<li><a class='btn-empty' href='changepw'>비밀번호변경</a></li>
			<li><a class='btn-fill' href='logout'>로그아웃</a></li>
		</c:if>
		</ul>
	</div>
</header>
<style>
.font-profile { font-size: 50px; }
.profile { width:50px; height: 50px;  border-radius: 50%; }
header { 
	border-bottom: 1px solid #aaa;
	display: flex; align-items: center; justify-content: space-between;
	padding: 0 100px;
}
header nav ul { font-size: 18px; font-weight: bold; }
header ul { display: flex; }
header nav ul li:not(:first-child) { margin-left: 50px }
header nav a:hover, header nav a.active { color:#0730fa  }
</style>
