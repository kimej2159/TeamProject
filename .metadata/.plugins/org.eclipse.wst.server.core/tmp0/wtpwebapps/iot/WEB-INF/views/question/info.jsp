<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새로운 질문글 작성하기</title>
</head>
<body>
<style type="text/css">
tabld td { tabld-align: left; }
#comment-regist, #comment-list {width: 600px; margin: 0 auto; text-align: left;}
#comment, textarea.modify {height: 60px; margin-top: 5px;}
#comment-regist div { display: flex; justify-content: space-between;}
</style>
</head>
<body>
<h3>질문글 안내</h3>
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

</table>
<div class='btnSet'>
<a class='btn-fill' id='list'>목록으로</a>
<c:if test="${vo.writer eq loginInfo.id}">
<a class='btn-fill' id='modify'>정보수정</a>
<a class='btn-fill' id='delete'>정보삭제</a>
</c:if>
</div>

<div id='comment-regist'>
	<div><span>댓글작성</span>
		<a class='btn-fill-s' id='regist'>댓글등록</a>
	</div>
	<textarea id='comment' class='full'></textarea>

</div>
<div id='comment-list'>


</div>

<form method='post' action='download.qu'>
<input type='hidden' name='file'>
<input type='hidden' name='id' value='${vo.id}'>
<input type='hidden' name='curPage' value='${page.curPage }'>
<input type='hidden' name='search' value='${page.search }'>
<input type='hidden' name='keyword' value='${page.keyword }'>
<input type='hidden' name='pageList' value='${page.pageList }'>
<input type='hidden' name='viewType' value='${page.viewType }'>
</form>


<script>
//댓글
$('#regist').click(function(){
	if( $('#comment').val()==''){
		alert('댓글을 입력하세요');
	}else if( ${empty loginInfo}){
		alert('로그인이 필요합니다');
	}else{
		$.ajax({
			url: 'question/comment/insert',		//question_comment_insert.bo의 형태를 경로의 형태로 작성함
			data: { question_id: ${vo.id}, content: $('#comment').val(), writer: '${loginInfo.id}'},
			success: function( response ){
				console.log( response )
				if( response){
					alert('댓글이 등록되었습니다');
					$('#comment').val('');
					comment_list();
				}else{
					alert("댓글 등록에 실패하였습니다");
				}
			},error: function(req, text){
				alert(text+':'+req.status);
			}
		});
	}
});

comment_list();

//댓글 목록 조회
function comment_list(){
	$.ajax({
		url: 'question/comment/list/${vo.id}',
		success: function( response ){
			$('#comment-list').html( response );
		}, error: function(req, text){
			alert(text+':'+req.status)
		}
	});
}


//질문글 목록, 삭제, 수정 
$('#list, #delete, #modify').click(function(){	
	$('form').attr('action', $(this).attr('id') + '.qu')
	if( $(this).attr('id')=='delete' ){
		if( confirm('삭제하시겠습니까?') ){
			$('form').submit();			
		}
	}else
		$('form').submit();
});

</script>


</body>
</html>