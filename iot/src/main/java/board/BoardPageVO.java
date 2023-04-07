package board;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardPageVO extends PageVO {
	private List<BoardVO> list;
}
