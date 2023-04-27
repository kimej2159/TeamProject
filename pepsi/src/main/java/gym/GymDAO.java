package gym;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import trainer.TrainerDTO;

@Service
public class GymDAO {
	
	@Autowired @Qualifier("pepsi") private SqlSession sql;
	
	public ArrayList<GymDTO> select_gymAllList() {
		
		List<GymDTO> list = new ArrayList<>();
	    list = sql.selectList("gym.gymAllList");
		return (ArrayList<GymDTO>) list;
			
	}

	public ArrayList<GymDTO> select_gymList(int gym_id) {
		List<GymDTO> list = sql.selectList("gym.gymList", gym_id);
		return (ArrayList<GymDTO>) list;
	}

	
}
