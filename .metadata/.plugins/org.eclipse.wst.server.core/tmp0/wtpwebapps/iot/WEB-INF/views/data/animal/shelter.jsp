<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<li>
<select class='w-px250' id='shelter'>
<option value=''>보호소선택</option>
<c:forEach items='${list.item}' var='vo'>
<option value='${vo.careRegNo}'>${vo.careNm}</option>
</c:forEach>
</select>
</li>

<script>
$('#shelter').change(function(){
	animal_list(1);
})
</script>