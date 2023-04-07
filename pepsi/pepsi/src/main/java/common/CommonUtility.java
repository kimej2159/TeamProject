package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class CommonUtility {
	
		//이메일로 임시비번 보내기
		public boolean sendPassword(MemberVO vo, String pw) {
			boolean send = true;
			
			HtmlEmail email = new HtmlEmail();
			email.setCharset("utf-8");
			email.setDebug(true);
			
			email.setHostName("smtp.naver.com"); //이메일보낼서버지정
			//이메일로 임시비번을 보내는 이는 사이트 관리자
			email.setAuthentication("kimej2159", "kej20181664"); //관리자 아이디,비번 입력
			email.setSSLOnConnect(true);  //로그인버튼 누르기
			
			try {
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
