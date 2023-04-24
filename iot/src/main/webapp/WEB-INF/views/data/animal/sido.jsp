<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<li>
<select class='w-px160' id='sido'>
	<option value=''>시도선택</option>
	<c:forEach items='${list.item }' var='vo'>
	<option value='${vo.orgCd }'>${vo.orgdownNm }</option>
	</c:forEach>
</select>
</li>
<script>
$('#sido').change(function(){
	animal_sigungu();
	animal_list( 1 );
})
function animal_sigungu(){
	$('#sigungu').closest('li').remove();
	if( $('#sido').val()=='' ) return;
	
	$.ajax({
		url: 'data/animal/sigungu',
		data: { sido: $('#sido').val() },
		success: function( response ){
			console.log( response );
			$('#sido').closest('li').after( response )
		}
	})
}
</script>