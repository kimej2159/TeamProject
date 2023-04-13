package member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDAO dao;
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_join(vo);
	}

	@Override
	public MemberVO member_myinfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> member_list() {
		// TODO Auto-generated method stub
		return dao.member_list();
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.member_login(map);
	}

	@Override
	public String member_salt(String id) {
		// TODO Auto-generated method stub
		return dao.member_salt(id);
	}

	@Override
	public String member_id_email(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_id_email(vo);
	}

	@Override
	public boolean member_id_check(String id) {
		// TODO Auto-generated method stub
		return dao.member_id_check(id);
	}

	@Override
	public int member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_update(vo);
	}

	@Override
	public int member_change_pw(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.member_change_pw(vo);
	}

	@Override
	public int member_delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
