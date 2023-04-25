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
.modify{display: none; width:calc(100%-22px)}
</style>
</head>
<body>
<h3>댓글 목록</h3>
<c:forEach items='${list}' var='vo' varStatus='s'>
${s.first ? '<hr>' : ''}
<div data-id='${vo.id}' >${vo.name} [${vo.writedate}]
	<c:if test='${vo.writer eq loginInfo.id }'>
	<span style='float:right;'>
		<a class='btn-fill-s modify-save'>수정</a>
		<a class='btn-fill-s delete-cancel'>삭제</a>
	</span>
	</c:if>
	<div class='view'>${fn:replace(fn:replace(vo.content, crlf, '<br>'), crlf,'<br>')}</div>
	<textarea class='modify'></textarea>
</div>
<hr>
</c:forEach>

<script>
$('.modify-save').click(function(){
	
	//수정 버튼
	var _div = $(this).closest('div');
	if($(this).text()=='수정'){
	_div.children('textarea.modify').val( _div.children('.view').html().replace(/<br>/g, '\n') ) ;
	modifyStatus( _div, true);
	}else{
	//저장
		$.ajax({
			type: 'post',
			contentType: 'application/json',
			url: 'question/comment/update', 
			data: JSON.stringify( { id: _div.data('id'), content: _div.children('textarea.modify').val() } ),
			success: function( response ){
				alert( '댓글변경 ' + response);
				comment_list();
			}
		})
	
	}
	
});
$('.delete-cancel').on('click', function(){
	var _div = $(this).closest('div');
	if( $(this).text()=='취소' )
		//취소버튼
		modifyStatus( _div, false );
	else{
		//삭제버튼
		if( confirm('삭제?') ){
			$.ajax({
				url: 'question/comment/delete/'+ _div.data('id'),
				success: function(){
					comment_list();
				}
			});
		}
	}
});

function modifyStatus( _div, is ){
	_div.children('textarea.modify').css('display', 'block');
	_div.children('.view').css('display', is?'none':'block');
	_div.find( '.modify-save').text( is? '저장':'수정');
	_div.find( '.delete-cancle').text( is? '취소':'삭제');
	
}
</script>


</body>
</html>