package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import member.MemberVO;

@Service
public class CommonUtility {
	
	//회원가입축하 이메일 보내기
	public void sendWelcome(MemberVO vo, String welcome) {
		HtmlEmail email =  new HtmlEmail();
		email.setDebug(true);
		email.setCharset("utf-8");
		
		email.setHostName("smtp.naver.com");
		//보내는 이인 관리자로 로그인: 아이디/비번 입력
		email.setAuthentication("kimej2159", "kej20181664!");		
		email.setSSLOnConnect(true); //로그인하기
		
		try {
			//보내는이
		email.setFrom("kimej2159@naver.com", "한울핏");
		email.addTo(vo.getEmail(), vo.getName()); //받는이:회원가입하는사람
		
		email.setSubject(vo.getName() + "님 회원가입을 축하합니다!!");
		StringBuffer msg = new StringBuffer();
		msg.append("<html>");
		msg.append("<body>");
		msg.append("<h3><a target='_blank' http://112.164.58.220:8850'>한울핏 홈페이지</a></h3>");
		msg.append("<div>한울핏과 함께 건강한 운동을!</div>");
		msg.append("<p>한울핏에 가입해주셔서 감사합니다</p>");
		msg.append("</body>");
		msg.append("</html>");
		email.setHtmlMsg(msg.toString());
		
		//축하파일 첨부하기
		EmailAttachment file = new EmailAttachment(); //첨부파일객체 생성
		file.setPath( welcome );
		email.attach(file);

//		file = new EmailAttachment(); //첨부파일객체 생성
//		file.setPath( welcome );
//		email.attach(file);
		
		email.send(); //메일보내기
		
		}catch(EmailException e) {
			
		}
	}
	
	
	
	
	//첨부파일 업로드 : 서버에 파일 저장
	public String fileUpload(MultipartFile file,  String category, HttpServletRequest request) {
		//해당 날짜의 폴더를 만들어 그 안에 업로드한 파일을 저장하도록
		//반환되는 물리적 위치 : D:\STUDY_SPRING\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\pepsi\resources
		
//		String path = request.getSession().getServletContext().getRealPath("resources");
//		String upload = "/upload/" + category + new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		String path = "d://app/" + request.getContextPath();
		String upload = "/upload/" + category + new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
				
		//D:\STUDY_SPRING\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\pepsi\resources/upload/profile/2023/04/24
		path += upload;
		
		//resources에 폴더가 없으면 생성, 만들어야 하는 하위 폴더가 년, 날, 일로 다수이므로 mkdirs() 사용
		File folder = new File( path );
		if( ! folder.exists() ) folder.mkdirs();
		
		//해당 폴더 안에 첨부한 파일 저장
		String uuid = UUID.randomUUID().toString() + "_" 
				+ file.getOriginalFilename();
		try {
			file.transferTo( new File(path, uuid) );
			
			}catch(Exception e) {
				}
		// http://localhost/iot/upload/profile/2023/02/28/adfa-dfa_abc.png
		return  appURL(request) + upload + "/" + uuid;
		}
	
	
	
	// 첨부파일 다운로드 : 클라이언트에 파일저장 - 클라이언트한테 쓰기작업을 하는거임
	public void fileDownload(String filename, String filepath, HttpServletRequest request, HttpServletResponse response) {
		
		// DB 에 저장된 경로와 다르게 저장이 되어야함
		filepath = filepath.replace(appURL(request), "d://app/" + request.getContextPath());
		
		// 실제 물리적 파일이 존재하면 클라이언트에 저장
		File file = new File(filepath);
		if( file.exists()) {
			
			String mime = request.getSession().getServletContext().getMimeType(filename);
			response.setContentType(mime);
			
			try { 
				
			filename = URLEncoder.encode(filename, "utf-8"); // 한글깨짐방지
			response.setHeader("content-disposition","attachment; filename=" + filename);
				
				ServletOutputStream out = response.getOutputStream();
				FileCopyUtils.copy( new FileInputStream(file), out );
				out.flush();
				
			} catch (Exception e) {
				
			}
		}
	}
	
