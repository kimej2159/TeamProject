<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#preview img, .preview img {max-height: 30px;}
.btn{ color: #ffffff;}
.h1title{font-size: 26px;}
input[type=file].attach-file {display: none;}
</style>
</head>
<body>
<!-- Page Header-->
<jsp:include page="/WEB-INF/views/board/board_header.jsp">
	<jsp:param value="헬스커뮤니티" name="subtitle"/>
</jsp:include>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10">
                        
	
	<h1 class='h1title'>내글수정</h1>
	<form method='post' action="info.bo" enctype='multipart/form-data'>
		<table class='table'>
		<colgroup>
			<col width='120px'>
			<col>
		</colgroup>
		<tr><th class="text-center">제목</th>
			<td><input type='text' value='${vo.title}' name='title' title='제목' class='form-control chk'></td>
		</tr>
		<tr><th class="text-center">내용</th>
			<td><textarea name="content" title="내용" class='form-control chk' style="height: 500px">${vo.content}</textarea></td>
		</tr>
		<tr><th class="text-center">첨부파일</th>
			<td class='align'>
				<c:forEach items="${vo.fileInfo}" var="f">
				<div class='align' data-file='${f.id}'>
				<label>
					<input type='file' name='file' class='attach-file'>
					<a><i class="fa-solid fa-file-circle-plus"></i></a>
				</label>
				<span class='file-name'>${f.filename}</span>
				<span class='preview'></span>
				<a class='delete-file' style='display:inline' ><i class="fa-regular fa-trash-can"></i></a>
				</div>
				</c:forEach>
				<div class='align'>
				<label>
					<input type='file' name='file' class='attach-file'>
					<a><i class="fa-solid fa-file-circle-plus"></i></a>
				</label>
				<span class='file-name'></span>
				<span class='preview'></span>
				<a class='delete-file'><i class="fa-regular fa-trash-can"></i></a>
				</div>
			</td>
		</tr>
		
		</table>
		<input type='hidden' name='removed'>
		<input type='hidden' name='id' value='${vo.id}'>
		<input type='hidden' name='curPage' value='${page.curPage}'>
		<input type='hidden' name='search' value='${page.search}'>
		<input type='hidden' name='keyword' value='${page.keyword}'>
		<input type='hidden' name='pageList' value='${page.pageList}'>
		<input type='hidden' name='viewType' value='${page.viewType}'>
		
		
	
	</form>
	<div class="d-grid gap-2 d-md-block text-center">
	  <a class="btn btn-primary"  id="save" >저장</a>
	  <a class="btn btn-secondary "  id="cancel" >취소</a>
	</div>
        
               
            </div>
        </div>
    </div>
</article>




        
<script>

<c:forEach items="${vo.fileInfo}" var="f" varStatus="s">
if ( isImage( '${f.filename}')){
	$('.preview').eq(${s.index}).html('<img src="${f.filepath}">');}
</c:forEach>

$('#cancel').click( function(){
	$('form').submit();
})
$('#save').on('click', function(){
	if( emptyCheck() )
		$('form').attr('action', 'update.bo').submit();
});



</script>
</body>
</html>