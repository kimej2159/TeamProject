package com.hanul.iot;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import board.BoardCommentVO;
import board.BoardPageVO;
import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;
import question.QuestionCommentVO;
import question.QuestionPageVO;
import question.QuestionServiceImpl;
import question.QuestionVO;

@Controller
public class QuestionController {

	
	@Autowired private QuestionServiceImpl service;
	
	@Autowired private CommonUtility common;
	
	@Autowired private MemberServiceImpl member;
	
	//질문글 댓글 목록 조회 요청
	@RequestMapping("/question/comment/list/{id}")
	public String question_comment_list(@PathVariable int id, Model model ) {
		//해당 질문글에 대한 댓글 목록을 DB에서 조회해 온다
		List<QuestionCommentVO> list = service.question_comment_list(id);
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("list", list);
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "question/comment/comment_list";
	}
	
	//질문글 댓글 삭제 처리 요청
	@ResponseBody @RequestMapping("/question/comment/delete/{id}")
	public void question_comment_delete(@PathVariable int id) {
		//선택한 댓글을 DB에서 삭제
		service.question_comment_delete(id);
	}
	
	//질문글 댓글 변경 저장 처리 요청
	@ResponseBody @RequestMapping(value="/question/comment/update"
			, produces="application/text; charset=utf-8")
	public String question_comment_update(@RequestBody QuestionCommentVO vo) {
		return service.question_comment_update(vo)==1 ? "성공" : "실패";
	}
	
	//질문글에 대한 댓글 저장 처리 요청
	@ResponseBody @RequestMapping("/question/comment/insert")
	public boolean question_comment_regist(QuestionCommentVO vo) {
		//화면에서 입력한 댓글 정보로 DB에 신규 저장
		return service.question_comment_regist(vo)==1 ? true : false;
	}
	
	
	//질문글 수정 화면 요청
	@RequestMapping("/modify.qu")
	public String modify(Model model, int id
						, QuestionPageVO page) {
		//선택한 글정보를 DB에서 조회한다
		QuestionVO vo = service.question_info(id);
		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "question/modify";
	}
	
	//질문록 글 수정 저장 처리 요청
	@RequestMapping("/update.qu")
	public String update(int id, Model model, QuestionPageVO page
							,QuestionVO vo) {
		
		//vo에 변경된 정보를 저장한다
		
		
		//화면에서 변경 입력한 정보로 DB에 변경한다
		service.question_update(vo);
		
		//화면연결 - 정보화면
		model.addAttribute("url", "info.qu" );
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "question/redirect";
		
	}
	
	
	
	

	//질문글 삭제 처리 요청
	@RequestMapping("/delete.qu")
	public String delete(int id, BoardPageVO page, Model model) {
		
		service.question_info(id).getFileInfo();
		
		//선택한 글을 DB에서 삭제한다
		service.question_delete(id);
		
		//응답화면연결 - 목록
		//redirect 화면에서 출력할 정보를 Model에 담는다
		model.addAttribute("url", "list.qu");
		model.addAttribute("id", id);
		model.addAttribute("page", page);
		return "question/redirect";
		
	}

	//질문글 새글쓰기화면 요청
	@RequestMapping("/new.qu")
	public String question() {
		return "question/regist";
	}
	
	//방명록 새글신규저장처리 요청
	@RequestMapping("/insert.qu")
	public String insert(QuestionVO vo, HttpServletRequest request) {

		//화면에서 입력한 정보로 DB에 신규저장
		service.question_insert(vo);
		//화면연결
		return "redirect:list.qu";
	}



	//선택한 질문 글 화면 요청
	@RequestMapping("/info.qu")
	public String info(Model model, int id, QuestionPageVO page) {
		//조회수 처리
		service.question_read(id);
		//선택한 글의 정보를 DB에서 조회해 온다
		QuestionVO vo = service.question_info(id);
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "question/info";
		
	}
	
	
	//질문글 목록 화면 요청
	@RequestMapping("/list.qu")
	public String question(Model model, HttpSession session
			, QuestionPageVO page) {
		
		// 임의로 관리자로 로그인해 둔다 -----------------
//		HashMap<String, String> map = new HashMap<String, String>();
//		String id = "admin2";	//admin2  kimej2159
//		map.put("id", id);		
//		map.put("pw", "manager");	//manager   kimej2159
//		String salt = member.member_salt(id);
//		map.put("pw", common.getEncrypt(map.get("pw"), salt) );
//		
//		MemberVO vo = member.member_login( map);
//		session.setAttribute("loginInfo", vo);
		//-----------------------------------------
		
		session.setAttribute("category", "qu");
		//DB에서 방명록 목록을 조회해 온다
		page = service.question_list(page);
		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("page", page);
		return "question/list";
	}
	

}



















