package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired private BoardDAO dao;
	
	@Override
	public int board_insert(BoardVO vo) {
		
		return dao.board_insert(vo);
	}

	@Override
	public BoardPageVO board_list(BoardPageVO vo) {
		
		return dao.board_list(vo);
	}

	@Override
	public BoardVO board_info(int id) {
		
		return dao.board_info(id);
	}

	@Override
	public int board_read(int id) {
		
		return dao.board_read(id);
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardFileVO board_file_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardFileVO> board_removed_file(String removed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int board_file_delete(String removed) {
		// TODO Auto-generated method stub
		return 0;
	}

}
