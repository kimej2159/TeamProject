<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#preview img, .preview img {max-height: 30px;}
</style>
</head>
<body>
<!-- Page Header-->
<jsp:include page="/WEB-INF/views/board/board_header.jsp">
	<jsp:param value="방명록 새 글" name="subtitle"/>
</jsp:include>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10">
                        
	
	<h3> 새글쓰기</h3>
	<form method='post' action="insert.bo" enctype='multipart/form-data'>
	<input type='hidden' name='writer' value='${loginInfo.id}'>
		<table class='table'>
		<colgroup>
			<col width='120px'>
			<col>
		</colgroup>
		<tr><th class="text-center">제목</th>
			<td><input type='text' name='title' title='제목' class='form-control chk'></td>
		</tr>
		<tr><th class="text-center">내용</th>
			<td><textarea name="content" title="내용" class='form-control chk' style="height: 500px"></textarea></td>
		</tr>
		<tr><th class="text-center">첨부파일</th>
			<td class='align'>
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
		
	
	</form>
	<div class="d-grid gap-2 d-md-block">
	  <a class="btn btn-primary arrow-middle btn-save" >저장</a>
	  <a class="btn btn-light arrow-middle"  href='list.bo'>취소</a>
	</div>
        
               
            </div>
        </div>
    </div>
</article>
        
<script>
$('.btn-save').click(function(){
	if( emptyCheck() ){
		$('form').submit();
	}
})


</script>
</body>
</html>