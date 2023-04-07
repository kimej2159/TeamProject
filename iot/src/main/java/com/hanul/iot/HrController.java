package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.DepartmentVO;
import hr.EmployeeVO;
import hr.HrServiceImpl;
import hr.JobVO;

@Controller
public class HrController {
	@Autowired private HrServiceImpl service;
	
	//신규사원등록저장처리 요청
	@RequestMapping("/insert.hr")
	public String regist(EmployeeVO vo) {
		//화면에서 입력한 정보를 DB에 신규저장한다
		service.employee_insert(vo);
		//응답화면연결 - 사원목록
		return "redirect:list.hr";
	}
	
	//신규사원등록화면 요청
	@RequestMapping("/new.hr")
	public String employee(Model model) {
		//사원등록시 지정할 관리자/부서/업무목록 을 DB에서 조회해와
		//등록화면에 출력할 수 있도록 Model에 담는다
		List<DepartmentVO> departments
			= service.hr_department_list();
		List<JobVO> jobs = service.hr_job_list();
		List<EmployeeVO> managers = service.hr_manager_list();
		model.addAttribute("departments", departments);
		model.addAttribute("jobs", jobs);
		model.addAttribute("managers", managers);
		return "hr/regist";
	}
	
	
	//선택한 사원정보 수정저장처리  요청
	@RequestMapping("/update.hr")
	public String update(EmployeeVO vo) {
		//화면에서 변경입력한 정보를 DB에 변경저장한다
		service.employee_update(vo);
		//응답화면연결 - 사원정보화면
		return "redirect:info.hr?id=" + vo.getEmployee_id();
	}
	
	//선택한 사원정보수정화면 요청
	@RequestMapping("/modify.hr")
	public String modify(Model model, int id) {
		//회사의 전체부서목록, 전체업무목록을 조회해온다
		List<DepartmentVO> departments 
				= service.hr_department_list(); 
		List<JobVO> jobs = service.hr_job_list(); 
		model.addAttribute("departments", departments);
		model.addAttribute("jobs", jobs);
		
		//선택한 사원정보를  DB에서 조회해온다
		EmployeeVO vo = service.employee_info(id);
		//수정화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		return "hr/modify";
	}
	
	//선택한 사원정보삭제처리 요청
	@RequestMapping("/delete.hr")
	public String delete(int id) {
		//선택한 사원정보를  DB에서 삭제한다
		service.employee_delete(id);
		//응답화면연결 - 사원목록화면
		return "redirect:list.hr";
	}
	
	
	//선택한 사원정보화면 요청
	@RequestMapping("/info.hr")
	public String info(int id, Model model) {
		//선택한 사번의 사원정보를 DB에서 조회해온다
		EmployeeVO vo = service.employee_info(id);
		
		//조회한 정보를 화면에서 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		return "hr/info";
	}
	
	
	
	//사원목록화면 요청
	@RequestMapping("/list.hr")
	public String list(HttpSession session, Model model
			, @RequestParam(defaultValue = "-1") int department_id) {
		
		session.setAttribute("category", "hr");
		//사원목록, 사원이소속된 부서목록을 DB에서 조회해와
//		List<EmployeeVO> list;
//		if( department_id==-1 )
//			list = service.employee_list();
//		else
//			list = service.employee_list(department_id);
		List<EmployeeVO> list = service.employee_list(department_id);
		//화면에 출력할 수 있도록 Model에 attribute로 담는다
		model.addAttribute("list", list);
		List<DepartmentVO> departments 
				= service.employee_department_list();
		model.addAttribute("departments", departments);
		model.addAttribute("department_id", department_id);
		//응답화면연결
		return "hr/list";
	}
}
