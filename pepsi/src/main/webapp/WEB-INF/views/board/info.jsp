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
</style>
</head>
<body>

<!-- Page Header-->
<jsp:include page="/WEB-INF/views/board/board_header.jsp">
	<jsp:param value="방명록 목록" name="subtitle" />
</jsp:include>
	<h1 class='h1title'> 새글쓰기</h1>
	
	
<article>
	
	<table class='w-px1200'>
	<colgroup>
		<col width='140px'>
		<col>
		<col width='140px'>
		<col width='140px'>
		<col width='100px'>
		<col width='100px'>
	</colgroup>
<tr><th>제목</th>
	<td colspan='5'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th>작성일자</th>
	<td>${vo.writedate}</td>
	<th>조회수</th>
	<td>${vo.readcnt}</td>
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
		<a class='download' data-file='${f.id}'><i class="font-img-b fa-solid fa-file-arrow-down"></i></a>
		</span>
		<span class='preview'></span>
	</div>
	</c:forEach>
	</td>
</tr>
</table>

<div class='btnSet'>
<a class='btn-fill' id='list'>목록으로</a>
<c:if test="${vo.writer eq loginInfo.id}">
<a class='btn-fill' id='modify'>정보수정</a>
<a class='btn-fill' id='delete'>정보삭제</a>
</c:if>
</div>


</article>



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
		if(confirm('정말정말정말 삭제?')){
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
		alert('댓글 등록하려면 로그인하세여~!');
		location = 'login'
	}else{
		$.ajax({
			url: 'board/comment/insert', // board_comment_insert.bo이런형태를 경로의 형태로 쓴것
			data: { board_id: ${vo.id}, content: $('#comment').val(), writer: '${loginInfo.id}' },
			success: function( response ){
				console.log( response )
				if(response){
					alert('댓글등록!');
					$('#comment').val('');
					comment_list();
				}else{
					alert('댓글실패!');
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