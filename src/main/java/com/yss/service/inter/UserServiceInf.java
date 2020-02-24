package com.yss.service.inter;

import com.alibaba.fastjson.JSONObject;
import com.yss.pojo.User;

public interface UserServiceInf {
	
	
	public User getUserNameById(String id);
	
	public void insertUser(User user);
	
	public void insertTest(JSONObject json,String busId);
	
	

}
