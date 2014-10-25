package com.esu.edu.instituteresearch.controller;

import com.esu.edu.instituteresearch.Bean.Message;
import com.esu.edu.instituteresearch.Bean.VariableBean;

public class CommonCheck {
		
	
	
	public static Message checkNumeric(String in, VariableBean bean){
		
		String pattern="[0-9]{1,}";
		Message message=new Message(false,"There are some non-digit characters");
		boolean boo=in.matches(pattern);
		if(boo==true){
			message.setStatus(true);
			message.setMessage("Pass check");
		}
		return message;
		
	}
	
	// default check only one digit after doc
	//it must be all numbers and there are  number after dot
	public static Message checkDouble(String in, VariableBean bean,int ...len){
		String pattern;
		StringBuilder  error= new StringBuilder("There are some non-digit characters or should have at least ");
	if(len.length==1){
		int value=len[0];
		if(value<1)
			return null;
		pattern="[0-9]{1,}\\.[0-9]{"+String.valueOf(value)+"}";
		error.append(len[0]);
	}else{
		pattern="[0-9]{1,}\\.[0-9]{1}";
		error.append(1);
	}
	error.append(" digit after dot");
	Message message=new Message(false,error.toString());
	boolean boo=in.matches(pattern);
	if(boo==true){
		message.setStatus(true);
		message.setMessage("Pass check");
	}
	return message;
	
		
	}
	
	
	public static Message checkLen(String in, VariableBean bean){
	
		Message message=new Message(true,"Pass check");
		int actLength=in.length();
		int defineLen=bean.getWidth();
		
		if(actLength!=defineLen){
			message.setMessage(",The length of "+in+"should be "+defineLen+",instead of "+actLength);
			message.setStatus(false);
		}
		
		return message;
	}
	
	
	public static void main(String[] args){
		
		Message msg;
		VariableBean bean= new VariableBean("name",5,VariableBean.Type.String);
		
		msg=checkLen("1234",bean);
		
		System.out.println(msg.getMessage());
		
	}
	
	

}
