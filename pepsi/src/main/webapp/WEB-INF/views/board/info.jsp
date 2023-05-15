<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.h1title{font-size: 26px;}
table td { text-align: left; }
.align{display: flex; align-items: center;}
.align * { margin-right: 5px;}
.btnSet{ 	margin: 20px auto; }
#preview img, .preview img {max-height: 30px;}
.btn {	color: #ffffff;}
#comment-regist {align-content: center;}
</style>
</head>
<body>

<!-- Page Header-->
<jsp:include page="/WEB-INF/views/board/board_header.jsp">
	<jsp:param value="헬스커뮤니티" name="subtitle" />
</jsp:include>
	<h1 class='h1title'>선택한 글</h1>
	
	
<article>
	<div class="container px-4 px-lg-5">
	<table class='table'>
	<colgroup>
		<col width='140px'>
		<col>
		<col width='140px'>
		<col width='140px'>
		<col width='100px'>
		<col width='40px'>
	</colgroup>
<tr><th>제목</th>
	<td colspan='5'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th class="text-end">작성일자</th>
	<td class="text-end">${vo.writedate}</td>
	<th class="text-end">조회수</th>
	<td class="text-end">${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5'>${fn: replace(vo.content, crlf, '<br>')}</td>
</tr>
<tr><th>첨부파일</th>
	<td colspan='5'>
	<c:forEach items='${vo.fileInfo}' var='f'>
	<div class='align'>
		<span>${f.filename}
		<%-- <a href='download.bo?id=${f.id}'><i class="font-img-b fa-solid fa-file-arrow-down"></i></a> --%>
		<a class='download' data-file='${f.id}'><i class="fa-solid fa-file-arrow-down"></i></a>
		</span>
		<span class='preview'></span>
	</div>
	</c:forEach>
	</td>
</tr>
</table>

<div class='btn-toolbar gap-2 justify-content-center'>
<a class='btn btn-primary' id='list'>목록으로</a>
<c:if test="${vo.writer eq loginInfo.id}">
<a class='btn btn-primary' id='modify'>정보수정</a>
<a class='btn btn-primary' id='delete'>정보삭제</a>
</c:if>
</div>

<div id='comment-regist ' class="d-flex justify-content-center ">
	<div style="width: 800px;">
		<div class="w-100 justify-content-between  d-flex align-items-center mb-1">
			<span>댓글작성</span>
			<a class='btn btn-primary' id='regist'>댓글등록</a>
		</div>
		<textarea id='comment' class="w-100" style="height:100px; box-sizing: border-box;"></textarea>
	</div>
</div>


<div id='comment-list'class="d-flex justify-content-center ">
</div>

</div>
</article>

<form method='post' action='download.bo'>
<input type='hidden' name='file'>
<input type='hidden' name='id' value='${vo.id}'>
<input type='hidden' name='curPage' value='${page.curPage}'>
<input type='hidden' name='search' value='${page.search}'>
<input type='hidden' name='keyword' value='${page.keyword}'>
<input type='hidden' name='pageList' value='${page.pageList}'>
<input type='hidden' name='viewType' value='${page.viewType}'>

</form>


<script>
<c:forEach items="${vo.fileInfo}" var='f' varStatus='state'>
if( isImage( '${f.filename}' ) ){
	$('.preview').eq( ${state.index}).html( '<img src="${f.filepath}">' )
	
}
</c:forEach>


$('.download').click(function (){
	$('[name=file]').val( $(this).data('file'))
	$('form').submit();
});

$('#list, #delete, #modify').click(function(){
	
	$('form').attr('action', $(this).attr('id') + '.bo')
	if( $(this).attr('id') == 'delete' ){
		if(confirm('삭제하시겠습니까?')){
			$('form').submit();
		}
	}else
		$('form').submit();
});

$('#regist').click(function(){
	if( $('#comment').val()==''){
		alert('댓글을 입력하세요!');
		$('#comment').focus();
	}else if( ${empty loginInfo}){
		alert('댓글 등록하려면 로그인하세요');
		location = 'login'
	}else{
		$.ajax({
			url: 'board/comment/insert', // board_comment_insert.bo이런형태를 경로의 형태로 쓴것
			data: { board_id: ${vo.id}, content: $('#comment').val(), writer: '${loginInfo.id}' },
			success: function( response ){
				console.log( response )
				if(response){
					alert('성공적으로 댓글을 등록하였습니다');
					$('#comment').val('');
					comment_list();
				}else{
					alert('댓글 등록에 실패하였습니다');
				}
			}, error: function(req, text){
				alert(text+':'+req.status);
			}
		});
	}
});

comment_list();

// 댓글목록조회
function comment_list(){
	$.ajax({
		url: 'board/comment/list/${vo.id}',
		success: function( response ){
			$('#comment-list').html( response );
		},error: function(req, text){
			alert(text+':'+req.status);
		}
		
		
	});
}


</script>
</body>
</html>