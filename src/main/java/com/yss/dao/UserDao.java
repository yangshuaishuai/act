package com.yss.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yss.pojo.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Object getUserNameById(String id) {
  
		List rows=jdbcTemplate.queryForList("select * from USER_TEST  ");
		for(int i=0;i<rows.size();i++){                
			Map userMap=(Map) rows.get(i);
			System.out.println(userMap.get("id"));  
			System.out.println(userMap.get("name"));  
			System.out.println(userMap.get("age"));
			}
		return jdbcTemplate.queryForObject("", User.class);

	}
	
	public void insertUser(User user)
	{
		 jdbcTemplate.update("insert into USER_TEST(NAME,AGE,ADDRESS)"
				+ "values(3,4,5)");
		 
	}
	
	
	public void insertTest(String sql)
	{
		 jdbcTemplate.update(sql);
		 
	}

}
