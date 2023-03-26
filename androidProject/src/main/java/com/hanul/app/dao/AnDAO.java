package com.hanul.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.app.dto.GymDTO;
import com.hanul.app.dto.MemberDTO;

public class AnDAO {
	DataSource dataSource;

	public AnDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/teamAll");
			/*dataSource = (DataSource) context.lookup("java:/comp/env/CSS");*/
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	//7. 회원가입 : command에서 넘어온 값을 받는다
	public int anJoin(MemberDTO dto) {
		
		// 데이터베이스와 연결하여 원하는 결과물을 얻는다
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into member(id, pw, name, phone, email) " + 
			               "values('" + dto.getId() + "', '" + dto.getPw() + "', '" + dto.getName() + "', '" + 
					        			dto.getPhone() + "', '" + dto.getEmail() + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println("에러 :"+e.getMessage());
		} finally {
			try {				
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		//8. 원하는 값을 리턴시킨다
		return state;
		
	}
	
	public MemberDTO anLogin(String idin, String pwin) {

    	MemberDTO adto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select id , name ,email , phone"					
							+ " from member" 
							+ " where id = '" + idin + "' and pw = '" + pwin + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email"); 
				String phone = resultSet.getString("phone");

				adto = new MemberDTO(id, name, email, phone );							
			}	
			
			System.out.println("MemberDTO id : " + adto.getId());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		return adto;

	}
	
		public ArrayList<GymDTO> selectMembers() {
		
		ArrayList<GymDTO> dtos = new ArrayList<GymDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from gym"	;	
							
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int gym_id = resultSet.getInt("gym_id");
				String gym_name = resultSet.getString("gym_name");
				String address = resultSet.getString("address");
				String telephone_number = resultSet.getString("telephone_number"); 
				String gym_picture = resultSet.getString("gym_picture"); 

				dtos.add(new GymDTO(gym_id, gym_name, address, telephone_number, gym_picture));							
			}	
			
			System.out.println("dtos size : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		return dtos;
		
	}


	

	

	
}
