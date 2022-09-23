<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
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
<!-- 메신저 알림 UI -->
<div id="alarm_talk" style="cursor:pointer;display:none;width:100px;height:100px;position:absolute;left:1300px;top:5px;border:1px solid black">
   채팅 알람이 있습니다.
</div>
<script type="text/javascript">
   let user = '${user.mem_num}';
   let alarm_socket;
   function alarm_connect() {
      alarm_socket = new WebSocket("ws://localhost:8080/alarm-ws.do");
      alarm_socket.onopen = function(evt) {
         if(user!=''){
            getAlarm(1);
         }
      };
      //서버로부터 메시지를 받으면 호출되는 함수 지정
      alarm_socket.onmessage = function(evt) {
         //talk 알림
         var data = evt.data;
            if (data.substring(0, 4) == "msg:") {
               if(user!=''){
                  getAlarm(1);
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
                }else if(alarm_kind==2){//쪽지
                   $('#alarm_note').show();
                   $('#alarm_note').text(param.count+'개의 채팅 알람이 있습니다.')
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
                if(redirect==1){
                   location.href='${pageContext.request.contextPath}/messanger/list.do';
                }else{
                   $('#alarm_talk').hide();
                }
             }
          },
          error:function(){
             alert('네트워크 오류 발생');
          }
        });
   }
   
   $('#alarm_talk').click(function(){
      //채팅,페이지 이동시는 1 지정
      deleteAlarm(1,1);
   });
</script>
</body>
</html>



