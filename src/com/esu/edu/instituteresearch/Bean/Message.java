package com.esu.edu.instituteresearch.Bean;

public class Message {
	
	
	boolean status;
	String message;
	
	
	public Message(boolean status,String message){
		this.status=status;
		this.message=message;
	}
	
	public Message(boolean status){
		this.status=status;
	}
	

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
