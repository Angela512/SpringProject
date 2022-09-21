package kr.spring.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import kr.spring.workflow.service.WorkflowMainService;
import kr.spring.workflow.vo.WorkflowMainVO;
import kr.spring.workflow.vo.WorkflowSignVO;
import kr.spring.workflow.vo.WorkflowVO;

@Controller
public class WorkflowController {
	private static final Logger logger =
		         LoggerFactory.getLogger(
				          WorkflowController.class);
	
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private WorkflowMainService flowService;
	@Autowired
	private MemberService memberService;
	
	
	//자바빈(VO) 초기화
//	@ModelAttribute
//	public WorkflowMainVO initCommand() {
//	return new WorkflowMainVO();
//	}
	
	@ModelAttribute
	public WorkflowVO initCommand() {
	return new WorkflowVO();
	}
	
	
	
	
	//===========게시판 글 목록============//
	@RequestMapping("/workflow/list.do")
	public ModelAndView process(
		@RequestParam(value="pageNum",defaultValue="1") 
		int currentPage,
		@RequestParam(value="keyfield",defaultValue="")
		String keyfield,
		@RequestParam(value="keyword",defaultValue="")
		String keyword,
		HttpSession session) {
	
    MemberVO user = (MemberVO)session.getAttribute("user");
    
		
	Map<String,Object> map = 
			    new HashMap<String,Object>();
	map.put("keyfield", keyfield);
	map.put("keyword", keyword);
	map.put("sign", user.getMem_name()); //이름 + 직책
	map.put("mem_num", user.getMem_num());
	
	//글의 총개수(검색된 글의 개수)
	int count = flowService.selectRowCount(map);
	logger.debug("<<count>> : " + count);
	
	//페이지 처리
	PagingUtil page = 
			new PagingUtil(keyfield,keyword,
					currentPage,count,
					rowCount,pageCount,"list.do");
	
	List<WorkflowVO> list = null;
	List<MemberVO> list2 = null;
	if(count > 0) {
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		list = flowService.selectList(map);
		list2 = memberService.selectList(map);;
	}
	
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("boardList"); //////////////////////////////////
	mav.addObject("count", count);
	mav.addObject("list", list);
	mav.addObject("list2", list2);
	mav.addObject("page", page.getPage());
	
	return mav;
	}
	
	
	
	
	
//	//===========파일다운로드===========//
//	@RequestMapping("/board/file.do")
//	public ModelAndView download(
//		   @RequestParam int board_num) {
//	
//	BoardVO board = 
//			boardService.selectBoard(board_num);
//	
//	ModelAndView mav = new ModelAndView();
//	mav.setViewName("downloadView");
//	mav.addObject("downloadFile", 
//			       board.getUploadfile());
//	mav.addObject("filename", 
//			          board.getFilename());
//	
//	return mav;
//	}
//	
//	//=========이미지 출력=========//
//	@RequestMapping("/board/imageView.do")
//	public ModelAndView viewImage(
//		   @RequestParam int board_num,
//		   @RequestParam int board_type) {
//	
//	BoardVO board = 
//			boardService.selectBoard(board_num);
//	
//	ModelAndView mav = new ModelAndView();
//	//뷰 이름
//	mav.setViewName("imageView");
//	
//	if(board_type==1) {//프로필 사진
//		mav.addObject("imageFile", board.getPhoto());
//		mav.addObject("filename", board.getPhoto_name());
//	}else if(board_type==2) {//업로드된 이미지
//		mav.addObject("imageFile", board.getUploadfile());
//		mav.addObject("filename", board.getFilename());
//	}
//	return mav;
//	}
//	
	
	
	//===========게시판 글수정===========//
	//수정 폼
	@GetMapping("/workflow/update.do")
	public String formUpdate(
		@RequestParam int flow_num,
		                         Model model) {
	WorkflowVO flowVO = 
			flowService.selectBoard(flow_num);
	
	model.addAttribute("workflowVO", flowVO);
	
	return "boardModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/workflow/update.do")
	public String submitUpdate(@Valid WorkflowVO flowVO,
		            BindingResult result,
		            HttpServletRequest request,
		            Model model) {
	logger.debug("<<글수정>> : " + flowVO);
	
	//유효성 체크 결과 오류가 있으면 폼 호출
	if(result.hasErrors()) {
		//title 또는 content가 입력되지 않아 유효성 체크에
		//걸리면 파일 정보를 잃어버리기 때문에 품을
		//호출할 때 다시 셋팅해주어야 함.
		WorkflowVO vo = flowService.selectBoard(
				flowVO.getFlow_num());
//		flowVO.setFilename(vo.getFilename());
		return "boardModify";
	}
	
	
	//글수정
	flowService.updateBoard(flowVO);
	flowService.updateSign(flowVO);
	
	//View에 표시할 메시지
	model.addAttribute("message", "글수정 완료!!");
	model.addAttribute("url", 
			request.getContextPath()+"/workflow/detail.do?flow_num="+flowVO.getFlow_num());	
	
	return "common/resultView";
	}
//	
//	//==========게시판 글삭제==========//
//	@RequestMapping("/board/delete.do")
//	public String submitDelete(
//		       @RequestParam int board_num,
//		       Model model,
//		       HttpServletRequest request) {
//	
//	logger.debug("<<글삭제>> : " + board_num);
//	
//	//글삭제
//	boardService.deleteBoard(board_num);
//	
//	//View에 표시할 메시지
//	model.addAttribute("message", "글삭제 완료!!");
//	model.addAttribute("url", 
//			request.getContextPath()+"/board/list.do");
//	
//	return "common/resultView";
//	}
	
	

	
	@RequestMapping("/workflow/signList.do")
	public ModelAndView line() {
	
	List<MemberVO> list = memberService.selectSignList();
	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("signList"); 
	mav.addObject("list", list);
	
	return mav;
	}
	
	
	@PostMapping("/workflow/signList.do")
	public String insertreview(HttpSession session,WorkflowVO work) throws Exception{
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//회원번호 세팅
		work.setMem_num(user.getMem_num());
		//테스트 값 세팅
		work.setFlow_line("테스트");
		/*
		boolean result = flowService.insertSign(sign);
		if (result) {
			return "workflow/autoClose";
		} else {
			return "error";
		}
		*/
		//flowService.insertTest(work);
		return "workflow/autoClose";
	}
		
