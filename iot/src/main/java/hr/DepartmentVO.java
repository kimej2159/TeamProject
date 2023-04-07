package hr;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DepartmentVO {
	private int department_id, location_id, manager_id;
	private String department_name, city, manager_name;
}
