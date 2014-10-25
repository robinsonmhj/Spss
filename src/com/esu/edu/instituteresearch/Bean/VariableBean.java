package com.esu.edu.instituteresearch.Bean;

public class VariableBean {
	
	
	public enum Type{
		Numeric(0),Comma(1),Dot(2),ScientificNotation(3),Date(4),Dollar(5),CustomCurrrenty(6),String(7),RestrictedNumeric(8);
		
		private int value;
		private Type(int value){
			this.value=value;
		}
		
		public int getValue(){
			return value;
		}
		
	}
	
	private String name;
	private int width;
	private Type type;
	private String range;
	
	
	
	
	public VariableBean(String name,int width,Type type){
		
		this.name=name;
		this.width=width;
		this.type=type;
		
		
	}
	
	
public VariableBean(String name,int width){
		
		this.name=name;
		this.width=width;
		this.type=Type.String;
		
	}


public VariableBean(String name,int width,String range){
	
	this.name=name;
	this.width=width;
	this.range=range;
	
	
}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}
	
	public void setRange(String range){
		this.range=range;
		
	}
	public String getRange(){
		
		return range;
	}
	

}