	/////-------------------테스트 코드2-----------------------////////////////
		
	//===========게시판 글 등록============//
		
		//등록 폼
		@GetMapping("/workflow/write.do")
		public String form() {
		return "boardWrite";
		}
		//등록 폼에서 전송된 데이터 처리
		@PostMapping("/workflow/write.do")
		public String submit(@Valid WorkflowVO flowVO,
			      BindingResult result,
			      HttpServletRequest request,
			      HttpSession session,
			      Model model) {
		
		logger.debug("<<게시판 글 저장>> : " + flowVO);
		
		//유효성 검사 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		//회원번호 셋팅
		flowVO.setMem_num(user.getMem_num());	
		
		flowService.insertFlow_num(flowVO);
		
		
		//View에 표시할 메시지
		model.addAttribute(
				"message", "글 등록이 완료되었습니다.");
		model.addAttribute(
		"url", request.getContextPath()+"/workflow/list.do");
		
		return "common/resultView";
		}
		
		
		
		
		
     /////-------------여기 까지2-----------------//////////	
		
		
		//========게시판 글상세===========//
		@RequestMapping("/workflow/detail.do")
		public ModelAndView detail(
			          @RequestParam int flow_num,HttpSession session) {
		
		logger.debug("<<board_num>> : " + flow_num);
		
		WorkflowVO workflow = 
				flowService.selectBoard(flow_num);
		
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		List<MemberVO> list = memberService.selectSignList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardView"); 
		mav.addObject("workflow",workflow);
		mav.addObject("list", list);
		mav.addObject("user_name", user.getMem_name());
		
		
		//제목에 태그를 허용하지 않음
//		workflow.setFlow_title(
//			 StringUtil.useNoHtml(workflow.getFlow_title()));
		//내용에 줄바꿈 처리하면서 태그를 허용하지 않음
		//ckeditor 사용시 아래 코드 주석 처리
		/*
		board.setContent(
		StringUtil.useBrNoHtml(board.getContent()));
		*/
	
		return mav;
		}	
		
		
		
		
		//===========승인 테스트===========//
		@GetMapping("/workflow/ok.do")
		public String formUpdate2(
			@RequestParam int flow_num,
			                         Model model) {
		WorkflowVO flowVO = 
				flowService.selectBoard(flow_num);
		
		model.addAttribute("workflowVO", flowVO);
		
		return "boardModify";
		}
		
		
		
		
		//수정 폼에서 전송된 데이터 처리
		@PostMapping("/workflow/ok.do")
		public String submitUpdate2(@Valid WorkflowVO flowVO,
			            BindingResult result,
			            HttpServletRequest request,
			            Model model) {
		logger.debug("<<글수정>> : " + flowVO);
		
		
		
		//글수정

		flowService.updateSignOk(flowVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "승인 완료!!");
		model.addAttribute("url", 
				request.getContextPath()+"/workflow/detail.do?flow_num="+flowVO.getFlow_num());		
		
		return "common/resultView";
		}
		
	
		
	
		
		

}
