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
</head>
<body>
<div id="main">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="main_nav">
		<tiles:insertAttribute name="nav"/>
	</div>
	<div id="main_body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
<!-- 알림 UI -->
<div id="alarm" style="display:none;width:100px;height:100px;position:absolute;left:1300px;top:5px;border:1px solid black"></div>
<script type="text/javascript">
let alarm_socket;
let alarm_kind;
function alarm_connect() {
   alarm_socket = new WebSocket("ws://localhost:8080/alarm-ws.do");
   alarm_socket.onopen = function(evt) {
      //소켓 시작시 작업
   };
   //서버로부터 메시지를 받으면 호출되는 함수 지정
   alarm_socket.onmessage = function(evt) {
      //talk 알림
      var data = evt.data;
         if (data.substring(0, 4) == "msg:") {
            let user = '${user.mem_num}';
            alarm_kind = data.substring(4,5);
            let members = data.substring(6).split(',');
            console.log(alarm_kind+':'+members);
            if(user!=''){
            	let alarm = document.getElementById('alarm');
                for(let i in members){
                   if(members[i] == user){
                      if(alarm_kind==1){
                    	  alarm.innerHTML = '채팅 알람이 있습니다.';
                      }
                      alarm.style.display = '';
                   }
                }
            }
         }
   };
   alarm_socket.onclose = function(evt) {
      //소켓이 종료된 후 부과적인 작업이 있을 경우 명시
   };         
}
alarm_connect();
document.getElementById('alarm').onclick=function(){
   if(alarm_kind==1){
      location.href='${pageContext.request.contextPath}/messanger/list.do';
   }
};
</script>
</body>
</html>



