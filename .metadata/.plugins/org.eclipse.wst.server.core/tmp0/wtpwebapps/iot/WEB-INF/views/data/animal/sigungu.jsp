<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<li>
<select class='w-px160' id='sigungu'>
	<option value=''>시군구선택</option>
	<c:forEach items='${list.item }' var='vo'>
	<option value='${vo.orgCd }'>${vo.orgdownNm }</option>
	</c:forEach>
</select>
</li>
<script>
$('#sigungu').change(function(){
	animal_shelter();
	animal_list(1);
});
function animal_shelter(){
	$('#shelter').closest('li').remove();
	if( $('#singungu').val()=='')	return;
	
	$.ajax({
		url: 'data/animal/shelter',
		data: { sido:$('#sido').val(), sigungu:$('#sigungu').val()},
		success: function( response ){
			console.log( response );
			$('#sigungu').closest('li').after( response );
		}
	});
	
}
</script>







