package com.yss;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
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

@SpringBootTest(classes = App.class)  
public class ClassProcessTest {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Test
	public void start(){
		
 			//runtimeService.startProcessInstanceByKey(processDefinitionKey)
	     	ProcessInstance pi = runtimeService.startProcessInstanceByKey("classProcess");//流程图id，业务表id
			System.out.println("流程启动成功，流程实例id:"+pi.getId());//7501
			System.out.println("流程启动成功，流程定义id:"+pi.getProcessDefinitionId());//7501
			System.out.println("流程启动成功，id:"+pi.getProcessInstanceId());//7501
			//pi.getProcessInstanceId()
		
	 
		
	}
	
	
	
	
	@Test
	public void  findMyTask(){
		String assignee="李四";
		List<Task> list=taskService.createTaskQuery()
				//.taskAssignee(assignee)
				.list();
		for (Task task : list) {
			System.out.println("处理人是:"+task.getAssignee());
			System.out.println("任务id是："+task.getId());
			System.out.println("任务的名称："+task.getName());
			
		}
		
		
	}
	
	@Test
	public void completeTask(){
		taskService.complete("42518");  
	}
	

}
