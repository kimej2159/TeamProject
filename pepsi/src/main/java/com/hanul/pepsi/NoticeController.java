package com.hanul.pepsi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	
	//공지글 목록 화면 요청 
	@RequestMapping("/list.no")
	public String list() {
		
		return "notice/list";
	}

}
