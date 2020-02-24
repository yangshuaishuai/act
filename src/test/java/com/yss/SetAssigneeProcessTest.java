package com.yss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yss.App;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringBootTest(classes = App.class)  
public class SetAssigneeProcessTest {

	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	HistoryService historyService;
	/**
	 * 使用表达式指定办理人
	 */
	@Test
	public void  start(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", "张三");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("setAssigneeProcess",map);//流程图id，业务表id
		System.out.println("流程启动成功，流程实例id:"+pi.getId());//
		System.out.println("流程启动成功，流程定义id:"+pi.getProcessDefinitionId());//
		System.out.println("流程启动成功，id:"+pi.getProcessInstanceId());// 
		
	}
	/**
	 * 查询个人任务
	 */
	@Test
	public void  findMyTask(){
		String assignee="张三";
		List<Task> list=taskService.createTaskQuery()
				.taskAssignee(assignee)
				.list();
		for (Task task : list) {
			System.out.println("处理人是:"+task.getAssignee());
			System.out.println("任务id是："+task.getId());
			System.out.println("任务的名称："+task.getName());
			
		}
		
		
	}
	
	
	/**
	 * 完成任务
	 */
	@Test
	public void complete(){
		String taskId="95028";
		taskService.complete(taskId);
		
	}
	
}
