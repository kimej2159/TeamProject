package board;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int board_read(int id) {
		// TODO Auto-generated method stub
		return 0;
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

}
