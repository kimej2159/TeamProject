package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDAO dao;
	
	@Override
	public int notice_insert(NoticeVO vo) {
		return dao.notice_insert(vo);
	}

	@Override
	public int notice_reply_insert(NoticeVO vo) {
		return 0;
	}

	@Override
	public List<NoticeVO> notice_list() {
		return dao.notice_list();
	}

	@Override
	public NoticeVO notice_info(int id) {
		return dao.notice_info(id);
	}

	@Override
	public int notice_update(NoticeVO vo) {
		return dao.notice_update(vo);
	}

	@Override
	public int notice_read(int id) {
		return dao.notice_read(id);
	}

	@Override
	public int notice_delete(int id) {
		return dao.notice_delete(id);
	}

	public int notice_file_delete(String removed) {
		// TODO Auto-generated method stub
		return dao.notice_file_delete(removed);
	}

	@Override
	public NoticeFileVO notice_file_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeFileVO> notice_removed_file(String removed) {
		// TODO Auto-generated method stub
		return dao.notice_removed_file(removed);
	}

	@Override
	public NoticePageVO notice_list(NoticePageVO page) {
		// TODO Auto-generated method stub
		return dao.notice_list(page);
	}

	


}
