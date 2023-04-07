package hr;

import java.util.List;

public interface HrService {
	//CRUD
	void employee_insert(EmployeeVO vo); //신규사원정보등록
	List<EmployeeVO> employee_list(); //사원목록조회
	List<EmployeeVO> employee_list(int department_id); //특정부서에 속한 사원목록조회
	EmployeeVO employee_info(int employee_id); //선택한 사원정보 조회
	void employee_update(EmployeeVO vo); //선택한 사원정보변경
	void employee_delete(int employee_id);//선택한 사원정보삭제
	
	List<DepartmentVO> employee_department_list();//부서목록조회(사원이소속된)
	List<DepartmentVO> hr_department_list();//부서목록조회(회사의전체)
	List<JobVO> hr_job_list();//업무목록조회(회사의전체)
	List<EmployeeVO> hr_manager_list();//관리자목록조회(회사의전체)
}
