package com.yss.filters;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskLinster implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask paramDelegateTask) {
		 Map<String,Object> map=new HashMap<String,Object>(); 
		 map.put("name", "李四");
		 paramDelegateTask.setVariables(map);
		
		 //String assignee="张三";
	     //paramDelegateTask.setAssignee(""); //指定办理人（个人任务）
		  
		 //String userId="小A";
		 //String userId2="小B";
		 //paramDelegateTask.addCandidateUser(userId);//指定办理人（组任务）
		//paramDelegateTask.addCandidateUser(userId2);//指定办理人（组任务）
 		
	}

}
