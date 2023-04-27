<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 목록</h3>

<form method='post' action='list.bo'>
<div id='list-top' class='w-px1200'>
<ul>
	<li><select name='search' class='w-px100'>
		<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</option>
		<option value='title' ${page.search eq 'title' ? 'selected' : ''}>제목</option>
		<option value='content' ${page.search eq 'content' ? 'selected' : ''}>내용</option>
		<option value='writer' ${page.search eq 'writer' ? 'selected' : ''}>작성자</option>
		</select>
	</li>
	<li><input type='text' value='${page.keyword}' name='keyword' class='w-px250'></li>
	<li><a class='btn-fill' onclick="$('form').submit()">검색</a></li>
</ul>
<ul>
	<li><select class='w-px100' name='pageList'>
		<c:forEach var='i' begin='1' end='5'>
		<option value='${10*i}'>${10*i}개씩</option>
		</c:forEach>
		</select>
	</li>
	<li><select class='w-px120' name='viewType'>
		<option value='list'>리스트</option>
		<option value='grid'>그리드</option>
		</select>
	</li>
	<!-- 로그인된 경우 새글쓰기 가능 -->
	<c:if test="${not empty loginInfo }">
	<li><a class='btn-fill' href='new.bo'>새글쓰기</a></li>
	</c:if>
</ul>

</div>
<input type='hidden' name='curPage' value='1'>
<input type='hidden' name='id'>
</form>

<script>
$('[name=pageList], [name=viewType]').change( function(){
	$('form').submit();
});
$('[name=pageList]').val( ${page.pageList} ).prop('selected', true);
$('[name=viewType]').val( '${page.viewType}' ).prop('selected', true);


function fn_info(id){
	console.log(id)
	$('[name=id]').val(id);
	$('[name=curPage]').val( ${page.curPage});
	$('form').attr('action', 'info.bo');
	$('form').submit();
}
</script>	

<c:if test='${page.viewType eq "grid"}'>
<ul class='grid w-px1200'>
	<c:forEach items="${page.list}" var="vo">
	<li><div><a onclick="fn_info( ${vo.id})" >${vo.title }</a></div>
		<div>${vo.name }</div>
		<div>${vo.writedate}
			<c:if test="${vo.filecnt gt 0}">
			<span style='float: right;'><i class="font-img-b fa-solid fa-paperclip"></i></span>
			</c:if>
		</div>
	</li>
	</c:forEach>
</ul>
</c:if>

<c:if test='${page.viewType eq "list"}'>
<table class='w-px1200 tb-list'>
<colgroup>
	<col width='100px'>
	<col>
	<col width='140px'>
	<col width='140px'>
</colgroup>
<tr><th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
</tr>
<c:forEach items='${page.list}' var='vo'>
<tr><td>${vo.no }</td>
	<td class='txt-left'><a onclick="fn_info( ${vo.id} )">${vo.title }</a>
	<c:if test='${vo.filecnt > 0}'>
	<span><i class="font-img-b fa-solid fa-paperclip"></i></span>
	</c:if>
<%-- 	<span>${vo.filecnt eq 0 ? '' : '<i class="font-img-b fa-solid fa-paperclip"></i>'}</span> --%>
	</td>
	<td>${vo.name }</td>
	<td>${vo.writedate }</td>
</tr>
</c:forEach>
</table>
</c:if>
<div class='btnSet'>
<jsp:include page="/WEB-INF/views/include/page.jsp"/>
</div>
</body>
</html>