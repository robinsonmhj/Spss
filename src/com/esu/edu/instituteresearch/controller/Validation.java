package com.esu.edu.instituteresearch.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.esu.edu.instituteresearch.Bean.Message;
import com.esu.edu.instituteresearch.Bean.VariableBean;
import com.esu.edu.instituteresearch.Util.FileUtil;
import com.ibm.statistics.plugin.Case;

public class Validation {

	private static final Logger log= Logger.getLogger(Validation.class);
	public static void main(String[] args) {
		
		int len=args.length;
		String dir;
		List<String> fileArray = new ArrayList<String>();;
		if(len==0){
			
			File file= new File("");
			dir=file.getAbsolutePath();
			fileArray=FileUtil.getFile(dir);
			
		}else{
			dir=args[0];
			File file= new File(dir);
			if(file.isDirectory()){
				fileArray=FileUtil.getFile(dir);
				
			}else if(file.isFile()&&file.toString().endsWith(".sav")){
				fileArray.add(dir);
				
			}
		}
		
		
		if(fileArray.size()==0){
			log.error("There is no SPSS files which ends with .sav");
			return;
		}
		
		List<VariableBean> vbList;
		Case[] data;
		String cell;
		Message msg;
		boolean status;
		String error;
		for(String file:fileArray){
			SPSSReader reader= new SPSSReader(file);
			log.error("Checking file"+file);
			vbList=reader.getVariableList();
			data=reader.getData();
			
			int i=0;
			for(Case ca:data){
				
				int j=0;
				for(VariableBean bean:vbList){
					try{
						cell=ca.getStringCellValue(j);
						msg=CommonCheck.checkLen(cell, bean);
						status=msg.isStatus();
						error=msg.getMessage();
						if(status==false){
							log.error("Row "+(i+1)+"Variable name:"+bean.getName()+error);
						}
					}catch(Exception e){
						e.printStackTrace();
						log.info(e);
					}
					j++;
				}
				
				i++;
				
			}
			
		}
		

	}

}
