package com.yss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)   
  //这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = App.class)  
  
public class ActivitiDemoTest {
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
    //初始化23张表
	//@Test
	public void Test001(){
		
		ProcessEngineConfiguration   processEngineConfiguration=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://49.235.89.144:3306/yss_act");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123456");
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//
		ProcessEngine processEngine	=processEngineConfiguration.buildProcessEngine();
		processEngine.getRepositoryService();//管理流程定义
		processEngine.getHistoryService();//历史任务
		processEngine.getIdentityService();//组织机构管理
		RuntimeService runtimeService =processEngine.getRuntimeService();//运行中的流程包括；启动、推进、删除
		processEngine.getFormService();//任务表单管理
		//List<Task>  list= processEngine.getTaskService().createTaskQuery().taskAssignee("张三").list();//任务管理
		
	
	}
	
	
	
	
	//@Test
	public void  startAct(){
		//runtimeService.startProcessInstanceByKey(processDefinitionKey)
     	ProcessInstance pi = runtimeService.startProcessInstanceByKey("leaveProcess003");//流程图id，业务表id
		System.out.println("流程启动成功，流程id:"+pi.getId());//7501
	}
	
	@Test
	 public void findPersonTask(){
		 List<Task>  list= taskService.createTaskQuery().taskAssignee("李四").list();//任务管理
		 for (Task task : list) {
				System.out.println("流程id:"+task.getId());//7505
				System.out.println("创建时间:"+task.getCreateTime());
				System.out.println("执行人:"+task.getAssignee());
				System.out.println("流程实力id:"+task.getProcessInstanceId());
				Map<String,Object> map=task.getTaskLocalVariables();
				Map<String,Object> map2=task.getProcessVariables();
				System.out.println(map.toString()+map2.toString());
			} 
	 }
 
	 //@Test
	 public void completeTask(){
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("remark", "我同意了");
		 map.put("status", "yes");
		 taskService.complete("12509", map);
		 
	 }
	 

}
