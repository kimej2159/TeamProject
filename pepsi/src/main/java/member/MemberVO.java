package member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {
	private String id, pw, address, profile, name, salt, email, phone
					, gender, social, admin, birth, post;
}
