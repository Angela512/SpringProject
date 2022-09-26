<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	alert('${message}');
	alarm_socket.send('msg:2');
	location.href='${url}';
});
</script>