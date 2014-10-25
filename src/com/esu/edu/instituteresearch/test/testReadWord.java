package com.esu.edu.instituteresearch.test;



import java.io.File;
import java.io.FileInputStream;  

  

import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;  
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;  
import org.apache.poi.hwpf.usermodel.Range;  
import org.apache.poi.hwpf.usermodel.Table;  
import org.apache.poi.hwpf.usermodel.TableCell;  
import org.apache.poi.hwpf.usermodel.TableIterator;  
import org.apache.poi.hwpf.usermodel.TableRow;  
  
 
  
  
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;  
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
  


public class testReadWord  
{  
	
	public static void main(String[] args) {
		testWord();
	}
	
    public static void testWord(){  
        try{  
            FileInputStream in = new FileInputStream("E:/SPSS/ACADEMIC YEAR 2014Test.dot");//载入文档  
           POIFSFileSystem pfs = new POIFSFileSystem(in);   
            
           HWPFDocument  hwpf = new HWPFDocument (pfs);     
            Range range = hwpf.getRange();//get the document range
            TableIterator it = new TableIterator(range);  
            while (it.hasNext()) {     
                Table tb = (Table) it.next();     
                // the rows
                for (int i = 0; i < tb.numRows(); i++) {     
                    TableRow tr = tb.getRow(i);  
                    if(tr.getCell(0).text().trim().matches("[1-9]{1,}")){
                    	// get the first 5 columns of each row
                    	for (int j = 0; j < 5; j++) {
                            TableCell td = tr.getCell(j);//取得单元格  
                            	System.out.print(td.text().replaceAll(" ", "").trim()+" ");
                    	 System.out.println("");
                    }
                 }
                 
                }   
            } 
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
    
	
	public void test2007(){
		
		// TODO Auto-generated method stub
				 try {  
			            //word 2003： 图片不会被读取  
					 /*
			              InputStream is = new FileInputStream(new File("c://files//2003.doc"));  
			            WordExtractor ex = new WordExtractor(is);  
			            String text2003 = ex.getText();  
			            System.out.println(text2003);  
			  
			            //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后  
			            OPCPackage opcPackage = POIXMLDocument.openPackage("c://files//2007.docx");  
			            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);  
			            String text2007 = extractor.getText();  
			            System.out.println(text2007);  
			             */ 
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }   
				 try {  
			            //word 2003： 图片不会被读取  
					 /*
			              InputStream is = new FileInputStream(new File("c://files//2003.doc"));  
			            WordExtractor ex = new WordExtractor(is);  
			            String text2003 = ex.getText();  
			            System.out.println(text2003);  
			  */
			            //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后  
			            OPCPackage opcPackage = POIXMLDocument.openPackage("E:/SPSS/ADCP 2014-2015.docx");  
			            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);  
			            String text2007 = extractor.getText();
			            
			            System.out.println(text2007);  
			              
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
		
	}
	
	
}