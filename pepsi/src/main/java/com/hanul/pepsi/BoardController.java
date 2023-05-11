package com.hanul.pepsi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.BoardCommentVO;
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
		
		
		
	// 방명록 첨부파일 다운로드 요청
	@RequestMapping("/download.bo")
	public void download(int file, HttpServletRequest req, HttpServletResponse res) {
	
		// 해당 첨부파일 정보를 DB에서 조회해와임마
		BoardFileVO vo = service.board_file_info(file);
		
		// 클라이언트쪽에 다운로드 처리한다
		// 화면연결필요없어
		common.fileDownload(vo.getFilename(), vo.getFilepath(), req, res);
	}	
		
		
	
	// 방명록 글 삭제처리 요청
		@RequestMapping("/delete.bo")
		public String delete(int id, BoardPageVO page, Model model, HttpServletRequest request) {
			// 첨부파일 정보를 조회해둔다
			List<BoardFileVO> files = service.board_info(id).getFileInfo();
			
			
			// 선택한 글을 DB에서 삭제한다
			if ( service.board_delete(id) == 1 ) {
				// 첨부되어진 파일을 물리적으로 저장된 영역에서 삭제한다
				for( BoardFileVO vo : files ) {
					common.file_delete(vo.getFilepath(), request );
				}
			};
			
			// 응답화면연결 - 목록화면연결
			// redirect 화면에서 출력 할 정보를 Model 에 담는다
			model.addAttribute("url", "list.bo");
			model.addAttribute("id", id);
			model.addAttribute("page", page);
			
			return "board/redirect";
			
		}
		 
		
		
	// 방명록 글 수정화면 요청
	@RequestMapping("/modify.bo")
	public String modify(Model model, int id, BoardPageVO page) {
		
		// 선택한 글 정보를 DB에서 조해해왕
		BoardVO vo = service.board_info(id);
		
		// 화면에 출력 할 수 있도록 Model 에 담아
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
				
		return "board/modify";
	}
	
	

	
	// 방명록 글 수정 저장처리 요청
	@RequestMapping("/update.bo")
	public String update(int id, BoardPageVO page, Model model, BoardVO vo, String removed, MultipartFile[] file, HttpServletRequest request) {
		
		// 첨부되어진 파일이 있다면 해당 파일 정보를 저장한다
		List<BoardFileVO> files = attached_file(file, request); // 파일목록
		vo.setFileInfo(files);
		// 화면에서 변경 입력한 정보로 DB에 변경저장한다
		service.board_update(vo);
		
		// 삭제하려는 대상 파일 정보 조회
		if ( ! removed.isEmpty() ) {
			
			List<BoardFileVO> remove_file = service.board_removed_file(removed);
			
			// DB에서 삭제 + 물리적인 파일 삭제
			if ( service.board_file_delete(removed) > 0 ) {
				for( BoardFileVO f : remove_file ) {
					common.file_delete(f.getFilepath(), request);
				}
			};
			
		}
		// 화면연결 - 정보화면
		model.addAttribute("url", "info.bo");
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		
		return "board/redirect";
		
	}
	

	
	
	
	// 방명록 댓글 저장처리 요청
	@ResponseBody @RequestMapping("/board/comment/insert")
	public boolean board_comment_regist(BoardCommentVO vo) {
		
		// 화면에서 입력한 댓글정보로 DB에 신규저장
		return service.board_comment_regist(vo)==1 ? true : false;
		
	}
	
	
	// 방명록 댓글 목록조회 요청
	@RequestMapping("board/comment/list/{id}")
	public String board_comment_list(@PathVariable int id, Model model) {
		
		// 해당 방명록글에 대한 댓글목록을 DB에서 조회해온다
		List<BoardCommentVO> list = service.board_comment_list(id);
		
		// 화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("list", list);
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		
		return "board/comment/comment_list";
		
	}
	
	
	// 방명록 댓글 변경저장처리 요청
//	@ResponseBody @RequestMapping("board/comment/update")
//	public void board_comment_update(@RequestBody BoardCommentVO vo) {
//		service.board_comment_update(vo);
//	}
	@ResponseBody @RequestMapping(value="board/comment/update", produces="application/text; charset=utf-8")
	public String board_comment_update(@RequestBody BoardCommentVO vo) {
		return service.board_comment_update(vo)==1?"성공":"실패";
	}
	
	
	
	//  방명록 댓글 삭제처리 요청
	@ResponseBody @RequestMapping("/board/comment/delete/{id}")
	public void board_comment_delete(@PathVariable int id) {
		// 선택한 댓글을 DB에서 삭제
		service.board_comment_delete(id);
	}
	
	
	
	
	
		
	
	
	
		 
		 
		 
	

}
