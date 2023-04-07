package board;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BoardFileVO {
	private int id, board_id;
	private String filename, filepath;
}
