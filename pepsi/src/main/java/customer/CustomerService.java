package customer;

import java.util.List;

public interface CustomerService {
	//CRUD
	int customer_new(CustomerVO vo);	//신규 고객 등록
	List<CustomerVO> customer_list();	//고객 목록 조회
	CustomerVO customer_info(int id);	//선택한 고객 정보 조회
	int customer_update(CustomerVO vo);	//선택한 고객 변경 저장
	int customer_delete(int id);		//선택한 고객 정보 삭제


}
