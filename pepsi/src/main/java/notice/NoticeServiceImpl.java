package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDAO dao;
	
	@Override
	public int notice_insert(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int notice_reply_insert(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeVO> notice_list() {
		// TODO Auto-generated method stub
		return dao.notice_list();
	}

	@Override
	public NoticeVO notice_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int notice_update(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int notice_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int notice_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
