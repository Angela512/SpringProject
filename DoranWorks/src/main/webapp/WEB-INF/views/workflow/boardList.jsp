<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
#ck_workflow{
	text-decoration: underline !important;
	font-weight: bold;
}
.material-symbols-outlined { 
font-size: 48px; 
transform:translateY(2px)
}

.list_center{
	padding:0 0 0 400px;
}

.new_write{
	display:inline-block;
	padding:25px 0 0  255px;
}
.tag_center{
	padding:0 0 0 400px;
}
.sub_move{
	display:inline-block; 
	padding:20px 0 0 0;
}

.icon{
	display:inline-block;
	padding:5px 0 0 35px;

}



</style>



<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<div class="page-main">
	<h4 style="padding:0 0 0 10px; margin:0 0 30px 0;"><b>전자결재</b></h4>

	
<!-- 	<input type="button" value="목록" onclick="location.href='sign.do'"> -->
	<form action="list.do" id="search_form" 
	                                   method="get">
		<ul class="search">
			<li>
			</li>
			<li>
				<select name="keyfield" id="keyfield" style="height:26px">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>작성자</option>
					<%--
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
					 --%>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword"
				               value="${param.keyword}" >
			</li>
			<li>
				<input id="write_button" type="submit" value="찾기" >
				<input id="write_button" type="button" value="목록" onclick="location.href='list.do'">
				
			</li>
			<br>
		</ul>
	</form>
	<c:if test="${!empty user && count == 0}">
	<div class="new_write">
		<input type="button" value="+ 새 결재 진행 " class="btn btn-outline-primary"
		          onclick="location.href='write.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>	
	</c:if>
	<c:if test="${count > 0}">
	
	<%-- 
	<c:forEach var="lm" items="${list2}">
		${lm.mem_name} ${lm.mem_dpt} <br>
	</c:forEach>
	 --%>
	
	</c:if>
</div>


<c:if test="${count != 0}">
<div class="tag_center">

<input type="button" value="+ 새 결재 진행 " class="btn btn-outline-primary"
		          onclick="location.href='write.do'" style="height:70px; transform:translateY(-20px); font-weight : bold">


<div style="width:200px; height:70px;  display:inline-block; border:4px solid #9370DB; border-radius:0.25rem;">
	<p style="text-align:center; color:#9370DB; margin: 0 0 5px 0; padding:7px 0 0 0;">결재 진행 중인 문서</p>
	<p style="text-align:center;  color:#9370DB; font-size:20px;"><b>${count-countSign}</b></p>
</div>
<div style="width:200px; height:70px;  display:inline-block; border:4px solid #09E81F; border-radius:0.25rem;">
	<p style="text-align:center; color:#09E81F; margin: 0 0 5px 0; padding:7px 0 0 0;">승인 처리된 문서</p>
	<p style="text-align:center;  color:#09E81F; font-size:20px;"><b>${countSign}</b></p>
</div>

</div>
</c:if>

<!-- 내용 끝 -->

<!-- 리스트 시작 -->
<div class="list_center">


<!-- 1. 결재 승인 -->

<c:forEach var="flow" items="${list}">
<c:if test="${flow.flow_state == '결재 승인'}">
<c:if test="${flow.flow_state == '결재 진행중'}">
<div style="width:100px; height:25px; background-color:#9370DB; display:inline-block; ">
	<p style="color : #FFFFFF; transform:translateY(2px)">&nbsp;&nbsp; 결재 진행중</p>
</div>
</c:if>

<c:if test="${flow.flow_state == '결재 승인'}">
<div style="width:100px; height:25px; background-color:#00FF00; display:inline-block; ">
	<p style="color : #FFFFFF; transform:translateY(2px)">&nbsp;&nbsp; 결재 승인</p>
</div>
</c:if>

<br>
<div style="width:700px; height:80px; display:inline-block; border:6px solid gray; border-radius: 50px;">
	<div class="icon">
	<span class="material-symbols-outlined" style="width:100px">content_paste</span>
	<br>
	<p style="width:100px; margin:0px 0px 0px 0px; display:inline-block; font-size:5px; transform:translateY(-12px)"><b>&nbsp;${flow.flow_sort}</b></p>
	</div>
	<a href="detail.do?flow_num=${flow.flow_num}" style="display:inline-block;  transform:translateY(-30px); width:350px"><fmt:formatDate value="${flow.flow_date}" pattern="yyyy/MM/dd"/>&nbsp;&nbsp;&nbsp;&nbsp;<b>${flow.flow_title}</b></a>

<div style="display:inline-block; transform:translateY(-30px)">
<b>	<span class="material-symbols-outlined">
		badge
	</span>
	${flow.mem_name} ${flow.mem_rank}
</b>
</div>
	
</div>
<br>
<br>
<br>
</c:if>
</c:forEach>


<!-- 2. 결재 진행중 -->

<c:forEach var="flow" items="${list}">
<c:if test="${flow.flow_state == '결재 진행중'}">
<c:if test="${flow.flow_state == '결재 진행중'}">
<div style="width:100px; height:25px; background-color:#9370DB; display:inline-block; ">
	<p style="color : #FFFFFF; transform:translateY(2px)">&nbsp;&nbsp; 결재 진행중</p>
</div>
</c:if>

<c:if test="${flow.flow_state == '결재 승인'}">
<div style="width:100px; height:25px; background-color:#00FF00; display:inline-block; ">
	<p style="color : #FFFFFF; transform:translateY(2px)">&nbsp;&nbsp; 결재 승인</p>
</div>
</c:if>

<br>
<div style="width:700px; height:80px; display:inline-block; border:6px solid gray; border-radius: 50px;">
	<div class="icon">
	<span class="material-symbols-outlined" style="width:100px">content_paste</span>
	<br>
	<p style="width:100px; margin:0px 0px 0px 0px; display:inline-block; font-size:5px; transform:translateY(-12px)"><b>&nbsp;${flow.flow_sort}</b></p>
	</div>
	<a href="detail.do?flow_num=${flow.flow_num}" style="display:inline-block;  transform:translateY(-30px); width:350px"><fmt:formatDate value="${flow.flow_date}" pattern="yyyy/MM/dd"/>&nbsp;&nbsp;&nbsp;&nbsp;<b>${flow.flow_title}</b></a>

<div style="display:inline-block; transform:translateY(-30px)">
<b>	<span class="material-symbols-outlined">
		badge
	</span>
	${flow.mem_name} ${flow.mem_rank}
</b>
</div>
	
</div>
<br>
<br>
<br>
</c:if>
</c:forEach>


</div>
<!-- 리스트 끝 -->
<div class="align-center" style="bottom:0"" position: fixed;">${page}</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