	// 첨부된 파일 삭제
	public void file_delete(String filepath, HttpServletRequest request) {
		// DB 에 저장된 경로와 다르게 저장이 되어야함
		
		if ( filepath != null ) {
			filepath = filepath.replace(appURL(request), "d://app/" + request.getContextPath());
			File file = new File( filepath );
			if ( file.exists() ) file.delete();
		}
	}
	
	
	
	
	
	//실행되고 있는 app의 url
	public String appURL(HttpServletRequest request) {
		//  http://localhost:99/naverCallback
		//--> http://localhost:99/
		return request.getRequestURL().toString().replace( request.getServletPath(), "");
	}
	
	//API 요청
	public String requestAPI( String apiURL ) {
		try {	
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			  BufferedReader br;
			  System.out.print("responseCode="+responseCode);
			  if(responseCode==200) { // 정상 호출
			    br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			  } else {  // 에러 발생
			    br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			  }
			  String inputLine;
			  StringBuffer res = new StringBuffer();
			  while ((inputLine = br.readLine()) != null) {
			    res.append(inputLine);
			  }
			  br.close();
			  if(responseCode==200) {
				  System.out.println( res.toString() );
			  }
			  apiURL = res.toString();
		} catch (Exception e) {
		  System.out.println(e);
		}		
		return apiURL;
	}
	
	//API 요청 - 헤더정보추가
	public String requestAPI( String apiURL, String property ) {
		try {	
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode="+responseCode);
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode==200) {
				System.out.println( res.toString() );
			}
			apiURL = res.toString();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return apiURL;
	}
		
		
	
		//이메일로 임시비번 보내기
		public boolean sendPassword(MemberVO vo, String pw) {
			boolean send = true;
			
			HtmlEmail email = new HtmlEmail();
			email.setCharset("utf-8");
			email.setDebug(true);
			
			email.setHostName("smtp.naver.com"); //이메일보낼서버지정
			//이메일로 임시비번을 보내는 이는 사이트 관리자
//			email.setAuthentication("itstudydev", "Itstudy10102"); //관리자 아이디,비번 입력
			email.setAuthentication("kimej2159", "kej20181664!"); //관리자 아이디,비번 입력
			email.setSSLOnConnect(true);  //로그인버튼 누르기
			
			try {
//			email.setFrom("itstudydev@naver.com", "hanulfit 관리자"); //보내는이 지정
			email.setFrom("kimej2159@naver.com", "hanulfit 관리자"); //보내는이 지정
			email.addTo( vo.getEmail(), vo.getName()  ); //받는이 지정
			email.setSubject("hanulfit 로그인 임시 비밀번호 확인") ;//제목
			
			//내용
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>["+ vo.getName() +"]님 비밀번호가 발급되었습니다.</h3>");
			msg.append("<div><strong>"+ pw +"</strong></div>");
			msg.append("<div>임시 비밀번호로 로그인 후 비밀번호를 변경하세요.</div>");
			msg.append("</body>");
			msg.append("</html>");
			email.setHtmlMsg( msg.toString() );
			
			email.send(); //보내기버튼 클릭
			
			}catch(Exception e) {
				System.out.println(e.getMessage());
				send = false;
			}
			return send;
		}
		
	//솔트를 사용해 비밀번호를 암호화하기
		public String getEncrypt(String pw, String salt) {
			pw = pw + salt;
			
			try {
				MessageDigest md		//암호화방식: SHA-256
					= MessageDigest.getInstance("SHA-256");
				md.update( pw.getBytes() );
				byte[] bytes = md.digest();
				
				StringBuffer val = new StringBuffer();
				for(byte b : bytes) {
					val.append( String.format("%02x", b) );
				}
				pw = val.toString(); //암호화된 비번		
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return pw;
		}
	
	
	
	//암호화에 사용할 솔트 생성
	public String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[16];
				
		random.nextBytes(bytes);
		//16개의 바이트를 16진수로 변환
		StringBuffer salt = new StringBuffer();
		for(byte b : bytes) {
			salt.append( String.format("%02x", b) );
		}
		return salt.toString();
	}
}
