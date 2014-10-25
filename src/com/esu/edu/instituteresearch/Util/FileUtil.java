package com.esu.edu.instituteresearch.Util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class FileUtil {
	
	private static final Logger log =Logger.getLogger(FileUtil.class);
	
	public static List<String> getFile(String directory){
		
		List<String> list= new ArrayList<String>(); ;
		File folder = new File(directory);
		File[] filelist= folder.listFiles();

		String absolute=folder.getAbsolutePath();
		for(File file:filelist){
			if(file.isFile()&&file.toString().endsWith(".sav")){		
				list.add(absolute+"/"+file.getName());
			}
			
		}
		
		return list;
		//String absoluatePath=directory.getAbsolutePath();
		
		
		
		
	}
	
	
	// the row looks like 1 SSN SSN NNNNNNNNN 9
	public static List<String>  readDot2003Table(String file){
		
		List<String> rows=new ArrayList<String>();
		
        try{  
            FileInputStream in = new FileInputStream(file);//load the file  
           POIFSFileSystem pfs = new POIFSFileSystem(in);   
            
           HWPFDocument  hwpf = new HWPFDocument (pfs);     
           Range range = hwpf.getRange();//get the document range
           TableIterator it = new TableIterator(range);  
           String value;
            while (it.hasNext()) {     
                Table tb = (Table) it.next();     
                // the rows
                for (int i = 0; i < tb.numRows(); i++) {     
                    TableRow tr = tb.getRow(i);  
                    if(tr.getCell(0).text().trim().matches("[0-9]{1,}")){
                    	StringBuilder builder= new StringBuilder();
                    	// get the first 5 columns of each row
                    	int number=5;
                    	for (int j = 0; j < number; j++) {
                            TableCell td = tr.getCell(j);//取得单元格  
                            //System.out.print("haha"+j+td.text().trim()+" ");
                            value=td.text().replaceAll("\\s", "").trim()+" ";
                            // just in case if some of cell can split into more than 1 cell
                            if(value.trim().isEmpty()){
                            	number++;
                            	continue;
                            }
                            builder.append(value);
                    }
                    	System.out.println("");
                    	log.info(builder.toString());
                    	rows.add(builder.toString());
                    	
                 }
                 
                }   
            } 
        }catch(Exception e){  
            e.printStackTrace();  
            log.info("Read Doc file error",e);
        } 
		
		return rows;
		
		
		
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
