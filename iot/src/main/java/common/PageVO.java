package common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {
	private String search, keyword, viewType="list"; //검색 조건, 검색어
	
	private int pageList = 10;		//페이지당 보여질 목록 수
	private int blockPage = 10;		//블럭당 보여질 페이지의 수
	private int totalList;			//총 목록의 갯수
	private int totalPage;			//총 페이지 수
	private int totalBlock;			//총 블럭의 수
	private int curPage = 1;		//현재 시작 번호
	private int endList, beginList;	//각 페이지의 끝 목록 번호
									//각 페이지의 시작 목록 번호
	private int curBlock;			//각 블럭에 보여질 끝 페이지 번호 
	private int endPage, beginPage;	//시작 페이지 번호
	
	
	//메소드
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		//총 페이지 수 
		totalPage = totalList / pageList;
		if( totalList % pageList > 0 ) ++totalPage;
		//총 블록 수 
		totalBlock = totalPage / blockPage;
		if( totalPage % blockPage > 0) ++totalBlock;
		//각 페이지의 끝 목록 번호, 시작 목록 번호
		endList = totalList - (curPage-1) * pageList;
		beginList = endList - (pageList-1);
		
		//현재 블록 번호
		curBlock = curPage / blockPage;
		if( curPage % blockPage > 0) ++curBlock;
		
		//각 블럭의 끝 페이지 번호
		//각 블럭의 시작 페이지 번호
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
		
		if( totalPage < endPage ) endPage = totalPage;
		
	}
	
}
