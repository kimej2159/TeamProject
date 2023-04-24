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
	public int customer_insert(CustomerVO vo) {
		return sql.insert("cu.insert", vo);
	}

	@Override
	public List<CustomerVO> customer_list() {
		return sql.selectList("cu.list");
	}

	@Override
	public CustomerVO customer_info(int id) {
		return sql.selectOne("cu.info", id);
	}

	@Override
	public int customer_update(CustomerVO vo) {
		return sql.update("cu.update", vo);
	}

	@Override
	public int customer_delete(int id) {
		return sql.delete("cu.delete", id);
	}

}
