package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import board.BoardVO;

@Repository
public class NoticeDAO implements NoticeService {
	@Autowired @Qualifier("pepsi") private SqlSession sql;
	
	@Override
	public int notice_insert(NoticeVO vo) {
		
		//방명록 글을 저장한 후 
		int dml = sql.insert("no.insert", vo);
		
		//해당 글에 첨부된 파일이 있으면 파일 정보를 저장
		if (dml > 0 && vo.getFileInfo()!=null )
			sql.insert("no.fileInsert", vo);
		
		return dml;
	}

	@Override
	public int notice_reply_insert(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeVO> notice_list() {
		// TODO Auto-generated method stub
		return sql.selectList("no.list");
	}

	@Override
	public NoticeVO notice_info(int id) {
		// TODO Auto-generated method stub
		NoticeVO vo = sql.selectOne("no.info", id);
		vo.setFileInfo( sql.selectList("no.fileList", id) );
		
		return vo;
	}

	@Override
	public int notice_update(NoticeVO vo) {
		if( vo.getFileInfo()!=null )
			sql.insert("no.fileInsert", vo);
		return sql.update("no.update", vo);
	}

	@Override
	public int notice_read(int id) {
		return sql.update("no.read", id);
	}

	@Override
	public int notice_delete(int id) {
		// TODO Auto-generated method stub
		return sql.delete("no.delete", id);
	}

	@Override
	public NoticeFileVO notice_file_info(int id) {
		
		return sql.selectOne("no.fileInfo", id);
	}

	@Override
	public List<NoticeFileVO> notice_removed_file(String removed) {
		
		return sql.selectList("no.fileRemoved", removed);
	}

	@Override
	public int notice_file_delete(String removed) {
		
		return sql.delete("no.fileDelete", removed);
	}

	@Override
	public NoticePageVO notice_list(NoticePageVO page) {
		// 총 글의 건 수를 조회하면 페이지 관련 정보가 계산 된다
		page.setTotalList( sql.selectOne("no.totalCount", page));
		
		//해당 페이지의 글 목록 조회
		page.setList( sql.selectList("no.list", page) );
		
		return page;
	}

	

}
