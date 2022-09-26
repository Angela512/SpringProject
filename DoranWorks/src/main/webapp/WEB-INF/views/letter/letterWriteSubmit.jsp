<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	alert('${message}');
	$.ajax({
		url:'getLetterData.do',
		type:'post',
		dataType:'json',
		success:function(param){
			alarm_socket.send('msg:2');
			location.href='${url}';
		},
		error:function(){
			alert('네트워크 오류 발생!!');
		}
	});
});
</script>