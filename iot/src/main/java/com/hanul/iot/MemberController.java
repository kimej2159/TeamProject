package com.hanul.iot;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;
	private String KAKAO_ID = "J4ptT0CmSzrdrExGgqMO";
	private String NAVER_ID = "cwCXun6Ln6n8NekdCAS4";
	private String NAVER_SECRET = "AyD5q97_Gs";
	
	//회원가입화면 요청
	@RequestMapping("/member")
	public String member(HttpSession session) {
		session.setAttribute("category", "join");
		return "member/join";
	}
	
	//회원가입처리 요청
		@ResponseBody 
		@RequestMapping(value="/join", produces="text/html; charset=utf-8")
		public String join(MemberVO vo, MultipartFile file  
					, HttpServletRequest request) {
			//첨부된 파일이 있는 경우
			if( ! file.isEmpty() ) {
				vo.setProfile(
					common.fileUpload(file, "profile", request) );
			}
		
		//화면에서 입력한 회원정보로 DB에 회원가입처리
		//비번암호화: 암호화용salt -> salt를 사용해 입력비번을 암호화
		String salt = common.generateSalt();
		String pw = common.getEncrypt(vo.getPw(), salt);
		vo.setSalt(salt);
		vo.setPw( pw );
		StringBuffer msg = new StringBuffer("<script>");
		if( service.member_join(vo)==1 ) {
			//이메일로 회원가입축하 메시지 보내기
			String filename = request.getSession().getServletContext()
					.getRealPath("resources/js/회원가입축하.pdf");
				common.sendWelcome(vo, filename);
			
			
			msg.append("alert('회원가입을 축하합니다^^'); location='")
				.append( common.appURL(request)) .append("' ");
		}else {
			msg.append("alert('회원가입 실패ㅠㅠ'); history.go(-1)");
		}
		msg.append("</script>");
		return msg.toString();
	}
		
	
	//아이디 중복확인처리 요청
	@ResponseBody @RequestMapping("/id_check")
	public boolean id_check(String id) {
		//화면에서 입력한 아이디가 DB에 존재하는지 존재여부 확인
		return service.member_id_check(id);
	}
	
	
	//카카오로그인처리 요청
	@RequestMapping("/kakaoLogin")
	public String kakaoLogin(HttpServletRequest request) {
		//인가 코드 받기
		//https://kauth.kakao.com/oauth/authorize?response_type=code
		//&client_id=${REST_API_KEY}
		//&redirect_uri=${REDIRECT_URI}
		StringBuffer url 
		= new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(KAKAO_ID);
		url.append("&redirect_uri=")
			.append( common.appURL(request) )
			.append("/kakaoCallback");
		
		return "redirect:"+url.toString();
	}
	
	//카카오콜백처리
	@RequestMapping("/kakaoCallback")
	public String kakaoCallback(String code, HttpSession session){
		if( code==null ) return "redirect:/";
		//토큰 받기
//		curl -v -X POST "https://kauth.kakao.com/oauth/token" \
//		 -H "Content-Type: application/x-www-form-urlencoded" \
//		 -d "grant_type=authorization_code" \
//		 -d "client_id=${REST_API_KEY}" \
//		 --data-urlencode "redirect_uri=${REDIRECT_URI}" \
//		 -d "code=${AUTHORIZE_CODE}"
		StringBuffer url 
		= new StringBuffer("https://kauth.kakao.com/oauth/token?grant_type=authorization_code") ;
		url.append("&client_id=").append(KAKAO_ID);
		url.append("&code=").append(code);
		JSONObject json 
			= new JSONObject( common.requestAPI(url.toString()) );
		String type = json.getString("token_type");
		String token = json.getString("access_token");
		
		//카카오 프로필정보 받기
//		curl -v -X GET "https://kapi.kakao.com/v2/user/me" \
//		  -H "Authorization: Bearer ${ACCESS_TOKEN}"
		url = new StringBuffer("https://kapi.kakao.com/v2/user/me");
		json = new JSONObject( common.requestAPI(url.toString(), type + " " + token) );
		
		if( !json.isEmpty() ) {
			MemberVO vo = new MemberVO();
			vo.setId( json.get("id").toString() );
			vo.setSocial("K");
			
			json = json.getJSONObject("kakao_account");
			vo.setName( jsonValue("name", json) );
			vo.setEmail( jsonValue("email", json ) );
			vo.setPhone( jsonValue("phone_number", json) );
			vo.setGender( jsonValue("gender", json, "female").equals("female") ? "여" : "남"); //female/male
			
			json = json.getJSONObject("profile");
			vo.setProfile( jsonValue("profile_image_url", json) );
			
			if( vo.getName().isEmpty() ) {
				vo.setName( jsonValue("nickname", json, "...") );
			}
			
			if(  service.member_id_check(vo.getId()) ) {
				service.member_update(vo);				
			}else {
				service.member_join(vo);
			}
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/";
	}
	
	private String jsonValue(String key, JSONObject json) {
		return json.has(key) ? json.getString(key) : "";
	}
	private String jsonValue(String key, JSONObject json, String defaultValue) {
		return json.has(key) ? json.getString(key) : defaultValue;
	}
	
	
	//네이버로그인처리 요청
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpSession session, HttpServletRequest request) {
		//https://nid.naver.com/oauth2.0/authorize?response_type=code
		//&client_id=CLIENT_ID
		//&state=STATE_STRING
		//&redirect_uri=CALLBACK_URL
		String state = UUID.randomUUID().toString();
		session.setAttribute("state", state);
		
		//네이버 로그인 연동 URL 생성하기
		StringBuffer url 
			= new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(NAVER_ID);
		url.append("&state=").append(state);
		url.append("&redirect_uri=").append( common.appURL(request) ).append("/naverCallback");
		
		return "redirect:" + url.toString();
	}
	
	@RequestMapping("/naverCallback")
	public String naverCallback(String state, String code
								, HttpSession session) {
		//네이버 로그인 연동 결과 Callback 정보
		//콜백실패로 에러가 발생했거나
		//상태토큰값이 일치하지 않는 경우 토큰을 발급받을수 없다
		String val = (String)session.getAttribute("state") ;
		if( code==null || ! state.equals( val ) ) 
			return "redirect:/";
		
		//접근 토큰 발급 요청
		//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		//&client_id=jyvqXeaVOVmV
		//&client_secret=527300A0_COq1_XV33cf
		//&code=EIc5bFrl4RibFls1
		//&state=9kgsGTfH4j7IyAkg
		StringBuffer url 
		= new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");	
		url.append("&client_id=").append(NAVER_ID);
		url.append("&client_secret=").append(NAVER_SECRET);
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		
		String response = common.requestAPI( url.toString() );
		JSONObject json = new JSONObject(response);
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
		//접근 토큰을 이용하여 프로필 API 호출하기
		url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
		response = common.requestAPI(url.toString(), type+" "+token);
		json = new JSONObject(response);
		
		if( json.getString("resultcode").equals("00") ) {
			json = json.getJSONObject("response");
			
			MemberVO vo = new MemberVO();
			vo.setId( json.getString("id") );
			vo.setName( json.getString("name") );
			vo.setEmail( json.getString("email") );
			vo.setProfile( json.getString("profile_image") );
			//male/female
			vo.setGender( json.getString("gender").equals("F") ? "여" : "남" );
			vo.setPhone( json.getString("mobile") );
			if( vo.getName().isEmpty() ) {
				vo.setName( json.getString("nickname") );
			}
			vo.setSocial("N");
			
			//insert/update
			//네이버로그인이 처음이면 신규저장/ 아니면 변경저장
			//네이버아이디가 존재하는지 파악: 1:T(update), 0:F(insert)
			if( service.member_id_check( vo.getId() ) ) {
				service.member_update(vo);				
			}else {
				service.member_join(vo);
			}
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/";
	}
	
	
	
	//로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {

		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		String social = vo.getSocial();
		
		//세션에 저장한 로그인정보를 삭제
		session.removeAttribute("loginInfo");
		
		if( social != null && social.equals("K") ) {			
			
			//"https://kauth.kakao.com/oauth/logout?
			//client_id=${YOUR_REST_API_KEY}
			//&logout_redirect_uri=${YOUR_LOGOUT_REDIRECT_URI}"
			StringBuffer url 
			= new StringBuffer("https://kauth.kakao.com/oauth/logout?");
			url.append("client_id=").append(KAKAO_ID);
			url.append("&logout_redirect_uri=")
				.append( common.appURL(request) );
			return "redirect:" + url.toString();
			
		}else {		
			//응답화면연결
			return "redirect:/";
		}
	}
	
	@Autowired private CommonUtility common;
	
	//비밀번호 변경화면 요청
	@RequestMapping("/changepw")
	public String changepw(HttpSession session) {
		//로그인된 상태인 경우		
		//로그인하지 않은 상태인 경우 로그인 화면으로 연결
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		if( vo==null )	return "redirect:login";
		//응답화면연결 - 비밀번호변경화면
		else {
			session.setAttribute("category", "changepw");
			return "member/password";
		}
	}
	
	
	//비밀번호 재발급 처리 요청
	@ResponseBody
	@RequestMapping(value="/reset"
					, produces="text/html; charset=utf-8") 
	public String reset(MemberVO vo) {
		//화면에서 입력한 아이디,이메일이 일치하는 회원에게 
		//임시 비번을 발급해서 이메일로 보낸다
		StringBuffer msg ;
		//아이디와 이메일 일치여부를 확인하여 
		if( service.member_id_email(vo)==1 ) {
			
			//임시 비번으로 사용할 랜덤한 문자열 만들기
			String pw = UUID.randomUUID().toString(); //dahj2324-h24fajk-adfl35ajf
			pw = pw.substring( pw.lastIndexOf("-") + 1 );//adfl35ajf
			
			//암호화용 솔트생성
			String salt = common.generateSalt();
			//솔트를 사용해 임시비번을 암호화
			vo.setPw( common.getEncrypt(pw, salt) );
			vo.setSalt(salt);
		
			//임시 비번으로 해당 회원정보를 변경
			service.member_change_pw(vo);
			
			//임시 비번을 해당 이메일로 보내기
			boolean send = common.sendPassword(vo, pw);
			msg = new StringBuffer("<script>");
			if( send ) {
				msg.append("alert('임시 비밀번호가 발급되었습니다.\\n이메일을 확인하세요'); ")
				.append("location='login' ");
			}else{
				msg.append("alert('임시 비밀번호 이메일 전송 실패 ㅠㅠ');")
					.append("history.go(-1)");
			}
			msg.append("</script>");
			
		}else {
			msg = new StringBuffer("<script>");
			msg.append("alert('아이디나 이메일이 맞지 않습니다\\n확인하세요!' ); ");
			msg.append("history.go(-1)");
			msg.append("</script>");
		}
		
		return msg.toString();
	}
	
	
	//비밀번호찾기 화면 요청
	@RequestMapping("/findpw")
	public String findpw() {
		return "default/member/find";
	}
	
	//로그인처리 요청
	@ResponseBody @RequestMapping("/iotLogin")
	public boolean login(String id, String pw, HttpSession session) {
		//화면에서 입력한 아이디의 솔트를 조회해와
		//해당 솔트를 사용해서 입력한 비번을 암호화한다
		String salt = service.member_salt(id);
		pw = common.getEncrypt(pw, salt);
		
		//화면에서 입력한 아이디/비번이 일치하는 회원정보를 조회해온다
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);
		return vo==null ? false : true;
	}
	
	//비밀번호변경저장처리 요청
	@RequestMapping("/change")
	public String changepw(String pw, HttpSession session) {
		//화면에서 입력한 비번으로 DB에 변경
		//솔트를 사용해 입력한 비번을 암호화		
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		if(vo==null) return "redirect:login";
		
		pw = common.getEncrypt(pw, vo.getSalt()); //암호화된 비번
		vo.setPw( pw );
		
		service.member_change_pw(vo);
		
		session.setAttribute("loginInfo", vo);
		//응답화면연결 - 웰컴
		return "redirect:/";
	}
	
	
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "default/member/login";
	}
}
