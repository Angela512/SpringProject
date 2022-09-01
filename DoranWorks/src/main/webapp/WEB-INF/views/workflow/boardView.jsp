<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
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
    
    <script language=JavaScript>
    function gb(obj) {
        var tms1 = as2.contentEditable;
        
        if(tms1 == 'true'){	
        
        var obj1 = (obj.id).substring(0,2);
        var obj2 = (obj.id).substring(2,4);
                
        if(obj2 == "1"){
             eval("aa1").innerText =  "연차( V )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "1";
        }
         if(obj2 == "2"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차( V )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "2";
        }
         if(obj2 == "3"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련( V )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "3";
        }
        if(obj2 == "4"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상( V )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "4";
        }
        if(obj2 == "5"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타( V )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "5";
        }
        if(obj2 == "6"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴( V )";
             eval("aa7").innerText =  "오후반차(   )";
             eval("gb1").innerText =  "6";
        }
        if(obj2 == "7"){
             eval("aa1").innerText =  "연차(   )";
             eval("aa2").innerText =  "오전반차(   )";	 
             eval("aa3").innerText =  "훈련(   )";
             eval("aa4").innerText =  "포상(   )"; 
             eval("aa5").innerText =  "기타(   )";
             eval("aa6").innerText =  "대휴(   )";
             eval("aa7").innerText =  "오후반차( V )";
             eval("gb1").innerText =  "7";
        }
        calendar2();
        title_make();
        }
    }
    
    function calendar(obj, todate_id) {
        var tms1 = as2.contentEditable;
        
        if(tms1 == 'true'){	
        
       fun('calender',obj.id,'YYYY년 MM월 DD일 DDD요일');
       
       //시작날짜
       var sdate_str = document.getElementById("h_sdate").innerText; 
       sdate_str = sdate_str.replace("년","");
       sdate_str = sdate_str.replace("월","");
       sdate_str = sdate_str.replace("일","");
       
       var sdateArray = sdate_str.split(" "); 
       var sdateObj = new Date(sdateArray[0], Number(sdateArray[1])-1, sdateArray[2]); 
       
       
       //끝 날짜
       var edate_str = document.getElementById("h_edate").innerText; 
       edate_str = edate_str.replace("년","");
       edate_str = edate_str.replace("월","");
       edate_str = edate_str.replace("일","");
       
       var edateArray = edate_str.split(" ");       
       var edateObj = new Date(edateArray[0], Number(edateArray[1])-1, edateArray[2]); 
       
       //시작 끝 날짜 계산
       var betweenDay = (edateObj.getTime() - sdateObj.getTime()) / 1000 / 60 / 60 / 24;
       
       //박 수 일 수를 히든으로
      document.getElementById("baksu").innerText = betweenDay ;
      document.getElementById("ilsu").innerText = betweenDay + 1;
      
      // 양식에 표시
      // h_caldate.innerText = "( "+ (betweenDay)+" 박 "+ (betweenDay + 1) + " 일 )";
      var gb_check = document.getElementById("gb1").innerText;
      
      if(gb_check == "2" || gb_check == "7"){
           h_caldate.innerText = "( "+ ((betweenDay + 1)/2) + " 일 )";
      }else{
      
      h_caldate.innerText = "( "+ (betweenDay + 1) + " 일 )";
      }
       
      
      //일자 기준 소계 계산
      
    
      
       title_make();    
        }
       
    }
    
    
    function calendar2() {
        
      
       
       //시작날짜
       var sdate_str = document.getElementById("h_sdate").innerText; 
       sdate_str = sdate_str.replace("년","");
       sdate_str = sdate_str.replace("월","");
       sdate_str = sdate_str.replace("일","");
       
       var sdateArray = sdate_str.split(" "); 
       var sdateObj = new Date(sdateArray[0], Number(sdateArray[1])-1, sdateArray[2]); 
       
       
       //끝 날짜
       var edate_str = document.getElementById("h_edate").innerText; 
       edate_str = edate_str.replace("년","");
       edate_str = edate_str.replace("월","");
       edate_str = edate_str.replace("일","");
       
       var edateArray = edate_str.split(" ");       
       var edateObj = new Date(edateArray[0], Number(edateArray[1])-1, edateArray[2]); 
       
       //시작 끝 날짜 계산
       var betweenDay = (edateObj.getTime() - sdateObj.getTime()) / 1000 / 60 / 60 / 24;
       
       //박 수 일 수를 히든으로
      document.getElementById("baksu").innerText = betweenDay ;
      document.getElementById("ilsu").innerText = betweenDay + 1;
      
      // 양식에 표시
      // h_caldate.innerText = "( "+ (betweenDay)+" 박 "+ (betweenDay + 1) + " 일 )";
      var gb_check = document.getElementById("gb1").innerText;
      
      if(gb_check == "2" || gb_check == "7"){
           h_caldate.innerText = "( "+ ((betweenDay + 1)/2) + " 일 )";
      }else{
      
      h_caldate.innerText = "( "+ (betweenDay + 1) + " 일 )";
      }
       
      
      //일자 기준 소계 계산
      
      title_make();
                  
       
    }
    
    function title_make(){
        var t1 = document.getElementById("gb1").innerText; 
        var t2 = document.getElementById("username").innerText; 
        
        var s1 = document.getElementById("h_sdate").innerText; 
        var s2 = s1.substring(6,8);
        var s3 = s1.substring(10,12);
        
        var s4 = s2+"/"+s3;
                
        var e1 = document.getElementById("h_edate").innerText; 
        var e2 = e1.substring(6,8);
        var e3 = e1.substring(10,12);
        
        var e4 = e2+"/"+e3;
        
        var t3 = s4+"~"+e4;
        if(s4 == e4) t3 = s4;
        
        
        var t4 = document.getElementById("h_caldate").innerText; 
        
        if(t1 == "1") t1 = "연차";
        if(t1 == "2") t1 = "오전반차";
        if(t1 == "3") t1 = "훈련";
        if(t1 == "4") t1 = "포상";
        if(t1 == "5") t1 = "기타";
        if(t1 == "6") t1 = "대휴";
        if(t1 == "7") t1 = "오후반차";
        eval("title1").innerText = "휴가신청서 [ " + t2+" _ "+t1+" _ "+t3+" _ "+t4+" ]";
        
    }
    
    
    
    
    
    
    
    
    
    </script>
    
    
    
    <script id="nopspro">
      <rootnode>
        <setvalue>
          <결재의견>opp</결재의견>
          <사원명>username</사원명> 
          <직위>pp</직위>
          <사원ID>user_id</사원ID>
          <부서명>partname</부서명>
          <년월일2>to_date</년월일2>
          <문서제목>title1;</문서제목>
          <형식일시>YYYY년 MM월 DD일;to_date;</형식일시> 
          <형식일시>YYYY년 MM월 DD일 DDD요일;h_sdate;h_edate</형식일시> 
          <첨부파일명>files</첨부파일명>
              <문서번호형식>Y;1;Y;휴가-{YY}{MM}{DD}-;3;</문서번호형식>
          <문서번호>docno</문서번호>
        </setvalue>  
        <command>
          <voice_rec>n</voice_rec>
          <gps_on>y</gps_on>
            <display_on_edit>hidetable</display_on_edit>
            <hide_on_print>hidetable</hide_on_print>
          <양식명제외></양식명제외>
          </command>
      </rootnode>
    </script>
    
    
    
    </HEAD>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<div class="page-main">
	<h2>${workflow_main.flow_title}</h2>
	<ul class="detail-info">
		<%-- <li>
			<c:if test="${!empty workflow_main.flow_photo_name}">
			<img src="imageView.do?flow_num=${workflow_main.flow_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty workflow_main.flow_photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li> --%>
		<li>
			[${workflow_main.mem_dpt}] ${workflow_main.mem_name} ${workflow_main.mem_rank}
			<br>
			결재 종류 :${workflow_main.flow_sort}
			<br>
			작성일 : ${workflow_main.flow_date}
		</li>
	</ul>
	<hr size="1" width="100%">
	<p>
		${workflow_main.flow_content}
	</p>
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
    <table width="644" border="1"  cellspacing="0" cellpadding="0" bordercolor="#000000" style="border-collapse:collapse;" align="center" class="form10">
    
    <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">문서번호</td>
        <td align="center" class="form10"><div id="docno"></div>${workflow_main.flow_num}</td>
        <td align="center" bgcolor="#D9E2F3" class="form10b">작성일자</td> 
        <td align="center" class="form10"><DIV   id="to_date" style="padding:3px;"></DIV>${workflow_main.flow_date}</td>
      </tr>
    
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">이 름</td>
        <td align="center" class="form10"><div id="username"></div>${workflow_main.mem_name}</td>
        <td align="center" bgcolor="#D9E2F3" class="form10b">직 책</td>
        <td align="center" class="form10"><div id="pp"></div>${workflow_main.mem_rank}</td>
      </tr>
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">소속(본부)</td>
        <td width="188" align="center" class="form10"><div tms_edit id="jic2"></div>도란도란</td>
        <td width="79" align="center" bgcolor="#D9E2F3" class="form10b">부서(팀)</td>
        <td width="182" align="center" class="form10"><div id="partname"></div>${workflow_main.mem_dpt}</td>
      </tr>
      <tr>
        <td height="40" colspan="3" align="center" bgcolor="#D9E2F3"  class="form10b">제 목</td>
        <td colspan="3" align="left" class="form10" onClick="javascript:title1.focus();"><DIV   id="title1" style="padding:3px;">휴가신청서[ ]</DIV></td>
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
            <td height="40" rowspan="2" align="center"><div  id="aa1" onClick="gb(this)">연차(&nbsp;&nbsp;&nbsp;)</div></td>
            <td align="center"><div  id="aa2" onClick="gb(this)">오전반차(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa3" onClick="gb(this)">훈련(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa4" onClick="gb(this)">포상(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa5" onClick="gb(this)">기타(&nbsp;&nbsp;&nbsp;)</div></td>
            <td rowspan="2" align="center"><div  id="aa6" onClick="gb(this)">대휴(&nbsp;&nbsp;&nbsp;)</div></td>
          </tr>
          <tr>
            <td align="center"><div  id="aa7" onClick="gb(this)">오후반차(&nbsp;&nbsp;&nbsp;)</div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">일정</td>
        <td colspan="3" align="left"  class="form10" onClick="javascript:as2.focus();"><table width="100%" border="0" align="center" cellpadding="0"  cellspacing="0" bordercolor="#ffffff" class="form2" style="border-collapse:collapse;">
          <tr>
            <td width="33%" align="left" class="form2"><div  id="h_sdate" onClick="calendar(this, 'todate1')"  style="padding:3px;cursor:hand;">2017.01.16.</div></td>
            <td width="6%" align="center" class="form2">~</td>
            <td width="33%" align="left" class="form2"><div  id="h_edate" onClick="calendar(this, 'todate1')"  style="padding:3px;cursor:hand;"></div></td>
            <td width="3%" align="left" class="form2">&nbsp;</td>
            <td width="25%" align="left" class="form2"><div   tms_edit    id="h_caldate" style="padding:3px" >(  1일)</div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">주요행선지</td>
        <td colspan="3" align="left"  class="form10" ><div tms_edit id="jic3" style="padding:3px"></div></td>
      </tr>
      <tr>
        <td height="40" align="center" bgcolor="#D9E2F3"  class="form10b">대체근무자</td>
        <td colspan="3" align="left" class="form10"><div tms_edit id="jic4" style="padding:3px"></div></td>
      </tr>
      <tr>
        <td height="200" colspan="2" align="center" bgcolor="#D9E2F3"  class="form10b">신청사유<br>
          (자세히)</td>
        <td colspan="3" align="left" valign="top" class="form10" onClick="javascript:as2.focus();"><DIV  tms_edit id="as2" style="padding:3px;"><DIV></td>
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
    &nbsp;2. 대휴 신청은 휴일/연장 근무 신청서를 반드시 첨부 바랍니다.
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
