package com.hanul.pepsi;


import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;


@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonUtility common;
	
	//비밀번호 변경 화면 요청
	@RequestMapping("/changepw")
	public String changepw(HttpSession session) {
		//로그인 상태인 경우
		//로그인하지 않은 상태의 경우 로그인 화면으로 연결
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		if( vo==null ) return "redirect:login";
		//응답화면 연결 - 비밀번호 변경화면
		else {
			session.setAttribute("category", "changepw");
			return "default/member/password";
		}
		
		
	}
	
	//비밀번호 재발급 처리 요청
	@ResponseBody
	@RequestMapping(value="/reset", produces="text/html; charset=utf-8") 
	public String reset(MemberVO vo) {
		//화면에서 입력한 아이디,이메일이 일치하는 회원에게 
		//임시 비번을 발급해서 이메일로 보낸다
		StringBuffer msg ;
		//아이디와 이메일 일치여부를 확인하여
		String name = service.member_id_email(vo);
		if( name != null ) {
			vo.setName(name);
			
			//임시 비번으로 사용할 랜덤한 문자열 만들기
			String pw = UUID.randomUUID().toString(); //dahj2324-h24fajk-adfl35ajf
			pw = pw.substring( pw.lastIndexOf("-") + 1 );//adfl35ajf
			
			//암호화용 솔트생성
			String salt = common.generateSalt();
			//솔트를 사용해 임시비번을 암호화
			vo.setPw( common.getEncrypt(pw, salt) );
			vo.setSalt(salt);
		
			//임시 비번으로 해당 회원정보를 변경
			service.member_change_pw(vo);
			
			//임시 비번을 해당 이메일로 보내기
			boolean send = common.sendPassword(vo, pw);
			msg = new StringBuffer("<script>");
			if( send ) {
				msg.append("alert('임시 비밀번호가 발급되었습니다.\\n이메일을 확인하세요'); ")
				.append("location='login' ");
			}else{
				msg.append("alert('임시 비밀번호 이메일 전송에 실패하였습니다');")
					.append("history.go(-1)");
			}
			msg.append("</script>");
			
		}else {
			msg = new StringBuffer("<script>");
			msg.append("alert('아이디나 이메일이 맞지 않습니다\\n확인하세요!' ); ");
			msg.append("history.go(-1)");
			msg.append("</script>");
		}
		
		return msg.toString();
	}
	
	//비밀번호 찾기 화면 요청
	@RequestMapping("/findpw")
	public String findpw() {
		
		return "default/member/find";
	}
	
	//로그아웃 처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//세션에 저장한 로그인 정보를 삭제
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}
	
	//로그인 처리 요청: ajax 통신으로 연결
	@ResponseBody @RequestMapping("/pepsiLogin")
	public boolean login(String id, String pw, HttpSession session) {
		//화면에서 입력한 아이디의 솔트를 조회해와
		//해당 솔트를 사용해서 입력한 비번을 암호화한다
		String salt = service.member_salt(id);
		pw = common.getEncrypt(pw, salt);
		
		//화면에서 입력한 아이디/비번이 일치하는 회원 정보를 조회해온다
		HashMap<String, String > map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);
		return vo==null ? false : true;
	}
	
	
	//로그인 화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session){
		session.setAttribute("category", "login");
		return "default/member/login";
	}
}
