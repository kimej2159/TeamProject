package gym;

public class GymDTO {
	int gym_id ,gym_price; 
	String gym_name , address , telephone_number , gym_picture , gym_facilities ,gym_service;
	
	public GymDTO() {
	}
	
	public GymDTO(int gym_id, int gym_price, String gym_name, String address, String telephone_number,
			String gym_picture, String gym_facilities, String gym_service) {
		super();
		this.gym_id = gym_id;
		this.gym_price = gym_price;
		this.gym_name = gym_name;
		this.address = address;
		this.telephone_number = telephone_number;
		this.gym_picture = gym_picture;
		this.gym_facilities = gym_facilities;
		this.gym_service = gym_service;
	}

	public int getGym_id() {
		return gym_id;
	}

	public void setGym_id(int gym_id) {
		this.gym_id = gym_id;
	}

	public int getGym_price() {
		return gym_price;
	}

	public void setGym_price(int gym_price) {
		this.gym_price = gym_price;
	}

	public String getGym_name() {
		return gym_name;
	}

	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone_number() {
		return telephone_number;
	}

	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}

	public String getGym_picture() {
		return gym_picture;
	}

	public void setGym_picture(String gym_picture) {
		this.gym_picture = gym_picture;
	}

	public String getGym_facilities() {
		return gym_facilities;
	}

	public void setGym_facilities(String gym_facilities) {
		this.gym_facilities = gym_facilities;
	}

	public String getGym_service() {
		return gym_service;
	}

	public void setGym_service(String gym_service) {
		this.gym_service = gym_service;
	}
	
	
	
	
}
