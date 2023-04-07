package question;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionPageVO extends PageVO {

	private List<QuestionVO> list;
}
