package customer;

import java.util.List;

public interface CustomerService {
	//CRUD(Create, Read, Update, Delete)
	int customer_insert(CustomerVO vo);		//신규고객등록
	List<CustomerVO> customer_list();		//고객목록조회
	CustomerVO customer_info(int id);		//선택한 고객정보 조회
	int customer_update(CustomerVO vo);		//선택한 고객변경 저장
	int customer_delete(int id); 			//선택한 고객정보 삭제
	
}
