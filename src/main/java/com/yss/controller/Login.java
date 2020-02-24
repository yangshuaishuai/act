package com.yss.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yss.pojo.User;
import com.yss.service.UserService;

@Controller
public class Login {

	 

	@Autowired
	private UserService userService;
/*	
	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService;  

	@Autowired  
	private RepositoryService repositoryService; 
	*/
     
	@ResponseBody
	@RequestMapping("/getUserNameById")
	public User getUserNameById() {

		return userService.getUserNameById("23");
	}
	
	@RequestMapping(value = {"/index", "/view"})
	public String index(Map<String, Object> map)
	{  
		map.put("name", "SpringBoot");
		map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return "index";
	}
	
	
	@ResponseBody
	@RequestMapping("/insertUser")
	public void insertUser() {
		User user=new User();
		user.setAddress("南京");
		user.setAge("18");
		user.setId("1");
		user.setName("yang");
		userService.insertUser(user);
	}
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
