<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<style>
.modify { display: none;  width: calc(100% - 22px) }
</style>



<div  style="width: 800px;">
<c:forEach items='${list}' var='vo' varStatus='s'>
${s.first ? '<hr>' : ''}
<div data-id='${vo.id}' class="w-100" >${vo.name} [${vo.writedate} ]
	<c:if test='${vo.writer eq loginInfo.id}'>
	<span style='float: right;'>
		<a class='btn btn-primary modify-save'>수정</a>
		<a class='btn btn-primary delete-cancel'>삭제</a>
	</span>
	</c:if>
	<div class='view'>${fn: replace(  fn:replace(vo.content, lf, '<br>'), crlf, '<br>')}</div>
	<textarea class='modify'></textarea>
</div>
<hr>
</c:forEach>

</div>

<script>
$('.modify-save').click(function(){
	var _div = $(this).closest('div');
		
	// 수정버튼
	if( $(this).text()=='수정'){
		_div.children('textarea.modify').val( _div.children('.view').html().replace(/<br>/g, '\n') ) ;
		modifyStatus( _div, true );
	}else{
	//저장버튼
		$.ajax({
			type: 'post',
			contentType: 'application/json',
			url: 'board/comment/update', 
			data: JSON.stringify( { id: _div.data('id'), content: _div.children('textarea.modify').val() } ),
			success: function( response ){
				alert( '댓글변경 ' + response);
				comment_list();
			}
		})
		
	}
});

$('.delete-cancel').on('click', function(){
	// 삭제버튼
	var _div = $(this).closest('div');
	if( $(this).text()=='취소')
		// 취소버튼
		modifyStatus( _div, false );	
	else{
		// 삭제버튼
		if( confirm('삭제??????')){
			$.ajax({
				url: 'board/comment/delete/' + _div.data('id'),
				success: function(){
					comment_list();
				}
			})
		}
	}
});


function modifyStatus( _div, is ){
	_div.children('textarea.modify').css('display', is?'block':'none');
	_div.children('.view').css('display', is?'none':'block');
	
	//버튼바뀌는것
	_div.find( '.modify-save' ).text( is?'저장':'수정' );
	_div.find( '.delete-cancel' ).text( is?'취소':'삭제' );
	
}


</script>
