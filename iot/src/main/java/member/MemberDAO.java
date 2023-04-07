package member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int member_join(MemberVO vo) {
		return sql.insert("member.join", vo);
	}

	@Override
	public MemberVO member_myinfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> member_list() {
		return sql.selectList("member.list");
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.login", map);
	}

	@Override
	public int member_update(MemberVO vo) {
		return sql.update("member.update", vo);
	}

	@Override
	public int member_delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_change_pw(MemberVO vo) {
		return sql.update("member.change_pw", vo);
	}

	@Override
	public String member_salt(String id) {
		return sql.selectOne("member.salt", id);
	}

	@Override
	public int member_id_email(MemberVO vo) {
		return sql.selectOne("member.id_email", vo);
	}

	@Override
	public boolean member_id_check(String id) {
		return (Integer)sql.selectOne("member.id_check", id) == 1 
					? true : false;
	}

}
