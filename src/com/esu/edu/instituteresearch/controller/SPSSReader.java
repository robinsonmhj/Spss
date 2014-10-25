package com.esu.edu.instituteresearch.controller;

import com.esu.edu.instituteresearch.Bean.VariableBean;
import com.ibm.statistics.plugin.Case;
import com.ibm.statistics.plugin.Cursor;
import com.ibm.statistics.plugin.DataUtil;
import com.ibm.statistics.plugin.StatsUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.*;


public class SPSSReader {
	
	private static final Logger log= Logger.getLogger(SPSSReader.class);
	private List<VariableBean> variableList;
	private Case[] data;
	
	
	
	public SPSSReader(String file){
		this.Extract(file);
	}
	
	
	private void Extract(String file) {

		try {

			variableList = new ArrayList<VariableBean>();
			StatsUtil.start();
			StringBuilder command = new StringBuilder("GET FILE='");
			command.append(file);
			command.append("'.");
			StatsUtil.submit(command.toString());
			// get the data file
			DataUtil datautil = new DataUtil();
			data = datautil.fetchCases(false, 0);
			// get the variable definition
			Cursor cursor = new Cursor();
			int count = cursor.getVariableCount();
			for (int i = 0; i < count; i++) {
				String name = cursor.getVariableName(i);
				int width = cursor.getVariableFormatWidth(i);
				int type = cursor.getVariableType(i);
				if(type!=8){// it must be String
					//throw new Exception("");
					log.error("The type of "+ name+ " is not String");
				}
					
				VariableBean bean = new VariableBean(name, width,VariableBean.Type.String);
				variableList.add(bean);
				// System.out.println("name="+name+",width="+width+",type="+type);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	public List<VariableBean> getVariableList() {
		return variableList;
	}


	public void setVariableList(List<VariableBean> variableList) {
		this.variableList = variableList;
	}


	public Case[] getData() {
		return data;
	}


	public void setData(Case[] data) {
		this.data = data;
	}
	

}
