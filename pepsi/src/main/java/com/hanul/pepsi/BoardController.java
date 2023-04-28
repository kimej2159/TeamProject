package com.hanul.pepsi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import board.BoardFileVO;
import board.BoardPageVO;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonUtility;

@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private CommonUtility common2;
	
	
	//게시판 글 목록 화면요청
	  
	 @RequestMapping("/list.bo")
	 public String board(Model model, HttpSession session, BoardPageVO page) {
	  
		 session.setAttribute("category", "bo");
		 
		 // DB에서 목록조회
		 page = service.board_list(page);
		 
		 // 화면에 출력 model에 담는다
		 model.addAttribute("page", page);
		 
		 return "board/list"; 
		 
	  }
	
	// 새글쓰기
	@RequestMapping("/new.bo")
	public String board() {
		
		return "board/regist";
	}
	
	
	// 새글 신규 저장
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile[] file, HttpServletRequest request) {
		
		// 첨부파일 처리
		if ( file.length > 1 ) {
			List<BoardFileVO> list = attached_file(file,request);
			vo.setFileInfo(list);
		}
		// 화면에서 입력한 정보로 DB에 신규저장
		service.board_insert(vo);
		
		// 화면연결		
		return "redirect:list.bo";
	}
	
	
	@Autowired private CommonUtility common;
	// 첨부한 파일정보 관리
	private List<BoardFileVO> attached_file(MultipartFile[] file, HttpServletRequest request) {
		List<BoardFileVO> list = null;
		for( MultipartFile attached : file ) {
			
			if( attached.isEmpty() ) continue;
			
			// 여러건의 자료구조 받을거 생성
			if(list==null) list = new ArrayList<BoardFileVO>();
			BoardFileVO fileVO = new BoardFileVO();
			fileVO.setFilename( attached.getOriginalFilename());
			fileVO.setFilepath( common.fileUpload(attached, "board", request) );
			list.add(fileVO);
		}
		return list;
	}
	
	
	// 선택한 방명록 정보화면 요청
		@RequestMapping("/info.bo")
		public String info( Model model, int id, BoardPageVO page) {
			
			// 조회수 증가처리
			service.board_read(id);
			
			// 선택한 글의 정보를 DB에서 조회해온다
			BoardVO vo = service.board_info(id);
			
			// 화면에 출력할 수 있도록 Model에 담는다
			model.addAttribute("vo", vo);
			model.addAttribute("page", page);
			// 줄바꿈적용
			model.addAttribute("crlf", "\r\n");
			model.addAttribute("lf", "\n");
			
			return "board/info";
		}
		
		
		
		
		
		
	
	
		 
		 
		 
		 
	

}
