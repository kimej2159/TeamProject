package notice;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NoticeFileVO {
	private int id, notice_id;
	private String filename, filepath;
}
