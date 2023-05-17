package notice;

import java.sql.Date;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
	private int id, readcnt, no, step, indent, root, filecnt;
	private String title, content, writer, name, filename, filepath;
	private Date writedate;
	private List<NoticeFileVO> fileInfo; 
}


