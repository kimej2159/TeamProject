package customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired private CustomerDAO dao;
	
	@Override
	public int customer_insert(CustomerVO vo) {
		return dao.customer_insert(vo);
	}

	@Override
	public List<CustomerVO> customer_list() {
		return dao.customer_list();
	}

	@Override
	public CustomerVO customer_info(int id) {
		return dao.customer_info(id);
	}

	@Override
	public int customer_update(CustomerVO vo) {
		return dao.customer_update(vo);
	}

	@Override
	public int customer_delete(int id) {
		return dao.customer_delete(id);
	}

}
