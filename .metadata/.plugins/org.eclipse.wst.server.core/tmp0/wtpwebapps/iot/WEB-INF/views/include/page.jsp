<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<div class='page-list'>
<c:if test="${page.curBlock > 1 }">
<a onclick="fn_page(1)"><i class="fa-solid fa-angles-left"></i></a>
<a onclick="fn_page( ${page.beginPage-page.blockPage} )"><i class="fa-solid fa-angle-left"></i></a>
</c:if>

<c:forEach var='no' begin='${page.beginPage }' end='${page.endPage }' step='1'>
<c:if test='${ no eq page.curPage }'><span>${no }</span></c:if>
<c:if test='${ no ne page.curPage }'><a onclick='fn_page(${no})'>${no }</a></c:if>
</c:forEach>

<c:if test="${page.totalBlock gt page.curBlock }">
<a onclick='fn_page( ${page.endPage+1} )'><i class="fa-solid fa-angle-right"></i></a>
<a onclick='fn_page( ${page.totalPage} )'><i class="fa-solid fa-angles-right"></i></a>
</c:if>

</div>
<script>
function fn_page( no ){
	$('[name=curPage]').val( no );
	$('form').submit();
}
</script>