package trainer;

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

import gym.GymDTO;

@Service
public class TrainerDAO {
	
	@Autowired @Qualifier("pepsi") private SqlSession sql;
	
	

	public ArrayList<TrainerDTO> select_trainerAllList() {
		List<TrainerDTO> list = new ArrayList<>();
	    list = sql.selectList("trainer.trainerAllList");
		
		
		return (ArrayList<TrainerDTO>) list;
		
	}
	
	public ArrayList<TrainerDTO> select_trainer(String trainer_name) {
		List<TrainerDTO> list = new ArrayList<>();
		list = sql.selectList("trainer.trainerDetail" , trainer_name);
		
		return (ArrayList<TrainerDTO>) list;
		
	}
	
	
}
