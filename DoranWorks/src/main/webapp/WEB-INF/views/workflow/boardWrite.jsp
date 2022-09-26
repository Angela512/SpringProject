<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
.underline{
	border-top-width: 0;
	border-left-width: 0px;
	border-right-width: 0px;
}
.menu li{
padding:45px 0 0 0;
font-size:15px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">

	<h2>글쓰기</h2>
	<form:form action="write.do" modelAttribute="workflowVO"
	        id="register_form" style="margin: 100px 0 0 100px; border:none" 
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul class="menu">
		<%-- 
			<li>
				<label for="flow_sort">결재종류</label>
				<form:radiobutton path="flow_sort" value="휴가신청"  /> 휴가신청
				<form:radiobutton path="flow_sort" value="기안서" selected="selected"/> 기안서
				<form:radiobutton path="flow_sort" value="예산서"/> 예산서
				<form:radiobutton path="flow_sort" value="경비청구"/> 경비청구
			</li>
			<br>
			 --%>
			<li>
				<label for="flow_sort"> 결재종류</label>
				<c:forEach var="flow" items="${list}">
					${flow_mem_name }
				</c:forEach>
				<input type="radio" name="flow_sort" value="휴가신청" checked style="padding: 50px 0 0 0"> 휴가신청		
			</li>
			
			<li>
				<label for="flow_title"> 제목</label>
				<input type="text" name="flow_title" 
				style="width:347.5px; 
				" class="underline"/>
				<form:errors path="flow_title" 
				             cssClass="error-color"/>
	             
			</li>
			
			<li>
				<label for=""> 결재정보</label>
				<input type="button" value="찾기"  onclick="nwindow()" id="write_button"/> 
				<input type="text"  name="sign_name" id="sign_name" readonly style="width:300px" class="underline"/>
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="sign_name" 
				             cssClass="error-color"/>      			    
			</li>
			<li>
				<label for="flow_subsort"> 휴가구분</label>
				<form:select path="flow_subsort">
				    <form:option value="">휴가종류 선택</form:option>
				    <form:option value="연차">연차</form:option>
				    <form:option value="오후반차">오후반차</form:option>
				    <form:option value="오전반차">오전반차</form:option>
				</form:select>
				<form:errors path="flow_subsort" 
				             cssClass="error-color"/>
			</li>
			
			<li>
				<label for="flow_start"> 휴가기간</label>
				<input type="date" name="flow_start" id="now_date"/>
				-		
				<input type="date" name="flow_end" />
							<form:errors path="flow_start" 
				             cssClass="error-color"/>
				             <form:errors path="flow_end" 
				             cssClass="error-color"/>
			</li> 
			
			<script>
				document.getElementById('now_date').valueAsDate = new Date();
				
				function nwindow(){
		            var url="signList.do";
		            window.open(url,"","width=500,height=400,left=600");
		        }
			</script>
			
			
			
			<li>
				<label for="flow_content"> 사유</label>
				<form:textarea path="flow_content" style="width:56%;"/>
				<form:errors path="flow_content" 
				             cssClass="error-color"/>               
			</li>
			<li>
				<input type="hidden" name="flow_state" value="결재 진행중"/>
			</li>
						
			
		</ul>
		
		
		<div class="align-center">
		
			<input type="submit" value="전송" id="write_button">
			<input type="button" value="목록"
			            onclick="location.href='list.do'" id="write_button">
			            
		</div>
	</form:form>
</div>

<!-- 내용 끝 -->