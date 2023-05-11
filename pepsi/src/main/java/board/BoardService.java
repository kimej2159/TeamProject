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
	
	int board_comment_regist(BoardCommentVO vo);// 댓글 신규 저장
	int board_comment_update(BoardCommentVO vo);// 댓글 변경 저장
	int board_comment_delete(int id);// 댓글 삭제 저장
	List<BoardCommentVO> board_comment_list(int board_id);// 댓글 목록 조회

	
	
}
