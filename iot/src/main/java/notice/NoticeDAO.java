package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int notice_insert(NoticeVO vo) {
		return sql.insert("no.insert", vo);
	}

	@Override
	public List<NoticeVO> notice_list() {
		return sql.selectList("no.list");
	}

	@Override
	public NoticeVO notice_info(int id) {
		return sql.selectOne("no.info", id);
	}

	@Override
	public int notice_update(NoticeVO vo) {
		return sql.update("no.update", vo);
	}

	@Override
	public int notice_delete(int id) {
		return sql.delete("no.delete", id);
	}

	@Override
	public int notice_read(int id) {
		return sql.update("no.read", id);
	}

	@Override
	public NoticePageVO notice_list(NoticePageVO page) {
		//총 글건수를 조회하면 페이지관련정보가 계산된다
		page.setTotalList( sql.selectOne("no.totalCount", page) );
		//해당 페이지의 글 목록 조회
		page.setList( sql.selectList("no.list", page) );
		return page;
	}

	@Override
	public int notice_reply_insert(NoticeVO vo) {
		return sql.insert("no.replyInsert", vo);
	}

}
