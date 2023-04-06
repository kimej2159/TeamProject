package customer;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements CustomerService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int customer_new(CustomerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerVO> customer_list() {
		// TODO Auto-generated method stub
		return sql.selectList("cu.list");
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
