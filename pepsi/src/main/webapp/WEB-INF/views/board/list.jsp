<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn {	color: #ffffff;}
#list-top ul {	padding: 0 0;}
.txt-left {	text-align: left;}
.border, .border div {	box-sizing: border-box}
.col-xl-3 {	width: 18.2% !important;}
.page-list{display:flex;justify-content: center;line-height: 36px;}
.page-list a, .page-list span {	width: 38px;
								height: 38px;
								margin: 1px;
								border: 1px solid transparent;
}
.page-list a:hover {cursor: pointer;background-color: rgb(51, 103, 214, 0.1);}
.h-title {cursor: pointer;}
.page-list span {
	border: 1px solid #3367d6;
	color: #3367d6;
}
.btnSet { margin: 20px auto; }
</style>
</head>
<body>
<!-- Page Header-->
<jsp:include page="/WEB-INF/views/board/board_header.jsp">
	<jsp:param value="헬스커뮤니티" name="subtitle" />
</jsp:include>
<!-- Post Content-->
<article class="mb-4">

<div class="container px-4 px-lg-5">
	<form action="list.bo" method='post'>
		<div class='btn-toolbar my-3 justify-content-between'>
			<div class='d-flex align-items-center gap-1'>
				<select name='search' class='w-px100'>
						<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</option>
						<option value='title'
							${page.search eq 'title' ? 'selected' : ''}>제목</option>
						<option value='content'
							${page.search eq 'content' ? 'selected' : ''}>내용</option>
						<option value='writer'
							${page.search eq 'writer' ? 'selected' : ''}>작성자</option>
				</select>
				<input type='text' value='${page.keyword}' name='keyword' class='w-px250'>
				<a class='btn btn-primary arrow-middle' onclick="$('form').submit()">검색</a>
			</div>
			<div>
				<select class='w-px100' name='pageList'>
						<c:forEach var='i' begin='1' end='5'>
							<option value='${10*i}'>${10*i}개씩</option>
						</c:forEach>
				</select>
				
				<select class='w-px120' name='viewType'>
						<option value='list'>리스트</option>
						<option value='grid'>그리드</option>
				</select>
				
				<c:if test="${not empty loginInfo}">
					<a class="btn btn-primary" href="new.bo" role="button">새글쓰기</a>

				</c:if>
			</div>
		</div>
		<input type='hidden' name='curPage' value='1'>
		<input type='hidden' name='id'>
	</form>


	<c:if test='${page.viewType eq "grid" }'>
		<div class="row">
			<c:forEach items="${page.list }" var="vo">

				<div class="col-xl-3 col-md-6 mb-5">
					<div class="card border-left-primary shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div
										class="h5 font-weight-bold text-primary text-uppercase mb-3">
										<a onclick="fn_info( ${vo.id} )">${vo.title}</a>
									</div>
									<div class=" mb-0 font-weight-bold text-gray-800 mb-1">${vo.name}</div>
									<div class="mb-0 font-weight-bold text-gray-800">${vo.writedate}</div>
								</div>
								<div class="col-auto">
									<!-- <i class="text-primary fa-solid fa-paperclip "></i> -->
									<c:if test="${vo.filecnt gt 0}">
									<span><i class="text-danger fa-solid fa-paperclip"></i></span>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</c:if>

	<c:if test='${page.viewType eq "list" }'>
		<table class='table table-hover text-center'>
			<colgroup>
				<col width='100px'>
				<col>
				<col width='140px'>
				<col width='140px'>
				<col width='100px'>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items='${page.list}' var='vo'>
				<tr>
					<td>${vo.no}</td>
					<td class='txt-left'><a class='h-title' onclick="fn_info( ${vo.id} )">${vo.title}</a>
						<c:if test='${vo.filecnt > 0}'>
							<span><i class="text-danger fa-solid fa-paperclip"></i></span>
						</c:if> <%-- <span>${vo.filecnt eq 0 ? '' : '<i class="font-img-b fa-solid fa-paperclip"></i>' }</span> --%>
					</td>
					<td>${vo.name}</td>
					<td>${vo.writedate}</td>
					<td>${vo.readcnt}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</div>

<div class='btnSet text-center'>
	<jsp:include page="/WEB-INF/views/include/page.jsp"></jsp:include>
</div>




<script>
$('[name=pageList], [name=viewType]').change( function(){
	$('form').submit();
});
$('[name=pageList]').val( ${page.pageList} ).prop('selected', true);
$('[name=viewType]').val( '${page.viewType}' ).prop('selected', true);


function fn_info ( id ){
	console.log(id)
	$('[name=id]').val(id);
	$('[name=curPage]').val(${page.curPage});
	$('form').attr('action', 'info.bo');
	$('form').submit();
}

</script>

</article>



</body>
</html>