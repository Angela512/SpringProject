<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow.view.js"></script>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="ko">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, minimum-scale=0.5, user-scalable=yes">
    <TITLE>기안서</TITLE>
    <link href="./common.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="./common.js"></script>
    <style type="text/css">
    <!--
    .p  {padding-left: 2;}
    .pline  {padding-bottom: 3px;}
    .c12 { width:12;cursor:hand;border:1px solid;text-align:right;font-size:9; background-color: "#FFFFFF"}
    -->
    
    .form11b {
        font-family: "맑은 고딕";
        font-size: 11pt;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
        font-weight:bold;
    }
    .form11 {
        font-family: "맑은 고딕";
        font-size: 11pt;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
    }
    .form10b {
        font-family: "맑은 고딕";
        font-size: 10pt;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
        font-weight:bold;
    }
    .form10 {
        font-family: "맑은 고딕";
        font-size: 10pt;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
    }
    .form16b {
        font-family: "맑은 고딕";
        font-size: 16pt;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
        font-weight:bold;
    }
    
    
    
    
    .form2 {	font-family: "맑은 고딕";
        font-size: 12px;
        color: #000000;
        text-decoration: none;
        letter-spacing:0px;
    }
    
    
    A:link {font-family: "맑은 고딕";font-size: 10pt;  text-decoration:none; color: black;} 
    A:visited {font-family: "맑은 고딕";font-size: 10pt; text-decoration:none; color: black;} 
    A:active {font-family: "맑은 고딕";font-size: 10pt; text-decoration:underline; color: black;} 
    A:hover {font-family: "맑은 고딕";font-size: 10pt;color: black; text-decoration:underline;} 
    
    
    
    </style>
        </HEAD>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<div class="page-main">
<fmt:parseDate value="${workflow.flow_start}" var="strPlanDate" pattern="yyyy-MM-dd"/>
	 <fmt:parseNumber value="${strPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
	 <fmt:parseDate value="${workflow.flow_end}" var="endPlanDate" pattern="yyyy-MM-dd"/> 
	 <fmt:parseNumber value="${endPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
	<h2>${workflow.flow_title}</h2>
	<input type="hidden" value="${workflow.flow_num}" id="fw_num">
	<ul class="detail-info">
		<%-- <li>
			<c:if test="${!empty workflow.flow_photo_name}">
			<img src="imageView.do?flow_num=${workflow.flow_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty workflow.flow_photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li> --%>
		
		
		<li>
			[${workflow.mem_dpt}] ${workflow.mem_name} ${workflow.mem_rank}
			<br>
			결재 종류 :${workflow.flow_sort}
			<br>
			작성일 : ${workflow.flow_date}
		</li>	
		<li>
			<form action="/workflow/ok.do" method="post" style="border:none;">
			<c:if test="${workflow.mem_name!=user_name}">
			<input type="submit" id="write_button" value="승인" style="background-color:#09E81F;">
			</c:if>
			<input type="hidden" name="flow_num" value="${workflow.flow_num}">			
			
			
			<c:set var="myArray" value="${fn:split(workflow.sign_name,',')}" />
			
			<c:if test="${fn:length(myArray) == 1}">
				<c:if test="${myArray[0] == user_name}">
				<input type="hidden" name="flow_no" value="1">
				<input type="hidden" name="flow_state" value="결재 승인"/>
				</c:if>
			</c:if>
			
			<c:if test="${fn:length(myArray) == 2}">
				<c:if test="${myArray[0] == user_name}">
				<input type="hidden" name="flow_no" value="1,0">
				<input type="hidden" name="flow_state" value="결재 진행중"/>
				</c:if>
				<c:if test="${myArray[1] == user_name}">
				<input type="hidden" name="flow_no" value="1,1">
				<input type="hidden" name="flow_state" value="결재 승인"/>
				</c:if>
			</c:if>
			
			<c:if test="${fn:length(myArray) == 3}">
				<c:if test="${myArray[0] == user_name}">
				<input type="hidden" name="flow_no" value="1,0,0">
				<input type="hidden" name="flow_state" value="결재 진행중"/>
				</c:if>
				<c:if test="${myArray[1] == user_name}">
				<input type="hidden" name="flow_no" value="1,1,0">
				<input type="hidden" name="flow_state" value="결재 진행중"/>
				</c:if>
				<c:if test="${myArray[2] == user_name}">
				<input type="hidden" name="flow_no" value="1,1,1">
				<input type="hidden" name="flow_state" value="결재 승인"/>
				</c:if>
			</c:if>
			
			</form>
		</li>
	</ul>
	<hr size="1" width="100%">
	

	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == workflow.mem_num}">
		<input type="button" value="수정"  id="write_button"
		  onclick="location.href='update.do?flow_num=${workflow.flow_num}'">
		<!-- <input type="button" value="삭제" id="delete_btn"> -->
		<!-- <script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?board_num=${board.board_num}');
				}
			};
		</script> -->  
		
		</c:if>
				
		<input type="button" id="write_button" value="목록"
		       onclick="location.href='list.do'">
	</div>
	
	
	
	
	
	
	
