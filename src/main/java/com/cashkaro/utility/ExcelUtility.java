package com.cashkaro.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;



public class ExcelUtility<T> {
	
	private String filePath;
	private String fileName;
	Workbook wb  =null;
	
	public ExcelUtility(String filePath,String fileName) {
		
		this.filePath = filePath;
		this.fileName = fileName;
	}
	public static void main(String[] args) throws Exception {
		
		ExcelUtility<DataInfo> data = new ExcelUtility<DataInfo>("./src/main/resources/testData/","SampleTestData.xlsx");
	
		
		List<DataInfo> l =  data.getData(DataInfo.class);
		
		for(DataInfo d : l){
			System.out.println(d.searchData);
			System.out.println(d.name);
		}
	}
	
	
	private Workbook getWorkBook() throws FileNotFoundException{
		
		Workbook wb = null;
		File excel = new File(filePath +"/" + fileName);
		
		FileInputStream isr = new FileInputStream(excel);
		
		try {
			wb = new XSSFWorkbook(isr);
		} catch (IOException e) {
			
		}
		
		return wb;
	}
	
	public  List<String> readExcelFields() throws FileNotFoundException{
		
		ArrayList<String> fields = new ArrayList<String>();
		wb = getWorkBook();
		Sheet sheet0 =  wb.getSheetAt(0);
		Row topRow  = sheet0.getRow(sheet0.getFirstRowNum());
		
		Iterator<Cell> cellIterator = topRow.cellIterator();
		
		while(cellIterator.hasNext()){
			
			fields.add(cellIterator.next().getStringCellValue());
		}
		return fields;
		
		
		
		
		
	}
	
	public ArrayList<ArrayList<String>> getAllRows(){
		
		ArrayList<ArrayList<String>> allRows = new ArrayList<ArrayList<String>>();
		
		Sheet sheet0 = wb.getSheetAt(0);
		
		for(int i= sheet0.getFirstRowNum() +1 ; i <=sheet0.getLastRowNum(); i++){
			
			ArrayList<String> rowData = new ArrayList<String>();
			Row row =sheet0.getRow(i);
			
			Iterator<Cell> cellIt  = row.cellIterator();
			
			while(cellIt.hasNext()){
				
				Cell c = cellIt.next();
				
				switch (c.getCellType()){
				
				case Cell.CELL_TYPE_NUMERIC:
				rowData.add(String.valueOf(c.getNumericCellValue()));
				break;
				
				default :
					rowData.add(c.getStringCellValue());
				}
				
			}
			
			allRows.add(rowData);
		}
		
		return allRows;
	}
	
	
	public List<T> getData(Class<T> dataClass) throws Exception{
		
		List<T> dataList = new ArrayList<T>();
		
		List<String> ef = readExcelFields();
	
		
		
		
	 for(List<String> data : getAllRows()){	
		
		T object = dataClass.newInstance();
		
		
		for(Field f : object.getClass().getDeclaredFields()){
			
		  for(int i = 0; i < ef.size(); i++){
			  
			  
			  if(f.getName().equalsIgnoreCase(ef.get(i))){
				  
				  
				 
				 String d =  data.get(i);
				 f.set(object,d);
			  }
			  
		  }
			
			
		}
		
		dataList.add(object);
	 }
		return dataList;
		
		
		
	}
	
	public void verifyFields(List<String> excelFields, List<String> classFields){
		
		
		if(excelFields.isEmpty()){
			
		  Reporter.log("Excel field is empty",1);
		}
		
		if(classFields.isEmpty()){
			
			  Reporter.log("Class field is empty",1);
		}
		
		
		
		
		for(String excelField : excelFields){
			boolean fieldInClass = false;
			
			for(String classField : classFields){	
				
				if(excelField.equalsIgnoreCase(classField)){
					
					fieldInClass = true;
					
				}
					
			}
		
		if(fieldInClass){
			 Reporter.log(excelField + "Field not founf in class",1);
		}
			
		}
		
		
	}
	
	
	public List<String> getClassFields(Class<T> cls){
		
		List<String> fields = new ArrayList<String>();
        for(Field f :cls.getDeclaredFields()){
			
        	fields.add(f.getName());
		}
		return fields;
	}

	
	


}
