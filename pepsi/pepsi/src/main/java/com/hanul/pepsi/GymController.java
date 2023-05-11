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
import trainer.TrainerDTO;

@Controller
public class GymController {
	
	@Autowired private GymDAO dao;
	
	
	@RequestMapping(value = "/gym.ch")
	public String CenterFind(HttpServletRequest request , HttpServletResponse response , Model model) {
		ArrayList<GymDTO> list = new ArrayList<GymDTO>();
		list = dao.select_gymAllList();
		model.addAttribute("gymlist" , list);
		return "gym/gym";
	}
	
	@RequestMapping(value = "/gym-detail.ch")
	public String GymDetail(HttpServletRequest request , HttpServletResponse response , Model model) {
		int gym_id = Integer.parseInt(request.getParameter("gym_id"));
		System.out.println(gym_id);
		
		
		ArrayList<GymDTO> list1 = new ArrayList<GymDTO>();
		ArrayList<TrainerDTO> list2 = new ArrayList<TrainerDTO>();
		
		//gym_id를 매개값으로 DB에서 해당 gym의 정보를 가져오는 메소드를 호출해서 리턴 받은 값을 list에 담아줌  
		list1 = dao.select_gymList(gym_id);
		list2 = dao.select_trainerList(gym_id);
		
		
		//model에 gymlist의 값을 담아줌 
		model.addAttribute("gymlist" , list1);
		model.addAttribute("trainerlist" , list2);
		//model에 trainerlist의 값을 담아줌
		
		
		return "gym/gym-detail";
		
	}


	
	
	
}
