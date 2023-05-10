package com.hanul.pepsi;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		/*
		 * List<MemberVO> list = member.member_list(); for(MemberVO vo : list) { if(
		 * vo.getSocial()!=null ) continue; if( vo.getSalt()!=null ) continue; String
		 * salt = common.generateSalt(); //솔트를 사용해 비번을 암호화 String pw =
		 * common.getEncrypt(vo.getPw(), salt); vo.setPw(pw); vo.setSalt(salt);
		 * member.member_change_pw(vo); }
		 * 
		 */ 
		session.removeAttribute("category");
		return "home";
	}
	
}
