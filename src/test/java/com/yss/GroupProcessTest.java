package com.yss;

import java.util.List;

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
public class GroupProcessTest {

	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	HistoryService historyService;
	
	@Test
	public void  start(){
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("groupProcess");//流程图id，业务表id
		System.out.println("流程启动成功，流程实例id:"+pi.getId());//
		System.out.println("流程启动成功，流程定义id:"+pi.getProcessDefinitionId());//
		System.out.println("流程启动成功，id:"+pi.getProcessInstanceId());// 
		
	}
	/**
	 * 查询组任务
	 */
	@Test
	public void  findGroupTask(){
		String candidateUser="小B";
		List<Task> list=taskService.createTaskQuery()
				 .taskCandidateUser(candidateUser)//查询组任务
				 .orderByTaskCreateTime().asc()//升序
				 .list();
		for (Task task : list) {
			System.out.println("处理人是:"+task.getAssignee());
			System.out.println("任务id是："+task.getId());
			System.out.println("任务的名称："+task.getName());
			
		} 
	}
	
	/**
	 * 查询正在执行的任务的人
	 */
	@Test
	public void  findGroupTasking(){
		String taskId="77524";
		List<IdentityLink> list=taskService.getIdentityLinksForTask(taskId);
		for (IdentityLink identityLink : list) {
			System.out.println("正在执行的人："+identityLink.getUserId());
			System.out.println("正在执行的任务id："+identityLink.getTaskId());
			System.out.println("正在执行的流程实例id："+identityLink.getProcessInstanceId());
			System.out.println("######################"); 
		} 
	}
	/**
	 * 查询历史执行的任务的人
	 */
	@Test
	public void  findHistoryGroupTasking(){
		String taskId="77524";
		List<HistoricIdentityLink> list=
				historyService.getHistoricIdentityLinksForTask(taskId);
		for (HistoricIdentityLink identityLink : list) {
			System.out.println("正在执行的人："+identityLink.getUserId());
			System.out.println("正在执行的任务id："+identityLink.getTaskId());
			System.out.println("正在执行的流程实例id："+identityLink.getProcessInstanceId());
			System.out.println("######################"); 
		} 
	}
	/**
	 * 将组任务分配给个人任务，指定办理人字段。任务的拾取
	 */
	@Test
	public void claim(){ 
		String taskId="77524";
		String userId="小m";
		taskService.claim(taskId, userId); 
	}
	
	/**
	 * 将个人任务回退到组任务，前提拾任务拾组任务
	 */
	@Test
	public void setAssignee(){ 
		String taskId="77524";
		//String userId="小z";
		taskService.setAssignee(taskId, null);//把个人任务的userId设定为null
 	}
	/**
	 * 组任务添加成员
	 */
	@Test
	public void addGroup(){ 
		String taskId="77524";
		String userId="小w";
		taskService.addCandidateUser(taskId, userId);
  	}
	
	
	/**
	 * 组任务删除成员
	 */
	@Test
	public void delGroup(){ 
		String taskId="77524";
		String userId="小 A";
		taskService.deleteCandidateUser(taskId, userId);
 	}
	
	
	/**
	 * 完成任务
	 */
	@Test
	public void complete(){
		String taskId="77524";
		taskService.complete(taskId);
		
	}
	
}
