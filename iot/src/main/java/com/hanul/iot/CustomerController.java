package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.CustomerServiceImpl;
import customer.CustomerVO;

@Controller
public class CustomerController {
	@Autowired private CustomerServiceImpl service;
		
	//신규고객등록처리 요청
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		//화면에서 입력한 정보로  DB에 신규삽입저장한다.
		service.customer_insert(vo);
		//응답화면연결 - 고객목록
		return "redirect:list.cu";
	}
	
	
	//신규고객등록화면 요청
	@RequestMapping("/new.cu")
	public String customer() {
		return "customer/new";
	}
	
	//선택한 고객정보 수정저장처리 요청
	@RequestMapping("/update.cu")
	public String update(CustomerVO vo) {
		//화면에서 변경입력한 정보를 DB에 변경저장한다
		service.customer_update(vo);
		//응답화면연결-고객정보
		return "redirect:info.cu?id=" + vo.getId();
	}
	
	
	
	//선택한 고객정보 수정화면 요청
	@RequestMapping("/modify.cu")
	public String modify(Model model, int id) {
		//선택한 고객정보를 DB에서 조회해와
		CustomerVO vo = service.customer_info(id);
		//고객수정화면에서 조회한 정보를 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		//응답화면연결 - 고객수정
		return "customer/modify";
	}
	
	
	//선택한 고객정보 삭제처리 요청
	@RequestMapping("/delete.cu")
	public String delete(int id) {
		//선택한 고객정보를 DB에서 삭제
		service.customer_delete(id);
		//응답화면연결 - 고객목록
		return "redirect:list.cu";
	}
	
	
	//선택한 고객정보화면 요청
	@RequestMapping("/info.cu")
	public String info(int id, Model model) {
		//해당 고객정보를 DB에서 조회해온다
		CustomerVO vo = service.customer_info(id);
		//화면에 출력할 수 있도록 Model에 attribute로 담는다
		model.addAttribute("vo", vo);
		
		//응답화면연결
		return "customer/info";
	}
	
	
	//고객목록화면 요청
	@RequestMapping("/list.cu")
	public String list(Model model, HttpSession session ) {
		session.setAttribute("category", "cu");
		List<CustomerVO> list = service.customer_list();
		model.addAttribute("list", list);
		return "customer/list";
	}
}
