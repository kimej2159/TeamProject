package com.hanul.iot;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import visual.VisualServiceImpl;

@RestController
public class VisualController {
	@Autowired private VisualServiceImpl service;
	
	//상위 3개 부서에 대한 채용인원수 조회
	@RequestMapping("/visual/hirement/top3/{unit}")
	public Object hirement_top3_year(@PathVariable String unit) {
		if( unit.equals("year")) {
			return service.hirement_top3_year();
		}else
			return service.hirement_top3_month();
	}
	
	//채용 인원수 조회
	@RequestMapping("/visual/hirement/year")
	public Object hirement_year() {
		return service.hirement_year();
	}
	
	@RequestMapping("/visual/hirement/month")
	public Object hirement_month() {
		return service.hirement_month();
		
	}
	
	//부서별 사원수 조회
	//@ResponseBody 
	@RequestMapping("/visual/department")
	public List<HashMap<String, Object>> department() {
		return service.department();
	}
	
	
	//시각화 화면 요청-homecontroller로 옮겨놓음
//	@RequestMapping("/visual/list")
//	public String list(HttpSession session) {
//		session.setAttribute("category", "vi");
//		return "visual/list";
//	}
}
