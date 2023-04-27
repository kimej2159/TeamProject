<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<li>
<select class='w-px300' id='kind'>
<option value=''>품종선택</option>
<c:forEach items='${list.item}' var='vo'>
<option value='${vo.kindCd}'>${vo.knm}</option>
</c:forEach>
</select>
</li>

<script>
$('#kind').change(function(){
	animal_list(1);
})
</script>