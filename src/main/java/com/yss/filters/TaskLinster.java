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
		 //paramDelegateTask.setVariables(map);
		 paramDelegateTask.setAssignee("李四");
		
	}

}
