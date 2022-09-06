/*
$(function(){
// 'request'라는 id를 가진 버튼 클릭 시 실행.
    $("#request").click(function(){
 
        
                
            // ajax 통신
            $.ajax({
                type : "POST",            // HTTP method type(GET, POST) 형식이다.
                url : "/workflow/signList.do",      // 컨트롤러에서 대기중인 URL 주소이다.
                data : form_data,            // Json 형식의 데이터이다.
                success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                    // 응답코드 > 0000
                    alert(res.code);
 					window.close();
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
                    alert("통신 실패.")
                }
            });
        });

});
*/

function fn_editFL()
	{
		var params = $("#request").serialize();
		$.ajax(
		{
			url : '/workflow/signList.do',
			data : params,
			success : function(xh)
					{									
						window.close();
					}
		});
	}