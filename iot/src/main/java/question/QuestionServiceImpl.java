package question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired private QuestionDAO dao;
	
	@Override
	public int question_insert(QuestionVO vo) {
		return dao.question_insert(vo);
	}

	@Override
	public QuestionPageVO question_list(QuestionPageVO vo) {
		// TODO Auto-generated method stub
		return dao.question_list(vo);
	}

	@Override
	public QuestionVO question_info(int id) {
		return dao.question_info(id);
	}

	@Override
	public int question_read(int id) {
		// TODO Auto-generated method stub
		return dao.question_read(id);
	}

	@Override
	public int question_update(QuestionVO vo) {
		// TODO Auto-generated method stub
		return dao.question_update(vo);
	}

	@Override
	public int question_delete(int id) {
		// TODO Auto-generated method stub
		return dao.question_delete(id);
	}

	@Override
	public List<QuestionCommentVO> question_comment_list(int question_id) {
		return dao.question_comment_list(question_id);
	}

	@Override
	public int question_comment_regist(QuestionCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.question_comment_regist(vo);
	}

	@Override
	public int question_comment_update(QuestionCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.question_comment_update(vo);
	}


	@Override
	public int question_comment_delete(int id) {
		// TODO Auto-generated method stub
		return dao.question_comment_delete(id);

	}

	
}
