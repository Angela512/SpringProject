<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<style>
#topButton {position: fixed; right: 2%; bottom: 120px; display: none; z-index: 999;}
figure.image img{
	max-width:600px;
}
figure.image{
	text-align: center;
}
#ck_letter{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/letter.detail.important.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<div class="content">
	<div class="align-right">
	<c:if test="${np.prev_num != null }">
	<button id="prev_btn" onclick="location.href='detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}'">▲</button>
	</c:if>
	
	<c:if test="${np.next_num != null }">
	<button id="next_btn" onclick="location.href='detail.do?lt_num=${np.next_num }&letter_type=${param.letter_type}'">▼</button>
	</c:if>
	<style>
		.material-symbols-outlined {
		  font-variation-settings:
		  'FILL' 0,
		  'wght' 400,
		  'GRAD' 0,
		  'opsz' 48
		}
	</style>
	
	
	</div>
	
	<input type="button" class="list_button" value="답장" onclick="location.href='reply.do?lt_num=${letter.lt_num}'">
	<input type="button" class="list_button" value="전달" onclick="location.href='forward.do?lt_num=${letter.lt_num}'">
	<input type="button" class="list_button" value="삭제" id="detail_delete">
	<input type="button" class="list_button" value="안읽음" id="detail_noread">
	<input type="button" class="list_button" value="목록" onclick="location.href='main.do'">
	
	<hr size="1" class="hr-line" width="100%">
	<%-- important --%>
	<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
	<input type="hidden" name="lt_num" id="lt_num" value="${letter.lt_num }">
	<input type="hidden" name="lt_type" id="lt_type" value="${param.letter_type}">
	<span id="detail_title">${letter.lt_title }</span>
	<span id="detail_date">${letter.lt_date}</span>
	<ul id="detail_srr">
		<li><b>보낸사람 :</b> ${letter.lt_sender_id}</li>
		<li><b>받는사람 :</b> ${letter.lt_receiver_id}</li>
		<li><b>참조 :</b> ${letter.lt_reference_id}</li>
	</ul>
	
	
	<c:if test="${!empty letter.lt_filename1 || !empty letter.lt_filename2 }">
	<hr size="1" width="100%" class="hr-line">
		<ul>
			<li>
				<b>첨부파일</b>
			</li>
			<li>
				<a href="file.do?lt_num=${letter.lt_num}&file_type=1">${letter.lt_filename1}</a>
			</li>
			<li>
				<a href="file.do?lt_num=${letter.lt_num}&file_type=2">${letter.lt_filename2}</a>
			</li>
		</ul>
	</c:if>
	
	<hr size="1" width="100%" class="hr-line">
	<c:if test="${fn:endsWith(letter.lt_filename1,'.jpg') ||
				  fn:endsWith(letter.lt_filename1,'.JPG') ||
				  fn:endsWith(letter.lt_filename1,'.JPEG') ||
				  fn:endsWith(letter.lt_filename1,'.jpeg') ||
				  fn:endsWith(letter.lt_filename1,'.GIF') ||
				  fn:endsWith(letter.lt_filename1,'.PNG') ||
				  fn:endsWith(letter.lt_filename1,'.gif') ||
				  fn:endsWith(letter.lt_filename1,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?lt_num=${letter.lt_num}&image_type=1" style="max-width:500px;">
	</div>
	<br>
	</c:if>
	
	
	<c:if test="${fn:endsWith(letter.lt_filename2,'.jpg') ||
				  fn:endsWith(letter.lt_filename2,'.JPG') ||
				  fn:endsWith(letter.lt_filename2,'.JPEG') ||
				  fn:endsWith(letter.lt_filename2,'.jpeg') ||
				  fn:endsWith(letter.lt_filename2,'.GIF') ||
				  fn:endsWith(letter.lt_filename2,'.PNG') ||
				  fn:endsWith(letter.lt_filename2,'.gif') ||
				  fn:endsWith(letter.lt_filename2,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?lt_num=${letter.lt_num}&image_type=2" style="max-width:500px;">
	</div>
	</c:if>
	
	<p>
		${letter.lt_content }
	</p>
	
	<div id="topButton">
		<input type="button" value="맨위로▲" id="topButtonImg" class="list_button">
	</div>
	
	<div class="npzone" style="margin:0;">
	<hr size="1" width="100%" class="hr-line">
	<c:if test="${param.letter_type==0 || param.letter_type == null || param.letter_type==4}">
		<c:if test="${np.prev_num != null }">
			<c:if test="${np.prev_send_id != user.mem_id }">
			<div style="max-height:45px;">
				<div class="npid">
				<a style="display: flex;align-items: center;" href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}">
				<span class="material-symbols-outlined">expand_less</span>
				<c:if test="${np.prev_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.prev_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				 <span style="display:inline-block;margin:0;padding:4px 0 0 0;" >${np.prev_send_id} ${np.prev_title } </span></a>
				 </div>
				 <div class="npdate align-right"><span>${np.prev_date}</span></div>
				 <div style="clear:both;"></div>
			 </div>
			</c:if>
			<c:if test="${np.prev_send_id == user.mem_id }">
			<div>
				<a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}" <c:if test="${np.prev_read==0}">style="color:blue;"</c:if>>
				<span class="material-symbols-outlined">expand_less</span>
				<c:if test="${np.prev_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.prev_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				 <span class="npid">${np.prev_receiver_id} ${np.prev_title } </span></a>
			</div>
			<div class="npdate align-right"><span>${np.prev_date}</span></div>
			<div style="clear:both;"></div>
			</c:if>
		</c:if>
		
		<c:if test="${np.next_num != null }">
			<c:if test="${np.next_send_id != user.mem_id }">
			<div>
			    <div class="npid">
				<a style="display: flex;align-items: center;" href="detail.do?lt_num=${np.next_num}&letter_type=${param.letter_type}" <c:if test="${np.next_read==0}">style="color:blue;"</c:if>>
				<span class="material-symbols-outlined">expand_more</span>
				<c:if test="${np.next_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.next_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				 <span style="display:inline-block;margin:0;padding:5px 0 0 0;" class="npid">${np.next_send_id} ${np.next_title }</span></a>
				 </div>
				<div class="npdate align-right"><span class="npdate">${np.next_date}</span></div>
				<div style="clear:both;margin:0;padding:0;height:0px;"></div>
			</div>
			</c:if>
			<c:if test="${np.next_send_id == user.mem_id }">
			<div class="np2">
				<a href="detail.do?lt_num=${np.next_num}&letter_type=${param.letter_type}" <c:if test="${np.next_read==0}">style="color:blue;"</c:if>>
				<span class="material-symbols-outlined">expand_more</span>
				<c:if test="${np.next_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.next_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				 <span class="npid">${np.next_receiver_id} ${np.next_title } </span>
				 <span class="npdate">${np.next_date}</span></a>
			</div>
			<div style="clear:both;"></div>
			</c:if>
		</c:if>
	</c:if>
	
	
	<c:if test="${param.letter_type==1 }">
		<c:if test="${np.prev_num != null }">
		<div>
			<a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}" <c:if test="${np.prev_read==0}">style="color:blue;"</c:if>>
			<span class="material-symbols-outlined">expand_less</span>
			<c:if test="${np.prev_read==0 }">
			<span class="material-symbols-outlined">mail</span>
			</c:if>
			<c:if test="${np.prev_read==1 }">
			<span class="material-symbols-outlined">drafts</span>
			</c:if>
			 <span class="npid">${np.prev_mem_name }(${np.prev_send_id}) ${np.prev_title } </span>
			 <span class="npdate">${np.prev_date}</span></a>
		</div>
		</c:if>
		
		<c:if test="${np.next_num != null }">
		<div>
			<a href="detail.do?lt_num=${np.next_num }&letter_type=${param.letter_type}" <c:if test="${np.next_read==0}">style="color:blue;"</c:if>>
			<span class="material-symbols-outlined">expand_more</span>
			<c:if test="${np.next_read==0 }">
			<span class="material-symbols-outlined">mail</span>
			</c:if>
			<c:if test="${np.next_read==1 }">
			<span class="material-symbols-outlined">drafts</span>
			</c:if>
			<span class="npid"> ${np.next_mem_name }(${np.next_send_id}) ${np.next_title } </span>
			 <span class="npdate">${np.next_date}</span></a>
		</div>
		</c:if>
	</c:if>

	<c:if test="${param.letter_type==2 || param.letter_type==3}">
		<c:if test="${np.prev_num != null }">
			<div>
				<a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}" <c:if test="${np.prev_read==0}">style="color:blue;"</c:if>>
				<span class="material-symbols-outlined">expand_less</span>
				<c:if test="${np.prev_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.prev_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				 <span class="npid">${np.prev_receiver_id} ${np.prev_title } </span>
				 <span class="npdate">${np.prev_date}</span></a>
			</div>
		</c:if>
		
		<c:if test="${np.next_num != null }">
			<div>
				<a href="detail.do?lt_num=${np.next_num }&letter_type=${param.letter_type}" <c:if test="${np.next_read==0}">style="color:blue;"</c:if>>
				<span class="material-symbols-outlined">expand_more</span>
				<c:if test="${np.next_read==0 }">
				<span class="material-symbols-outlined">mail</span>
				</c:if>
				<c:if test="${np.next_read==1 }">
				<span class="material-symbols-outlined">drafts</span>
				</c:if>
				<span class="npid"> ${np.next_receiver_id} ${np.next_title }</span>
				 <span class="npdate">${np.next_date}</span></a>
			</div>
		</c:if>
	</c:if>
	
	<hr size="1" width="100%" class="hr-line">
	</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<!-- 내용 끝 -->