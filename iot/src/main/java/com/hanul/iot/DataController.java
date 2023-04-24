package com.hanul.iot;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonUtility;

@Controller
public class DataController {
	//공공데이터 키 
	private String key = 
			"FPgj2NXbJw46TcGkmAfZEiYFDbxilys7KLjk3KaB7AfeJE00ZhPNM0M8unwbsI69fSmT8SNfVEimE6ZZ2U14hA%3D%3D";

	@Autowired private CommonUtility common;
	private String animalURL 
		= "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/";
	
	//유기동물 보호소 조회 요청
	@RequestMapping("data/animal/shelter")
	public String animal_shelter(String sido, String sigungu, Model model ) {
		StringBuffer url = new StringBuffer( animalURL +"shelter" );
		url.append("?serviceKey=").append(key);
		url.append("&_type=json");
		url.append("&upr_cd=").append(sido);
		url.append("&org_cd=").append(sigungu);
		model.addAttribute("list", common.requestAPItoMap(url));
		return "data/animal/shelter";
	}
	
	
	//유기동물 시군구조회 요청
	@RequestMapping("/data/animal/sigungu")
	public String animal_sigungu(String sido, Model model) {
		StringBuffer url = new StringBuffer( animalURL + "sigungu" );
		url.append("?serviceKey=").append(key);
		url.append("&_type=json");
		url.append("&upr_cd=").append(sido);
		model.addAttribute("list",  common.requestAPItoMap(url));
		
		return "data/animal/sigungu";
	}
	
	//유기동물 시도조회 요청
	@RequestMapping("/data/animal/sido")
	public String animal_sido(Model model) {
		StringBuffer url = new StringBuffer( animalURL + "sido");
		url.append("?serviceKey=").append(key);
		url.append("&_type=json");
		url.append("&numOfRows=").append(30);
		model.addAttribute("list", common.requestAPItoMap(url)) ;
		return "data/animal/sido";
	}
	


	
	
	//유기동물정보조회 요청(해당 정보만 골라서 조회)
	@RequestMapping("/data/animal/list")
	public Object animal_list( @RequestBody HashMap<String, Object> map, Model model ) {
		StringBuffer url = new StringBuffer( animalURL + "abandonmentPublic" );
		url.append("?serviceKey=").append(key);
		url.append("&pageNo=").append( map.get("pageNo") );
		url.append("&numOfRows=").append( map.get("rows") );
		url.append("&upr_cd=").append( map.get("sido") );
		url.append("&org_cd=").append( map.get("sigungu") );
		url.append("&care_reg_no=").append( map.get("shelter") );
		url.append("&upkind=").append( map.get("upkind") );
		url.append("&kind=").append( map.get("kind") );
		url.append("&_type=json");
		model.addAttribute("list", common.requestAPItoMap(url));
		model.addAttribute("page", map.get("pageNo") );
		return "data/animal/animal_list";
	}
	
	//유기동물 축종에 딸린 품종 조회 요청
	@RequestMapping("/data/animal/kind")
	public String animal_kind(Model model, String upkind) {
		StringBuffer url = new StringBuffer( animalURL + "kind");
		url.append("?serviceKey=").append(key);
		url.append("&_type=json");
		url.append("&up_kind_cd=").append(upkind);
		model.addAttribute("list", common.requestAPItoMap(url));
		return "data/animal/kind";
	}
	
	
	//약국 정보 조회 요청
	@ResponseBody @RequestMapping("/data/pharmacy")
	public Object pharmacy_list(int pageNo, int rows) {
		StringBuffer url = new StringBuffer(
				"http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList");
		url.append("?ServiceKey=").append(key);
		url.append("&_type=json");
		url.append("&pageNo=").append( pageNo );
		url.append("&numOfRows=").append( rows );
		return common.requestAPItoMap(url);
		
	}
	
	@RequestMapping("/list.da")
	//공공데이터 목록화면 요청 (선택됐을 때 파란 색 표시)
	public String list(HttpSession session) {
		
		session.setAttribute("category", "da");
		
		return "data/list";
	}
	
}
