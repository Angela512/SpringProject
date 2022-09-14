$(function(){
$('#list_read').click(function(){
		let checks='';
		if($('input[type=checkbox]:checked').length<1){
			alert('한 사람 이상 선택하세요!');
			return;
		}
		
		 $('input[type=checkbox]:checked').each(function(index){
			if(index==0){
				checks+=$(this).val();
			}else{
				checks+=','+$(this).val();
			}
		});
		
		opener.document.getElementById('sign_name').value = checks;
		
		self.close();
	});
	
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function email_check() {
		var select = eval("document.email_select_form");
		var checked = document.getElementsByName("chkbox");
		var chkList = ""; for(var i=0; i<select.chkbox.length; i++) {
				
				if(checked[i].checked == true) {
					chkList +=checked[i].value+", ";
					}
				}
				opener.document.board_form.email_group_market_name.value=chkList;
				
				self.close();
				
				
		}
				
				
				
				
