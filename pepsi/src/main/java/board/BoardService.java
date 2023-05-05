package board;

import java.util.List;

public interface BoardService {

	
	//CRUD(Create, Read, Update, Delete)
	int board_insert(BoardVO vo);			// 새글등록
	BoardPageVO board_list(BoardPageVO vo); // 목록조회
	BoardVO board_info(int id); 			// 선택 글 조회
	int board_read(int id); 				// 선택 글 조회수 변경
	int board_update(BoardVO vo); 			// 선택 글 수정저장
	int board_delete(int id); 				// 선택 글 삭제
	
	BoardFileVO board_file_info(int id);// 첨부파일 정보 조회
	List<BoardFileVO> board_removed_file(String removed);// 삭제하려는 첨부파일 정보 조회
	int board_file_delete(String removed);			// 변경첨부/삭제 한 파일 정보 삭제
	
	
	
	
	
	
}
