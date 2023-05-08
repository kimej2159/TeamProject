<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 수정 화면</title>
<style>
.txt-left{
	text-align: left;
}
.centerr{
text-align: center;
}

textarea{
	padding: 0 10px;
	resize: none;
	height: 150px;
	border: 1px solid #b0b0b0;
}
.btn{ color: #ffffff;}
.h1title{font-size: 26px;}
#preview img, .preview img {max-height: 30px;}
input[type=file].attach-file {display: none;}
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
            <div class="col-md-10">
<!-- 			<h1 class='h1title'>공지사항 새글쓰기</h1> -->
            
          <form method='post' action='update.no' enctype='multipart/form-data'>
			<input type='hidden' name='writer' value='${loginInfo.id}'>
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
				<td>
					<c:forEach items="${vo.fileInfo}" var="f">
					<div class='align'  data-file='${f.id}'>
					<label>
						<input type='file' name='file' class='attach-file'>
						<a><i class="fa-solid fa-file-circle-plus"></i></a>
					</label>
					<span class='file-name'>${f.filename}</span>
					<span class='preview'></span>
					<a class='delete-file' 
					style='display:${empty f.filename ? "none" : "inline" }'><i class="fa-regular fa-trash-can"></i></a>
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
			<input type='hidden' name='id' value='${vo.id}'>
			<input type='hidden' name='filename'>
			<input type='hidden' name='removed'>
			
          </form>
		<div class="d-grid gap-2 d-md-block">
	  		<a class="btn btn-primary arrow-middle btn-save" >저장</a>
	  		<a class="btn btn-secondary arrow-middle"  href='javascript:history.go(-1)'>취소</a>
<%-- 	  		<a class="btn btn-secondary arrow-middle"  href='info.no?id=${vo.id}'>취소</a> --%>
		</div>
			</div>
		</div>
	</div>
</article>
<script>
//첨부된 파일이 이미지인 경우만 미리 보기 태그에 img 태그 넣기/ 수정 시 파일이 바뀌었는지 안바뀌었는지 확인할 수 있다
// if( isImage( '${vo.filename}') ) $('#preview').html( "<img src='${vo.filepath}'>" );

<c:forEach items="${vo.fileInfo}" var="f" varStatus="s">
if( isImage( '${f.filename}' ) ){
	$('.preview').eq( ${s.index} )
			.html( '<img src="${f.filepath}">' );
}
</c:forEach>

$('.btn-save').click(function(){
	$('[name=filename]').val( $('.file-name').text() );
	if( emptyCheck() ){
		$('form').submit();
	}
})

</script>
</body>
</html>