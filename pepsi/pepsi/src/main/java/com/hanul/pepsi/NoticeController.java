package com.hanul.pepsi;

import java.util.ArrayList;
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
import notice.NoticeFileVO;
import notice.NoticePageVO;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private NoticeServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;

	// 첨부한 파일정보 관리
	private List<NoticeFileVO> attached_file(MultipartFile[] file, HttpServletRequest request) {
		List<NoticeFileVO> list = null;
		for( MultipartFile attached : file ) {
			
			if( attached.isEmpty() ) continue;
			
			// 여러건의 자료구조 받을거 생성
			if(list==null) list = new ArrayList<NoticeFileVO>();
			NoticeFileVO fileVO = new NoticeFileVO();
			fileVO.setFilename( attached.getOriginalFilename());
			fileVO.setFilepath( common.fileUpload(attached, "notice", request) );
			list.add(fileVO);
		}
		return list;
	}
	
	
	//공지글 수정 저장 요청
	@RequestMapping("/update.no")
	public String update(int id, NoticePageVO page, Model model
			, MultipartFile[] file
			, HttpServletRequest request
			, String removed, NoticeVO vo) {
		
		//첨부된 파일을 저장한다
		List<NoticeFileVO> files = attached_file(file, request);
		vo.setFileInfo(files);
		
		//화면에서 변경 입력한 정보로 DB에 변경저장한다
		service.notice_update(vo);
				
		// 삭제하려는 대상 파일 정보 조회

		if( ! removed.isEmpty()) {
			List<NoticeFileVO> remove_file = service.notice_removed_file(removed);
			
			//DB에서 삭제 + 물리적인 파일 삭제
			if( service.notice_file_delete(removed) > 0) {
				for(NoticeFileVO f : remove_file) {
					common.file_delete(f.getFilepath(), request);
				}
			};
		}
		//공지글 안내 화면으로 응답화면 연결
		model.addAttribute("url", "info.no");
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		
		return "notice/redirect";
	}
	
	
	//공지글 수정 화면 요청
	@RequestMapping("/modify.no")
	public String modify(int id, Model model) {
		//선택한 공지글 정보를 DB에서 조회해온다
		NoticeVO vo =service.notice_info(id);
		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		return "notice/modify";
	}
	
	
	//선택한 공지글 삭제처리 요청
	@RequestMapping("/delete.no")
	public String delete(int id, HttpServletRequest request, Model model) {
		
		//첨부파일 정보를 조회해둔다
		List<NoticeFileVO> files = service.notice_info(id).getFileInfo();
		
		
		// 선택한 글을 DB에서 삭제한다
		if ( service.notice_delete(id) == 1 ) {
			// 첨부되어진 파일을 물리적으로 저장된 영역에서 삭제한다
			for( NoticeFileVO vo : files ) {
				common.file_delete(vo.getFilepath(), request );
			}
		};


		// 응답화면연결 - 목록화면연결
		// redirect 화면에서 출력 할 정보를 Model 에 담는다
		model.addAttribute("url", "list.no");
		model.addAttribute("id", id);
//		model.addAttribute("page", page);
		
		return "notice/redirect";
					
	}
	
	
	//첨부파일 다운로드 처리
	@RequestMapping("/download.no")
	public void download(int id, HttpServletRequest request, HttpServletResponse response) {
		//선택한 공지글의 첨부파일을 서버에서 클라이언트에 다운로드한다
		NoticeVO vo = service.notice_info(id);
		common.fileDownload( vo.getFilename(), vo.getFilepath(), request, response);
		
	}
	
	
	
	//선택한 공지글 정보 화면 요청
	@RequestMapping("/info.no")
	public String info(int id, Model model) {
		//조회수 증가 처리
		service.notice_read(id);
		
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\r");
		
		
		//선택한 공지글 정보를  DB에서 조회
		NoticeVO vo = service.notice_info(id);
		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		return "notice/info";
	}
	
	//신규 공지글 등록 저장 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile[] file, HttpServletRequest request) {
		//첨부파일이 있는 경우 서버의 물리적인 영역에 첨부된 파일을 저장
		//첨부된 파일을 물리적으로 어디에 어떤 이름으로 저장했는지 DB에 저장
		/*	
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath(common.fileUpload(file, "notice", request) );
		}
		 */
		if ( file.length > 1 ) {
			List<NoticeFileVO> list = attached_file(file,request);
			vo.setFileInfo(list);
		}		
		//화면에서 입력한 정보를 DB에 신규 저장
		service.notice_insert(vo);
		
		//응답화면 연결 - 목록
		return "redirect:list.no";
	}
	
	
	//공지글 신규 작성 화면 요청
	@RequestMapping("/regist.no")
	public String regist() {
		return "notice/regist";
	}
	
	
	//공지글 목록 화면 요청 
	@RequestMapping("/list.no")
	public String list(Model model, HttpSession session, NoticePageVO page) {
		
		//임의로 관리자로 로그인 해 둔다--------------------------------
/*		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin1");
		
		map.put("pw", "manager");
		String salt = member.member_salt("admin1");
		map.put("pw", common.getEncrypt(map.get("pw"), salt));
		
		MemberVO vo = member.member_login(map);
		session.setAttribute("loginInfo", vo);
*/	
		//--------------------------------------------------------
		
		//DB에서 공지글 목록을 조회해온다
		//List<NoticeVO> list = service.notice_list();
		page = service.notice_list(page);
		
		//조회해온 정보를 화면에 출력할 수 있게 Model에 담는다
		model.addAttribute("page", page);
		return "notice/list";
	}

}




