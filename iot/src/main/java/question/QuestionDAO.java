package question;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO implements QuestionService {
	@Autowired @Qualifier("hanul")  private SqlSession sql;
	
	@Override
	public int question_insert(QuestionVO vo) {
		//방명록 글을 저장한 후 
		int dml = sql.insert("qu.insert", vo);
	
		return dml;
	}

	@Override
	public QuestionPageVO question_list(QuestionPageVO vo) {
		vo.setTotalList( sql.selectOne("qu.totalcount"));
		vo.setList( sql.selectList("qu.list", vo) );
		return vo;
	}

	@Override
	public QuestionVO question_info(int id) {
		QuestionVO vo = sql.selectOne("qu.info", id);
		
		return vo;
	}

	@Override
	public int question_read(int id) {
		// TODO Auto-generated method stub
		return sql.update("qu.read", id);
	}

	@Override
	public int question_update(QuestionVO vo) {
		// TODO Auto-generated method stub
		return sql.update("qu.update", vo);
	}

	@Override
	public int question_delete(int id) {
		// TODO Auto-generated method stub
		return sql.delete("qu.delete", id);
	}

	@Override
	public int question_comment_regist(QuestionCommentVO vo) {
		// TODO Auto-generated method stub
		return sql.insert("qu.commentInsert", vo);
	}

	@Override
	public int question_comment_update(QuestionCommentVO vo) {
		// TODO Auto-generated method stub
		return sql.update("qu.commentUpdate", vo);
	}

	@Override
	public List<QuestionCommentVO> question_comment_list(int question_id) {
		// TODO Auto-generated method stub
		return sql.selectList("qu.commentList", question_id);
	}

	@Override
	public int question_comment_delete(int id) {
		return sql.delete("qu.commentDelete", id);
	}

}
