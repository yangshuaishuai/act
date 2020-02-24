package com.yss.dao;
 import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yss.pojo.LeaveInfo;

@Repository
public class LeaveDao {

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    //@Insert("<script>insert into leave_info(message,status) values(#{leaveInfo.message},#{leaveInfo.status})</script>")
   // @Options(useGeneratedKeys = true, keyProperty = "leaveInfo.id")
    public void insert(LeaveInfo leaveInfo){
    	
    	String sql="insert into leave_info(id,message,status)"
 				+ "values( '"+leaveInfo.getId()+"', '"+leaveInfo.getMessage()+"','"+leaveInfo.getStatus()+"')";
    	System.out.println(sql);
    	 jdbcTemplate.update(sql);
    	
    }

    
    public void update(LeaveInfo leaveInfo){
    	 jdbcTemplate.update("update leave_info set status='"+leaveInfo.getStatus()+"'    where id='"+leaveInfo.getId()+"'");
    	
    	
    }

   // @Select("select * from leave_info where id=#{id}")
    public LeaveInfo selectById(String id){
    	
    	return jdbcTemplate.queryForObject("select id,message,status from leave_info where id='" +id+"'",new RowMapper<LeaveInfo>(){

			@Override
			public LeaveInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				LeaveInfo res=new LeaveInfo();
				res.setId(rs.getString("id"));
				res.setMessage(rs.getString("message"));
				res.setStatus(rs.getString("status"));
				return res;
			}
    		
    	});
    	//return  null;
    	//return jdbcTemplate.query("select * from leave_info where id=" +id, LeaveInfo.class);
    	
    	
    }

}
