package question;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionCommentVO {

	private int id, question_id;
	private String content, writer, name, writedate;
}
