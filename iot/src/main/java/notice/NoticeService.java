package notice;

import java.util.List;

public interface NoticeService {
	//CRUD
	int notice_insert(NoticeVO vo); //신규 공지글 저장
	int notice_reply_insert(NoticeVO vo); //답글 저장
	List<NoticeVO> notice_list(); //공지글목록 조회
	NoticePageVO notice_list(NoticePageVO page); //해당페이지 공지글목록 조회
	NoticeVO notice_info(int id); //선택한 공지글정보 조회
	int notice_update(NoticeVO vo); //공지글정보 변경
	int notice_read(int id); //선택한 공지글 조회수 변경
	int notice_delete(int id); //선택한 공지글삭제
}
