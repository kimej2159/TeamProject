package customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerVO {
	private int id;
	private String pw, name, email, phone;
}
