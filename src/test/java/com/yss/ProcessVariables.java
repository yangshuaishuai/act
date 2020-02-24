package com.yss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yss.pojo.LeaveInfo;

@RunWith(SpringJUnit4ClassRunner.class)   

@SpringBootTest(classes = App.class)  
public class ProcessVariables {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService  historyService;
	
	@Test
	public void start(){
		
 			//runtimeService.startProcessInstanceByKey(processDefinitionKey)
	     	ProcessInstance pi = runtimeService.startProcessInstanceByKey("processVariables");//流程图id，业务表id
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
	public void setVariable(){
		
		//runtimeService.setVariable(paramString1, paramString2, paramObject);
		String taskId="47521";
		taskService.setVariableLocal(taskId, "请假天数", 5);
		taskService.setVariable(taskId, "请假时间",new Date());
		taskService.setVariable(taskId, "请假原因","鸡下蛋了！");
		//taskService.setVariableLocal(taskId, variableName, value); 只会在当前节点有效 不是全局的
		//taskService.(taskId, variableName, value);  
	}
	@Test 
	public void getVariable(){
		
		//runtimeService.setVariable(paramString1, paramString2, paramObject);
		String taskId="55002";
	   Map<String,Object> res= taskService.getVariables(taskId);
	   Set<String> set=res.keySet();
	   for (String key : set) {
		   System.out.println("key:"+key+",value:"+res.get(key));
	   }
		
		//taskService.(taskId, variableName, value);  
	}
	
	
	
	
	@Test 
	public void setMapVariable(){
		
		//runtimeService.setVariable(paramString1, paramString2, paramObject);
		String taskId="47521";
		Map<String,Object> variables=new HashMap<String,Object>(); 
		variables.put("请假天数", 10);
		variables.put("请假时间", new Date());
		variables.put("请假原因", "鸡下蛋了！");
		taskService.setVariables(taskId, variables);//本方法支持javabean
		//taskService.(taskId, variableName, value); 
		
	}
	
	
	
	@Test 
	public void setJavaBeanVariable(){
		
		//javaBean记得序列化
		//javabean的属性在使用以后不能在改变，如果改变那么则会报错，解决方式是javaBean设置serialVersionUID	
		String taskId="60005";
		LeaveInfo  leaveInfo=new LeaveInfo();
		leaveInfo.setId("1");
		leaveInfo.setMessage("我请假了");
		leaveInfo.setStatus("no");
		taskService.setVariable(taskId,"请假信息", leaveInfo);//本方法支持javabean
		//taskService.(taskId, variableName, value); 
		
	}
	
	

	@Test 
	public void getJavaBeanVariable(){
		
		//runtimeService.setVariable(paramString1, paramString2, paramObject);
		String taskId="60005";
		LeaveInfo  leaveInfo=(LeaveInfo) taskService.getVariable(taskId, "请假信息");
	   System.out.println("id"+leaveInfo.getId()+",内容："+leaveInfo.getMessage());
		
		//taskService.(taskId, variableName, value);  
	}
	
	 
	
	@Test
	public void completeTask(){
		taskService.complete("65002");  
		System.out.println("任务结束了！");
	}
	
	/**
	 * 	历史的流程流程变量
	 */
	@Test
	public void findHsitoryProcessVariables(){
		List<HistoricVariableInstance> list=	historyService.createHistoricVariableInstanceQuery()
		.variableName("请假信息")
		.list();
		System.out.println(list.size());
		for (HistoricVariableInstance historicVariableInstance : list) {
			System.out.println("流程的id"+historicVariableInstance.getId()+",流程的名称"+historicVariableInstance.getVariableName()
			+"流程的值："+historicVariableInstance.getValue());
		}
		
	}

}
