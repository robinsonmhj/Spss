package com.esu.edu.instituteresearch.test;

import java.util.Calendar;

import com.ibm.statistics.plugin.*;

public class demo {
	public static void main(String[] args) {
		/*try {
			StatsUtil.start();
			String[] command = { "OMS",
					"/DESTINATION FORMAT=HTML OUTFILE='E:/tmp/demo.html'.",
					"DATA LIST FREE /salary (F).", "BEGIN DATA", "21450",
					"30000", "57000", "END DATA.", "DESCRIPTIVES salary.",
					"OMSEND." };
			StatsUtil.submit(command);
			StatsUtil.stop();
		} catch (StatsException e) {
			e.printStackTrace();
		}
		*/
		
		//createNewVariable();
		getAttributes();
		
	}
	
	public static void createNewVariable(){
		
		try{
			StatsUtil.start();
			String[] command={"DATA LIST FREE /case (A5).",
					"BEGIN DATA",
					"case1",
					"case2",
					"case3",
					"END DATA."};
					StatsUtil.submit(command);
					Variable numVar = new Variable("numvar",0);
					Variable strVar = new Variable("strvar",1);
					Variable dateVar = new Variable("datevar",0);
					dateVar.setFormatType(VariableFormat.DATE);
					double[] numValues = new double[]{1.0,2.0,3.0};
					String[] strValues = new String[]{"a","b","c"};
					Calendar dateValue = Calendar.getInstance();
					dateValue.set(Calendar.YEAR, 2012);
					dateValue.set(Calendar.MONTH, Calendar.JANUARY);
					dateValue.set(Calendar.DAY_OF_MONTH, 1);
					Calendar[] dateValues = new Calendar[]{dateValue};
					DataUtil datautil = new DataUtil();
					datautil.addVariableWithValue(numVar, numValues, 0);
					datautil.addVariableWithValue(strVar, strValues, 0);
					datautil.addVariableWithValue(dateVar, dateValues, 0);
					//datautil.add
					//datautil.
					datautil.release();
					StatsUtil.submit("SAVE OUTFILE='E:/SPSS/11.sav'.");
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void readDataFromDataView(){
		try{
			
			try{
				StatsUtil.start();
				StatsUtil.submit("GET FILE='E:/SPSS/hsb2.sav'.");
				DataUtil datautil = new DataUtil();
				datautil.setConvertDateTypes(true);
				Case[] data = datautil.fetchCases(false, 0);
				Double numvar;
				String strvar;
				Calendar datevar;
				for(Case onecase: data){
				for(int i = 0;i<onecase.getCellNumber();i++){
				CellValueFormat format = onecase.getCellValueFormat(i);
				if(format == CellValueFormat.DOUBLE){
				numvar = onecase.getDoubleCellValue(i);
				System.out.print(numvar+" ");
				}
				else if(format == CellValueFormat.STRING){
				strvar = onecase.getStringCellValue(i);
				System.out.print(strvar+" ");
				}
				else if(format == CellValueFormat.DATE){
				datevar = onecase.getDateCellValue(i);
				System.out.print(datevar+" ");
				}
				}
				System.out.println();
				}
				datautil.release();
				
			}catch (StatsException e) {
				e.printStackTrace();
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void getAttributes(){
		
		
		try{
			StatsUtil.start();
			StatsUtil.submit("GET FILE='E:/SPSS/hsb2.sav'.");
			Cursor cursor= new Cursor();
			int count=cursor.getVariableCount();
			for(int i=0;i<count;i++){
				
				
				String name = cursor.getVariableName(i);
				int width=cursor.getVariableFormatWidth(i);
				int type=cursor.getVariableType(i);
				//System.out.println(width);
				System.out.println("name="+name+",width="+width+",type="+type);
				/*for(int j=0;j<names.lengameth;j++){
					System.out.print(names[j]+" ");
				}*/
				
				
				
			}
			
			cursor.close();
		}catch(Exception e){
			
			
		}
		
		
	}
	
	
}
