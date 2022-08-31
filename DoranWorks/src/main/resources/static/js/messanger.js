$(function(){
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}
		
	});
});