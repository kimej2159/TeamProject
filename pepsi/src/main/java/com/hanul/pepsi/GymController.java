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
		ArrayList<GymDTO> list = new ArrayList<GymDTO>();
		list = dao.select_gymList(gym_id);
		System.out.println(list.size());
		model.addAttribute("gymlist" , list);
		return "gym/gym-detail";
		
	}
	
	
}
