package com.yss.act.p_gateway;

import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ParallelGateway {
	
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void start(){
		
		InputStream bpmInp=this.getClass().getResourceAsStream("parallelGateWay.bpmn");
		
		InputStream imgInp=this.getClass().getResourceAsStream("parallelGateWay.png");
	         
		Deployment deployment= processEngine.getRepositoryService()
		.createDeployment()
		.name("并行网关")
		.addInputStream("并行网关", bpmInp)
		.addInputStream("并行网关", imgInp)
		.deploy();
		
		System.out.println("部署ID："+deployment.getId());
		System.out.println("部署名称："+deployment.getName());
		
		
		
		
	}
	

}
