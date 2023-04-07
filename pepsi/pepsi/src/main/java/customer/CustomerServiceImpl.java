package customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired private CustomerDAO dao;
	
	@Override
	public int customer_new(CustomerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerVO> customer_list() {
		// TODO Auto-generated method stub
		return dao.customer_list();
	}

	@Override
	public CustomerVO customer_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int customer_update(CustomerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int customer_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
