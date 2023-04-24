package question;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionVO {
	private int id, readcnt, no, filecnt;
	private String title, content, writer, name;
	private Date writedate;
	private List<QuestionFileVO> fileInfo;
}
