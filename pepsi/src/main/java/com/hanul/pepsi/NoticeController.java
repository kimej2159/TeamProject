package com.hanul.pepsi;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private NoticeServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;
	
	//
	
	//신규 공지글 등록 저장 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile file, HttpServletRequest request) {
		//첨부파일이 있는 경우 서버의 물리적인 영역에 첨부된 파일을 저장
		//첨부된 파일을 물리적으로 어디에 어떤 이름으로 저장했는지 DB에 저장
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			common.fileUpload(file, "notice", request);
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
	public String list(Model model, HttpSession session) {
		//임의로 관리자로 로그인 해 둔다--------------------------------
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin1");
		
		map.put("pw", "manager");
		String salt = member.member_salt("admin1");
		map.put("pw", common.getEncrypt(map.get("pw"), salt));
		
		MemberVO vo = member.member_login(map);
		session.setAttribute("loginInfo", vo);
		//--------------------------------------------------------
		
		//DB에서 공지글 목록을 조회해온다
		List<NoticeVO> list = service.notice_list();
		
		//조회해온 정보를 화면에 출력할 수 있게 Model에 담는다
		model.addAttribute("list", list);
		return "notice/list";
	}

}
