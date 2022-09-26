<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messanger.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/workflow.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body> 
<c:if test="${!empty user}">
<!-- 메신저 알림 UI -->
<div id="alarm_talk" style="display:none;">
   채팅 알람이 있습니다.
</div>
<script type="text/javascript">
   let user = '${user.mem_num}';
   let alarm_socket;
   function alarm_connect() {
      alarm_socket = new WebSocket("ws://localhost:8080/alarm-ws.do");
      alarm_socket.onopen = function(evt) {
            getAlarm(1);
            getAlarm(2);

      };
      //서버로부터 메시지를 받으면 호출되는 함수 지정
      alarm_socket.onmessage = function(evt) {
         //talk 알림
         let data = evt.data;
         if (data.substring(0, 4) == "msg:") {
         	let alarm_kind = data.substring(4,5);
         	if(alarm_kind==1){
         		getAlarm(1);
         	}else if(alarm_kind==2){
         		getAlarm(2);
         	}
         }
      };
      alarm_socket.onclose = function(evt) {
         //소켓이 종료된 후 부과적인 작업이 있을 경우 명시
      };         
   }
   alarm_connect();
   function getAlarm(alarm_kind){
      $.ajax({
          url: '../alarm/getCount.do',
          data: {alarm_kind:alarm_kind,mem_num:user},
          type: "POST",
          dataType:'json',
          success: function(param) {
             if(param.count>0){
                if(alarm_kind==1){//채팅
                   $('#alarm_talk').show();
                   $('#alarm_talk').text(param.count+'개의 채팅 알람이 있습니다.')
                   $('#alarm_badge').show();
                   $('#alarm_badge').text(param.count);
                }else if(alarm_kind==2){//쪽지
                   $('#letter_bz').show();
                   $('#letter_bz').text(param.count);
                }
             }
          },
          error:function(){
             alert('네트워크 오류 발생');
          }
        });
   }
   
   function deleteAlarm(alarm_kind,redirect){
      $.ajax({
          url: '../alarm/deleteCount.do',
          data: {alarm_kind:alarm_kind,mem_num:user},
          type: "POST",
          dataType:'json',
          success: function(param) {
             if(param.result=='logout'){
                alert('로그인해야 알람을 확인할 수 있습니다.');
             }else if(param.result=='success'){
            	if(alarm_kind==1){//채팅
            		if(redirect==1){
                        location.href='${pageContext.request.contextPath}/messanger/list.do';
                     }else{
                        $('#alarm_talk').hide();
                        $('#alarm_badge').hide();
                     }
            	}else if(alarm_kind==2){//쪽지
                        $('#letter_bz').hide();
            	}
             }
          },
          error:function(){
             alert('네트워크 오류 발생');
          }
        });
   }
   
   $(function(){
	   $('#alarm_talk').click(function(){
	      //채팅,페이지 이동시는 1 지정, 숨김 처리 0
	      deleteAlarm(1,1);
	   });

	   $('#ck_letter').click(function(){
	      //채팅,페이지 이동시는 1 지정, 숨김 처리 0
	      deleteAlarm(2,0);
	   });
   });
</script>
</c:if>
<div id="main">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div class="side-height">
		<div id="main_nav">
			<tiles:insertAttribute name="nav"/>
		</div>
		<div id="main_body">
			<tiles:insertAttribute name="body"/>
		</div>
	</div>
	<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>

</body>
</html>



