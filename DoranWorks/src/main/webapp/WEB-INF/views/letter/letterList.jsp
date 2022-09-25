<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
#ck_letter{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/letter.list.important.js"></script>
<div class="page-main">
	<div class="content">
	<h2>쪽지</h2>
	<div class="sub_nav" id="sub_nav1">
		<ul>
			<li><a href="main.do?letter_type=0">전체쪽지함</a></li>
			<li><a href="main.do?letter_type=1">받은쪽지함</a></li>
			<li><a href="main.do?letter_type=2">보낸쪽지함</a></li>
			<li><a href="main.do?letter_type=3">내게쓴쪽지함</a></li>
			<li id="type4"><a href="main.do?letter_type=4"> 중요쪽지함</a></li>
		</ul>
	</div>
	
	<div id="letter-search">
	<form action="main.do" id="search_form" class="letter-form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>보낸사람</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>받는사람</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>내용</option>
				</select>
			</li>
			
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword }">
			</li>
			
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='main.do'">
			</li>
		</ul>
	</form>
	</div>
	
	<div class="letter-count">
	<c:if test="${param.letter_type==0 || param.letter_type==null}">전체쪽지함 | </c:if>
	<c:if test="${param.letter_type==1}">받은쪽지함 | </c:if>
	<c:if test="${param.letter_type==2}">보낸쪽지함 | </c:if>
	<c:if test="${param.letter_type==3}">내게쓴쪽지함 | </c:if>
	<c:if test="${param.letter_type==4}">중요쪽지함 | </c:if>
	${count }
	</div>
	
	
	<input type="hidden" value="${param.letter_type}" id="lt_type" name="lt_type">
	
	<c:if test="${!empty user }">
	<div class="align-right">
		<input type="button" value="글쓰기" id="write_button" onclick="location.href='write.do'">
	</div>
	</c:if>
	
	<c:if test="${count==0 }">
		<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count>0 }">
	<div id="list_rd">
	<br>
	<input type="button" class="list_button" value="읽음" id="list_read">
	<input type="button" class="list_button" value="삭제" id="list_delete">
	</div>
	<table class="letter_table">
		<c:forEach var="letter" items="${list }" varStatus="status">
		<tr>
			<td width="20">
			<input type="checkbox" class="lt_num" value="${letter.lt_num }" data-num="${letter.lt_num}">
			</td>
			<td width="55">
			<c:if test="${letter.lt_important==0 }">
			<img class="output_important" src="${pageContext.request.contextPath}/images/fav01.gif" data-ltnum="${letter.lt_num}" width="40">
			</c:if>
			<c:if test="${letter.lt_important==1 }">
			<img class="output_important" src="${pageContext.request.contextPath}/images/fav02.gif" data-ltnum="${letter.lt_num}" width="40">
			</c:if>
			</td>
			<td>
			<c:if test="${letter.lt_read==0 }">
			<span class="material-symbols-outlined">mail</span>
			<style>
				.material-symbols-outlined {
				  font-variation-settings:
				  'FILL' 0,
				  'wght' 400,
				  'GRAD' 0,
				  'opsz' 48
				}
			</style>
			</c:if>
			
			<c:if test="${letter.lt_read==1 }">
			<span class="material-symbols-outlined">drafts</span>
			
			</c:if>
			</td>
			<td width="150" <c:if test="${letter.lt_read==0}">style="color:blue;"</c:if>>
				<c:if test="${param.letter_type ==0 || param.letter_type == null || param.letter_type == 4}">
					<c:if test="${letter.lt_sender_num == user.mem_num}">${letter.lt_receiver_id }</c:if>
					<c:if test="${letter.lt_sender_num != user.mem_num }">${letter.lt_sender_id }</c:if>
				</c:if>
				
				<c:if test="${param.letter_type==1 }">
				${letter.mem_name}(${letter.lt_sender_id })
				</c:if>

				<c:if test="${param.letter_type==2 || param.letter_type==3 }">
				${letter.lt_receiver_id }
				</c:if>
			 </td>
			<td>
				<a href="detail.do?lt_num=${letter.lt_num }&letter_type=${param.letter_type}" <c:if test="${letter.lt_read==0}">style="color:blue;"</c:if>>${letter.lt_title }</a>
				
			</td>
			<td width="150" <c:if test="${letter.lt_read==0}">style="color:blue;"</c:if>>${letter.lt_date }</td>
		</tr>
		</c:forEach>
	</table>
	
	<div class="align-center">${page }</div>
	</c:if>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<!-- 내용 끝 -->