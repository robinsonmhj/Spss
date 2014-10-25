package com.esu.edu.instituteresearch.controller;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.esu.edu.instituteresearch.Bean.VariableBean;
import com.esu.edu.instituteresearch.Util.FileUtil;
import com.ibm.statistics.plugin.DataUtil;
import com.ibm.statistics.plugin.StatsUtil;
import com.ibm.statistics.plugin.Variable;

public class GenerateTemplate {
	
	
	private static final Logger log= Logger.getLogger(GenerateTemplate.class);
	
	
	
	
	

	public static void main(String[] args) {
		
		if(args.length!=1){
			log.error("Please specify the document you want to convert to sav");
			return;
		}
		String fileName=args[0];
		List<String> rows;
		VariableBean[] vbArray;
		File file= new File(fileName);
		String path = file.getParent();
		String name;
		String fileNameWithoutSuff;
		VariableBean.Type type;
		
		int width;
		//System.out.println(file.toString());
		if(!file.toString().endsWith("doc")){
			
			log.error("The file you want to convert must be words 2003 to 2007 file which ends with .doc");
			return;
			
		}
		

		if(file.exists()&&!file.isDirectory()){
		//if(!file.isDirectory()){
			rows=FileUtil.readDot2003Table(fileName);
			fileNameWithoutSuff=file.getName().substring(0, file.getName().lastIndexOf("."));
			
			vbArray=covertString2Bean(rows);
			try{
				StatsUtil.start();
				String command="DATA LIST FREE /case.";
				StatsUtil.submit(command);
				DataUtil datautil = new DataUtil();
				Variable var=null;
				for(VariableBean vb:vbArray){
					if(vb==null)
						continue;
					name=vb.getName();
					width=vb.getWidth();
					type=vb.getType();
					try{
						
						
						var = new Variable(name,type.getValue());
						var.setFormatWidth(width);
						
						
					}catch(Exception e){
						e.printStackTrace();
						log.info("add new variable error",e);
					}
					if(var!=null){
						datautil.addVariable(var);
					}
						
					
				}
				datautil.release();
				StatsUtil.submit("DELETE VARIABLE case");
				StringBuilder builder = new StringBuilder("SAVE OUTFILE='");
				//System.out.println("The path is "+path);
				builder.append(path+"\\");
				builder.append(fileNameWithoutSuff);
				builder.append(".sav");
				builder.append("'.");
				StatsUtil.submit(builder.toString());
			}catch(Exception e){
				e.printStackTrace();
				log.info("Error",e);
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		

	}
	
	
	public static VariableBean[] covertString2Bean(List<String> rbList){
		
		int size=rbList.size();
		VariableBean[] vbArray= new VariableBean[size];
		
		
		String name;
		int width=8;
		String[] lines;
		int i=0;
		for(String str:rbList){// there is always a null in the []
			//System.out.println(rbArray[i]);
			lines=str.split(" ");
			name=lines[1];
			try{
				width=Integer.valueOf(lines[4]);
			}catch(Exception e){
				e.printStackTrace();
				log.info(e);
			}
			
			VariableBean bean= new VariableBean(name,width);
			vbArray[i]=bean;
			i++;
		}
		
		return vbArray;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
