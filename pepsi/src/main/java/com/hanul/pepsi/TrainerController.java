package com.hanul.pepsi;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



import trainer.TrainerDAO;
import trainer.TrainerDTO;

@Controller
public class TrainerController {
	
	@Autowired TrainerDAO dao;
	
	//메뉴에서 강사매칭 클릭시 보여줄 페이지
	@RequestMapping(value = "/trainer.ch")
	public String TrainerFind(HttpServletRequest request , HttpServletResponse response , Model model) {
		
		ArrayList<TrainerDTO> list = new ArrayList<>();
		list = dao.select_trainerAllList();
		model.addAttribute("trainerlist" , list);
		
		
		return "trainer/trainer";
		
	}
	
	@RequestMapping(value = "/trainer-detail.ch")
	public String TrainerDetail(HttpServletRequest request , HttpServletResponse response , Model model) {
		
		
		
		return "trainer-detail";
	}
	
}
