<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 목록</title>
<style>
.btnSet { margin: 20px auto; }
.page-list{display: flex; justify-content: center; line-height: 36px;}
.page-list a, .page-list span{text-align: center;
	width: 38px; height: 38px; margin: 1px;
	border: 1px solid transparent;
}
.page-list span{border:1px solid #33676d; color: #3367d6;}
.page-list a:hover{cursor: pointer; background-color: rgb(51, 103, 214, 0.1);}
/* .h-title {cursor: pointer;} */

.wd{
width: 100px;

}
.centerr{
text-align: center;
}

.btn {
	color: #ffffff;
}
</style>
</head>
<body>
<!-- Page Header-->
 <jsp:include page="/WEB-INF/views/notice/notice_header.jsp">
	<jsp:param value="공지사항" name="subtitle"/>
</jsp:include>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 ">
                
<form method='post' action='list.no'>          
    <div id='list-top'>
  			<select name='search' class='wd'>
		    	<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</option>
				<option value='title'
					${page.search eq 'title' ? 'selected' : ''}>제목</option>
				<option value='content'
					${page.search eq 'content' ? 'selected' : ''}>내용</option>
<!-- 				<option value='writer' -->
<%-- 					${page.search eq 'writer' ? 'selected' : ''}>작성자</option> --%>
		    </select>
	  <input type='text' name='keyword' value='${page.keyword}' class='w-250px'>
	  <a class='btn btn-primary arrow-middle' onclick='$("form").submit()'>검색</a>
   
			<!--관리자로 로그인이 된 경우에만 새 글 쓰기 가능 -->
			<c:if test="${loginInfo.admin eq 'Y'}">
				<a class="btn btn-primary" href="regist.no" role="button" >새글쓰기</a>
			</c:if>
		
	</div>
	<input type='hidden' name='curPage' value='1'>
</form> 

	<table class='table table-hover'>
	<colgroup>
		<col width='100px'>
		<col width='300px'>
		<col width='100px'>
		<col width='100px'>
	</colgroup>
	<tr><th class='centerr'>번호</th>
		<th class='centerr'>제목</th>
		<th class='centerr'>작성자</th>
		<th class='centerr'>작성일</th>
	</tr>
	<c:forEach items='${page.list}' var='vo'>
	<tr><td class='centerr'>${vo.no}</td>
		<td><a href='info.no?id=${vo.id}'>${vo.title}</a></td>
		<td class='centerr'>${vo.name}</td>
		<td class='centerr'>${vo.writedate}</td>
	</tr>
	</c:forEach>
	</table>
    <div class='btnSet'>
    <jsp:include page="/WEB-INF/views/include/page.jsp" />
    </div>            
                        
            </div>
        </div>
    </div>
</article>

</body>
</html>