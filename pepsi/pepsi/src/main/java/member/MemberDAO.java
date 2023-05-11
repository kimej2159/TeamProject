package member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {
	@Autowired @Qualifier("pepsi") private SqlSession sql;
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.insert("member.join", vo);
	}

	@Override
	public MemberVO member_myinfo(String id) {
		
		return null;
	}

	@Override
	public List<MemberVO> member_list() {
		// TODO Auto-generated method stub
		return sql.selectList("member.list");
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.login", map);
	}

	@Override
	public String member_salt(String id) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.salt", id);
	}

	@Override
	public String member_id_email(MemberVO vo) {
		// TODO Auto-generated method stub
		if( (Integer)sql.selectOne("member.id_email", vo) == 1 )
			return sql.selectOne("member.name", vo);
		else
			return null;
	}

	@Override
	public boolean member_id_check(String id) {
		// TODO Auto-generated method stub
		return (Integer)sql.selectOne("member.id_check", id) == 1 ? true : false;
	}

	@Override
	public int member_update(MemberVO vo) {
		return sql.update("member.update", vo);
	}

	@Override
	public int member_change_pw(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.update("member.change_pw", vo);
	}

	@Override
	public int member_delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
