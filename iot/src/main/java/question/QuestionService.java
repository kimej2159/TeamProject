package question;

import java.util.List;

public interface QuestionService {
	//CURD
	int question_insert(QuestionVO vo);	//질문글 새로 저장
	QuestionPageVO question_list(QuestionPageVO vo);//질문글 목록 조회
	QuestionVO question_info(int id);//선택한 질문글 조회
	int question_read(int id);//선택한 질문글 조회수 변경
	int question_update(QuestionVO vo);//선택한 방명록 정보 수정 저장 
	int question_delete(int id);//선택한 질문글 정보 삭제
	
	
	int question_comment_regist(QuestionCommentVO vo);//댓글 신규 저장 
	int question_comment_update(QuestionCommentVO vo);//댓글 수정 처리
	int question_comment_delete(int id);//댓글 삭제 처리
	List<QuestionCommentVO> question_comment_list(int question_id);
	//댓글 목록 조회

}
