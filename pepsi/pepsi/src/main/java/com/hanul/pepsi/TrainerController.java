package com.hanul.pepsi;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gym.GymDAO;
import gym.GymDTO;
import trainer.TrainerDAO;
import trainer.TrainerDTO;

@Controller
public class TrainerController {
	
	@Autowired TrainerDAO trainerdao;
	@Autowired private GymDAO gymdao;
	
	//메뉴에서 강사매칭 클릭시 보여줄 페이지
	@RequestMapping(value = "/trainer.ch")
	public String TrainerFind(HttpServletRequest request , HttpServletResponse response , Model model) {
		
		ArrayList<TrainerDTO> list = new ArrayList<>();
		list = trainerdao.select_trainerAllList();
		model.addAttribute("trainerlist" , list);
		
		
		return "trainer/trainer";
		
	}
	
	@RequestMapping(value = "/trainer-detail.ch")
	public String TrainerDetail(HttpServletRequest request , HttpServletResponse response , Model model) {
		
		String trainer_name = request.getParameter("trainer_name");
		System.out.println(trainer_name);
		
		ArrayList<TrainerDTO> list1 = new ArrayList<>();
		ArrayList<GymDTO> list2 = new ArrayList<>();
		
		
		list1 = trainerdao.select_trainer(trainer_name);
		list2 = gymdao.select_GymofTrainer(trainer_name);
		
		
		model.addAttribute("TrainerList" , list1);
		model.addAttribute("GymList" , list2);
		
		
		return "trainer/trainer-detail";
	}
	
}
