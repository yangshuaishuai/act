package com.yss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yss.dao.UserDao;
import com.yss.pojo.User;
import com.yss.service.inter.UserServiceInf;

@Service
public class UserService implements UserServiceInf {
	@Autowired
	private UserDao userdao;

	@Override
	public User getUserNameById(String id) {
		Object ob= userdao.getUserNameById("1");
       return  (User)ob;
	}

	@Override
	public void insertUser(User user) {
		userdao.insertUser(user);
		
	}
	
	
	@Override
	public void insertTest(JSONObject json,String busId) {
		//userdao.insertTest(sql);
		
		jxAxnJ(json, busId);
	}


	public  void jxAxnJ(JSONObject json,String busId){
		
	   String tableName=json.getString("caseCode");
	   Object data=json.get("data");
	   String id =UUID.randomUUID().toString();
	   
	   
	   
	   //data是json
	   if(data instanceof JSONObject){
		   JSONObject json1=(JSONObject)data; 
		   Set<String> keySet1=json1.keySet(); 
		   List<String> key1List=new ArrayList<String>();
		   key1List.add("id");
		   key1List.add("bus_Id");
	  	   List<String> value1List=new ArrayList<String>();
	  	   value1List.add("'"+id+"'");
	  	   value1List.add("'"+busId+"'");
		   
		   for (String key1 : keySet1) {  //循环第二层
			  
			   key1List.add(key1);
			   Object data1=json1.get(key1);
			   if(data1 instanceof JSONObject){
				   
				   JSONObject json2=(JSONObject)data1;
				   value1List.add("'"+""+"'");
				   String tableName2=key1;
				   //循环第三层
				   List<String> key2List=new ArrayList<String>();
				   key2List.add(tableName+"_id");
				   key2List.add("id");
				   key2List.add("bus_Id");
				   
			  	   List<String> value2List=new ArrayList<String>();
			  	   value2List.add("'"+id+"'");
			  	   value2List.add("'"+UUID.randomUUID().toString()+"'");
			  	   value2List.add("'"+busId+"'");
			  	   
			  	   Set<String> keySet2=json2.keySet(); 
 			  	   for (String key2 : keySet2) {
 			  		key2List.add(key2);
 			  		value2List.add("'"+(json2.getString(key2))+"'");

				   }
 			  	  
 			  	  
 			 	   System.out.println("tableName:" +tableName+"_"+tableName2+";key:"+StringUtils.join(key2List.toArray(), ",")+";value:"+StringUtils.join(value2List.toArray(), ","));
 			 	   StringBuffer sql0=new StringBuffer();
 			 	   sql0.append("insert into axn_sdbgv2_"+tableName+"_"+tableName2+"("+StringUtils.join(key2List.toArray(), ",") +")values ("+StringUtils.join(value2List.toArray(), ",")+")");
 			 	   System.err.println("1"+sql0.toString()); 
 			 	   userdao.insertTest(sql0.toString());
 			 	   
 			 	   

				   
			   } else if(data1 instanceof JSONArray){//因为是数组所以tablename还是上一层的名字
				   value1List.add("'"+""+"'");
				   JSONArray data2= (JSONArray)data1;
				   for (Object data3 : data2) { 
					   JSONObject json2=(JSONObject)data3;
					   List<String> key2List=new ArrayList<String>();
					   key2List.add(tableName+"_id");
					   key2List.add("id");
					   key2List.add("bus_Id");
				  	   List<String> value2List=new ArrayList<String>();
				  	   value2List.add("'"+id+"'");
				  	   value2List.add("'"+UUID.randomUUID().toString()+"'");
				  	   value2List.add("'"+busId+"'");
				  	   Set<String> keySet2=json2.keySet(); 
	 			  	   for (String key2 : keySet2) {
	 			  		key2List.add(key2);
	 			  		value2List.add("'"+(json2.getString(key2))+"'");

					   }
	 			  	System.out.println("tableName:" +tableName+"_"+key1+";key:"+StringUtils.join(key2List.toArray(), ",")+";value:"+StringUtils.join(value2List.toArray(), ","));
	 			    StringBuffer sql1=new StringBuffer();
	 			    sql1.append("insert into axn_sdbgv2_"+tableName+"_"+key1+"("+StringUtils.join(key2List.toArray(), ",") +")values ("+StringUtils.join(value2List.toArray(), ",")+")");
	 			    System.err.println("2"+sql1.toString());
	 			    userdao.insertTest(sql1.toString());

				   
				   
				   } 
			   }else { 
				  // value1List.add("'"+json1.getString(key1)+"'"); 
				   value1List.add("'"+(json1.getString(key1))+"'");

			   }
			     
		   }
		  	System.out.println( "tableName:" +tableName+";key:"+StringUtils.join(key1List.toArray(), ",")+";value:"+StringUtils.join(value1List.toArray(), ","));
		    StringBuffer sql=new StringBuffer();
	 	    sql.append("insert into axn_sdbgv2_"+tableName+"("+StringUtils.join(key1List.toArray(), ",") +")values ("+StringUtils.join(value1List.toArray(), ",")+")");
	 	    System.err.println("3"+sql.toString()); 
	 	    userdao.insertTest(sql.toString());

	   }
	   
	   
	   if(data instanceof JSONArray){ //data 是json数组
		   JSONArray jsonArr1=(JSONArray)data;
		   for (Object object : jsonArr1) {
 				   JSONObject json2=(JSONObject)object;
				   List<String> key2List=new ArrayList<String>();
 				   key2List.add("id");
				   key2List.add("bus_Id");
			  	   List<String> value2List=new ArrayList<String>();
			  	   value2List.add("'"+id+"'");
 			  	   value2List.add("'"+busId+"'");
			  	   Set<String> keySet2=json2.keySet(); 
 			  	   for (String key2 : keySet2) {
 			  		key2List.add(key2);
 			  		value2List.add("'"+(json2.getString(key2))+"'");
 			  		//value2List.add("'"+json2.getString(key2)+"'");
				   }
 				  	System.out.println( "tableName:" +tableName+";key:"+StringUtils.join(key2List.toArray(), ",")+";value:"+StringUtils.join(value2List.toArray(), ","));
 				    StringBuffer sql=new StringBuffer();
 			 	    sql.append("insert into axn_sdbgv2_"+tableName+"("+StringUtils.join(key2List.toArray(), ",") +")values ("+StringUtils.join(value2List.toArray(), ",")+")");
 			 	    System.err.println("4"+sql.toString());

 			 	    userdao.insertTest(sql.toString());

		   	}
		   
	   }
		 
		
	}
	

}