</div>
    
    
	<BODY onLoad='FixMenu(document, document.all.tools)' onScroll="FixMenu(document, document.all.tools)" onResize="FixMenu(document, document.all.tools)"nn>
    
    
    <table width="644" border="0"  cellspacing="0" cellpadding="0" bordercolor="#ffffff" style="border-collapse:collapse;" class="form2" align="center" >
            <tr>
                <td align="right"  >&nbsp;</td>
            </tr>
    </table>
    <table width="644" border="0"  cellspacing="0" cellpadding="0" bordercolor="#ffffff" style="border-collapse:collapse;" class="form10" align="center" >
          <tr  valign="middle" bgcolor="white">
            <td width="643"   height="93" align="center" class="form16b">
              <div id="title" style="font-size: 18pt">휴가신청서</div>
              <div  id="baksu" style="display:none;"></div>
              <div  id="ilsu" style="display:none;"></div>
              <div  id="gb1" style="display:none;">1</div></td>
            <td width="1" align="center" class="form16b" id="tms_auto_sign"></td>
          </tr>
    </table>
    
     
    
     
    <table width="644" border="0"  cellspacing="0" cellpadding="0" bordercolor="#ffffff" style="border-collapse:collapse;" class="form2" align="center" >
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
   
    <table width="644" border="1"  cellspacing="0" cellpadding="0" bordercolor="#000000" style="border-collapse:collapse; border:none;" align="center" class="form10">
    <tr >
    	<td colspan="7" style="border:none;">
    	<!-- 테이블 테스트 -->
    		<table border="solid" align="right" class="form10" style="border-collapse:collapse;">
   				<tr>
    				<td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b" rowspan="2" width="10"></td>
    				<c:forTokens items="${workflow.sign_name}" delims = "," var="name">
    				<td><c:out value="${name}" /></td>
    				</c:forTokens>

    			</tr>
    			<tr>
    				
    			<c:if test="${empty workflow.flow_no }">
    				<c:forTokens items="${workflow.sign_name}" delims = "," var="name">
    				<td height="60" width="80">
    				</td>
    				</c:forTokens>
    			</c:if>
    			
    			<c:if test="${!empty workflow.flow_no }">
    			    <c:forTokens items="${workflow.flow_no}" delims = "," var="name">
    				<td height="60" width="80"> 
    				 				
    				<c:if test="${name == '1' }">
    				<img src="${pageContext.request.contextPath}/images/seal.png" width="40" height="60" style="margin:0 0 0 15px;" class="my-photo">  				
    				</c:if>	
    				
    				</td>   				
					</c:forTokens> 
                </c:if>
                    
    			</tr>
 		   </table>
 		  
	   
    	<!-- 테이블 테스트 끝 -->
    	</td>
    </tr>
    <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">문서번호</td>
        <td align="center" class="form10"><div id="docno"></div><fmt:formatDate value="${workflow.flow_date}" pattern="yyyyMMdd"/>-${workflow.flow_num}</td>
        <td align="center" bgcolor="#D9E2F3" class="form10b">작성일자</td> 
        <td align="center" class="form10"><DIV   id="to_date" style="padding:3px;"></DIV>${workflow.flow_date}</td>
      </tr>
    
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">이 름</td>
        <td align="center" class="form10"><div id="username"></div>${workflow.mem_name}</td>
        <td align="center" bgcolor="#D9E2F3" class="form10b">직 책</td>
        <td align="center" class="form10"><div id="pp"></div>${workflow.mem_rank}</td>
      </tr>
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">소속(본부)</td>
        <td width="188" align="center" class="form10"><div tms_edit id="jic2"></div>도란도란</td>
        <td width="79" align="center" bgcolor="#D9E2F3" class="form10b">부서(팀)</td>
        <td width="182" align="center" class="form10"><div id="partname"></div>${workflow.mem_dpt}</td>
      </tr>
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">제 목</td>
        <td colspan="3" align="left" class="form10" onClick="javascript:title1.focus();"><DIV   id="title1" style="padding:3px;">${workflow.flow_title}</DIV></td>
      </tr>
      <tr>
        <td width="38" rowspan="6" align="center" bgcolor="#D9E2F3"  class="form10b">신<br>
          청<br>
          내<br>
          용</td>
        <td width="40" rowspan="4" align="center" bgcolor="#D9E2F3"  class="form10b">휴<br>
          가<br>
          신<br>
          청<br>
          서</td>
        <td width="103" height="50" align="center" bgcolor="#D9E2F3"  class="form10b">종류<br>
          (V체크)</td>
        <td colspan="3" align="left"  class="form10">
        <table width="100%" border="0"  class="form10">
          <tr>
            <c:if test="${workflow.flow_subsort == '연차' }">
            <td height="40" rowspan="2" align="center"><div  id="aa1" onClick="gb(this)">연차(V)</div></td>
            </c:if>
            <c:if test="${workflow.flow_subsort != '연차' }">
            <td height="40" rowspan="2" align="center"><div  id="aa1" onClick="gb(this)">연차(&nbsp;&nbsp;&nbsp;)</div></td>
            </c:if>
            
            <c:if test="${workflow.flow_subsort == '오전반차' }">
            <td align="center"><div  id="aa2" onClick="gb(this)">오전반차(V)</div></td>
            </c:if>
            <c:if test="${workflow.flow_subsort != '오전반차' }">
            <td align="center"><div  id="aa2" onClick="gb(this)">오전반차(&nbsp;&nbsp;&nbsp;)</div></td>
            </c:if>
                      
            <c:if test="${workflow.flow_subsort == '훈련' }">
            <td rowspan="2" align="center"><div  id="aa3" onClick="gb(this)">훈련(V)</div></td>
            </c:if>
            <c:if test="${workflow.flow_subsort != '훈련' }">
            <td rowspan="2" align="center"><div  id="aa3" onClick="gb(this)">훈련(&nbsp;&nbsp;&nbsp;)</div></td>
            </c:if>
            
            <td rowspan="2" align="center"><div  id="aa4" onClick="gb(this)">포상(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa5" onClick="gb(this)">기타(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa6" onClick="gb(this)">대휴(&nbsp;&nbsp;&nbsp;)</div></td>
          </tr>
          <tr>
          	<c:if test="${workflow.flow_subsort == '오후반차' }">
            <td align="center"><div  id="aa7" onClick="gb(this)">오후반차(V)</div></td>
            </c:if>
            <c:if test="${workflow.flow_subsort != '오후반차' }">
            <td align="center"><div  id="aa7" onClick="gb(this)">오후반차(&nbsp;&nbsp;&nbsp;)</div></td>
            </c:if>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">일정</td>
        <td colspan="3" align="left"  class="form10" onClick="javascript:as2.focus();"><table width="100%" border="0" align="center" cellpadding="0"  cellspacing="0" bordercolor="#ffffff" class="form2" style="border-collapse:collapse;">
          <tr>
            <td width="33%" align="left" class="form2"><div  id="h_sdate" onClick="calendar(this, 'todate1')"  style="padding:3px;cursor:hand;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${workflow.flow_start}</div></td>
            <td width="6%" align="center" class="form2">~</td>
            <td width="33%" align="left" class="form2"><div  id="h_edate" onClick="calendar(this, 'todate1')"  style="padding:3px;cursor:hand;">${workflow.flow_end}</div></td>
            <td width="3%" align="left" class="form2">&nbsp;</td>
            <td width="25%" align="left" class="form2"><div   tms_edit    id="h_caldate" style="padding:3px" >(   ${endDate-strDate}일)</div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">수신</td>
        <td colspan="3" align="left"  class="form10" ><div tms_edit id="jic3" style="padding:3px">
		&nbsp;
		<c:forTokens items="${workflow.sign_name}" delims = "," var="name">
        <c:out value="${name}" /> &nbsp;
        </c:forTokens>

	</div></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">대체근무자</td>
        <td colspan="3" align="left" class="form10"><div tms_edit id="jic4" style="padding:3px"></div></td>
      </tr>
      <tr>
        <td height="200" colspan="2" align="center" bgcolor="#D9E2F3"  class="form10b">신청사유<br>
          (자세히)</td>
        <td colspan="3" align="left" valign="top" class="form10" onClick="javascript:as2.focus();"><DIV  tms_edit id="as2" style="padding:3px;"><DIV>${workflow.flow_content}</DIV>       	    
        </td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center" bgcolor="#D9E2F3"  class="form10b">긴급연락처</td>
        <td colspan="3" align="left"  class="form10" ><div tms_edit id="jic5" style="padding:3px"></div></td>
      </tr>
     
    <tr>
        <td height="60" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">참고사항</td>
        <td colspan="3" align="left" class="form10" onClick="javascript:as2.focus();">
    <b><font color=red >
    &nbsp;1. 수신자에 인사팀, 총무팀 추가 바랍니다.</font></b><br>
    &nbsp;2. 대휴 신청은 휴일/연장 근무 신청서를 반드시 첨부 바랍니다.<br>
    </td>
      </tr>
    
    
    
     <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b" style="cursor:hand;" onClick="javascript:fun('add_file')">첨부파일</td>
        <td colspan="3" align="left" class="form10" onClick="javascript:as3.focus();"><DIV   id="files" style="padding:3px;">
          <DIV></td>
      </tr>
    </table>
    <table width="700" border="0"  cellspacing="0" cellpadding="0" bordercolor="#ffffff" style="border-collapse:collapse;" class="form16" align="center" >
           <tr>
                <td></td>
      </tr>
    </table>
    </BODY>







<!-- 내용 끝 -->

</HTML>
