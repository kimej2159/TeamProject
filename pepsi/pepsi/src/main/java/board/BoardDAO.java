package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements BoardService {


	@Autowired @Qualifier("pepsi") private SqlSession sql;

	
	@Override
	public int board_insert(BoardVO vo) {
		
		// 방명록 글을 저장한 후
			int dml = sql.insert("bo.insert", vo);
			
			// 해당 글에 첨부된 파일이 있으면 파일정보를 저장
			if ( dml > 0 && vo.getFileInfo()!=null )
				sql.insert("bo.fileInsert", vo);
			
			return dml;
	}

	@Override
	public BoardPageVO board_list(BoardPageVO vo) {
		vo.setTotalList( sql.selectOne("bo.totalCount",vo) );
		vo.setList( sql.selectList("bo.list",vo) ) ;
		return vo;
	}

	@Override
	public BoardVO board_info(int id) {
		BoardVO vo = sql.selectOne("bo.info", id);
		vo.setFileInfo( sql.selectList("bo.fileList", id) );
		
		return vo;
	}

	@Override
	public int board_read(int id) {

		return sql.update("bo.read", id);
	}

	@Override
	public int board_update(BoardVO vo) {
		if(vo.getFileInfo()!=null) // 파일추가가 있으면 저장하기
			sql.insert("bo.fileInsert", vo);
		return sql.update("bo.update", vo);
	}

	@Override
	public int board_delete(int id) {
		
		return sql.delete("bo.delete", id);
	}

	@Override
	public BoardFileVO board_file_info(int id) {
		
		return sql.selectOne("bo.fileInfo", id);
	}

	@Override
	public List<BoardFileVO> board_removed_file(String removed) {

		return sql.selectList("bo.fileRemoved", removed);
	}

	@Override
	public int board_file_delete(String removed) {

		return sql.delete("bo.fileDelete", removed);
	}

}
