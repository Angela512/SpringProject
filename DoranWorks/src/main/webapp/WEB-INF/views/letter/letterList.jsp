<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<c:if test="${!empty user }">
<div class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</div>
</c:if>
<!-- 내용 끝 -->