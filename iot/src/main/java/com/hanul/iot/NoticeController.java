package com.hanul.iot;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticePageVO;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private NoticeServiceImpl service;
	
	//첨부파일 다운로드처리
	@RequestMapping("/download.no")
	public void download(int id, HttpServletRequest request
						, HttpServletResponse response) {
		//선택한 공지글의 첨부파일을 서버에서 클라이언트에 다운로드한다
		NoticeVO vo = service.notice_info(id);
		common.fileDownload( vo.getFilename(), vo.getFilepath()
							, request, response);	
	}
	
	
	//선택한 공지글삭제처리 요청
	@RequestMapping("/delete.no")
	public String delete(int id, NoticePageVO page
						, HttpServletRequest request) 
									throws Exception{
		//첨부파일이 있는 공지글은 서버에서 물리적인 파일도 삭제
		NoticeVO vo = service.notice_info(id);
		
		//선택한 공지글정보를  DB에서 삭제한다
		if( service.notice_delete(id) == 1 ) {
			common.file_delete(vo.getFilepath(), request);
		}
		//목록화면연결
		return "redirect:list.no?curPage="+ page.getCurPage()
				+"&search="+ page.getSearch()
				+"&keyword="+
					URLEncoder.encode( page.getKeyword(), "utf-8");
	}
	
	
	//선택한 공지글수정저장처리 요청
	@RequestMapping("/update.no")
	public String update(NoticeVO vo, NoticePageVO page
						, MultipartFile file
						, HttpServletRequest request) throws Exception{
		NoticeVO notice = service.notice_info( vo.getId() );
		
		//파일 첨부하지 않는 경우
		if( file.isEmpty() ) {
			if( vo.getFilename().isEmpty() ) {				
				//원래 첨부파일 X --> 첨부X
				//원래 첨부파일 O --> 첨부X
				common.file_delete(notice.getFilepath(), request);
				
			}else {
				//원래 첨부파일 O --> 그대로 사용: 원래 정보로 담아둔다
				vo.setFilename( notice.getFilename() );
				vo.setFilepath( notice.getFilepath() );
			}
		
		}else {
		//파일 첨부하는 경우
		//원래 첨부파일 X --> 첨부
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUpload(file, "notice", request) );
			
		//원래 첨부파일 O --> 바꿔 첨부
			common.file_delete(notice.getFilepath(), request);
		}
		
		//화면에서 변경입력한 정보로 DB에 변경저장한다
		service.notice_update(vo);
		//공지글안내화면연결
		return "redirect:info.no?id="+ vo.getId() 
				+ "&curPage="+ page.getCurPage()
				+ "&search="+ page.getSearch()
				+ "&keyword="
					+ URLEncoder.encode(page.getKeyword(), "utf-8")
				;
	}
	
	
	
	//선택한 공지글수정화면 요청
	@RequestMapping("/modify.no")
	public String modify(int id, Model model, NoticePageVO page) {
		//선택한 공지글정보를 DB에서 조회해온다
		NoticeVO vo = service.notice_info(id);
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "notice/modify";
	}
	
	
	
	//선택한 공지글정보화면 요청
	@RequestMapping("/info.no")
	public String info(int id, NoticePageVO page, Model model) {
		//조회수 증가처리
		service.notice_read(id);
		
		model.addAttribute("crlf", "\r\n");		
		model.addAttribute("lf", "\n");		
		
		//선택한 공지글정보를 DB에서 조회한다
		NoticeVO vo = service.notice_info(id);
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "notice/info";
	}
	
	//신규공지글 저장처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile file
						, HttpServletRequest request) {
		//첨부파일이 있는 경우 서버의 물리적영역에 첨부된 파일을 저장
		//첨부된 파일을 물리적으로 어디에, 어떤이름으로 저장했는지를 DB에 저장
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( 
					common.fileUpload(file, "notice", request) );
		}
		
		//화면에서 입력한 정보로 DB에 신규저장
		service.notice_insert(vo);
		//응답화면연결 -목록
		return "redirect:list.no";
	}
	
	
	//공지글신규작성화면 요청
	@RequestMapping("/regist.no")
	public String regist() {
		return "notice/regist";
	}
	
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;
	
	//답글저장처리 요청
	@RequestMapping("/reply_insert.no")
	public String reply_insert(NoticeVO vo
							, NoticePageVO page
							, MultipartFile file
							, HttpServletRequest request) 
									throws Exception {
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUpload(file, "notice", request));
		}
		
		//화면에서 입력한 답글정보로 DB에 신규저장한다
		service.notice_reply_insert(vo);
		//목록화면연결
		return "redirect:list.no?curPage="+page.getCurPage()
				+ "&search=" + page.getSearch()
				+ "&keyword=" 
					+ URLEncoder.encode(page.getKeyword(),"utf-8" )
				;
	}
	
	//답글쓰기화면 요청
	@RequestMapping("/reply.no")
	public String reply(int id, Model model
						, NoticePageVO page) {
		//답글쓰기 데이터 저장시 필요한 원글의 정보를 조회한다
		//답글쓰기 화면에서 사용할 수 있도록 Model에 담는다
		model.addAttribute("vo", service.notice_info(id) );
		model.addAttribute("page", page);
		return "notice/reply";
	}
	
	//공지글목록화면 요청
	@RequestMapping("/list.no")
	public String list(Model model, HttpSession session
						, NoticePageVO page) {
		// 임의로 관리자로 로그인해 둔다 -----------------
		HashMap<String, String> map = new HashMap<String, String>();
		String id = "admin2";
//		String id = "hong2023";
		map.put("id", id);		
		map.put("pw", "manager");
//		map.put("pw", "Hong2023");
		String salt = member.member_salt(id);
		map.put("pw", common.getEncrypt(map.get("pw"), salt) );
		
		MemberVO vo = member.member_login( map);
		session.setAttribute("loginInfo", vo);
		//-----------------------------------------
		
		session.setAttribute("category", "no");
		//DB 에서 공지글목록을 조회해온다
		//List<NoticeVO> list = service.notice_list();
		page = service.notice_list(page);
		
		//조회해온 정보를 화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("page", page);
		return "notice/list";
	}
}
