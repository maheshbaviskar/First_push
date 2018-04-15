package com.training.SDET_Assignment1;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log.output.FileOutputLogTarget;
import org.apache.poi.openxml4j.*;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.poi.util.StringUtil;

public class Excel_Readandwrite  {
	
	public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\maheshba\\SDET_Siggnment1\\Input_value.xlsx";
	Sheet sheet=null;
	Row row=null;
	Workbook workbook = null;
	String email=null,amount=null;
	public String readEmail() throws  IOException, InvalidFormatException{
		
		
		workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		
		
		
		sheet= workbook.getSheetAt(0);
		DataFormatter dataFormatter= new DataFormatter();
		
		Iterator<Row> rowIterator= sheet.rowIterator();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			
			if (dataFormatter.formatCellValue(row.getCell(0)).equals("Email")) {
				
				email=row.getCell(1).toString();
				
			}
			
		}
		return email;
	}
	
	public String readAmount() throws IOException, InvalidFormatException {
		
		workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));	
		sheet= workbook.getSheetAt(0);
		DataFormatter dataFormatter= new DataFormatter();
		
		Iterator<Row> rowIterator= sheet.rowIterator();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			
			if (dataFormatter.formatCellValue(row.getCell(0)).equals("Amount")) {
				
				amount=row.getCell(1).toString();
				//System.out.println("Row value is:  " + amount);
			}
		}
		
		
		return amount;
		
	}
	
public String readUserName() throws IOException, InvalidFormatException {
		
		workbook = WorkbookFactory.create(new File("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx"));	
		sheet= workbook.getSheetAt(0);
		DataFormatter dataFormatter= new DataFormatter();
		String userName=null;
		Iterator<Row> rowIterator= sheet.rowIterator();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			
			if (dataFormatter.formatCellValue(row.getCell(0)).equals("User ID :")) {
				
				userName=row.getCell(1).toString();
				//System.out.println("Row value is:  " + amount);
			}
		}
		
		
		return userName;
		
	}

public String readPassword() throws IOException, InvalidFormatException {
	
	workbook = WorkbookFactory.create(new File("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx"));	
	sheet= workbook.getSheetAt(0);
	String password=null;
	DataFormatter dataFormatter= new DataFormatter();
	
	Iterator<Row> rowIterator= sheet.rowIterator();
	while (rowIterator.hasNext()) {
		row = rowIterator.next();
		
		if (dataFormatter.formatCellValue(row.getCell(0)).equals("Password :")) {
			
			password=row.getCell(1).toString();
			//System.out.println("Row value is:  " + amount);
		}
	}
	
	
	return password;
	
}
	
	
public String readCustomer() throws IOException, InvalidFormatException {
		
		workbook = WorkbookFactory.create(new File("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx"));	
		sheet= workbook.getSheetAt(1);
		DataFormatter dataFormatter= new DataFormatter();
		String cust_id=null;
		Iterator<Row> rowIterator= sheet.rowIterator();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			
			if (dataFormatter.formatCellValue(row.getCell(0)).equals("Customer ID")) {
				
				cust_id=row.getCell(1).toString();
				//System.out.println("Row value is:  " + amount);
			}
		}
		
		
		return cust_id;
		
	}

public String readAccount() throws IOException, InvalidFormatException {
	
	workbook = WorkbookFactory.create(new File("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx"));	
	sheet= workbook.getSheetAt(2);
	DataFormatter dataFormatter= new DataFormatter();
	String account_Id=null;
	Iterator<Row> rowIterator= sheet.rowIterator();
	while (rowIterator.hasNext()) {
		row = rowIterator.next();
		
		if (dataFormatter.formatCellValue(row.getCell(0)).equals("Account ID")) {
			
			account_Id=row.getCell(1).toString();
			//System.out.println("Row value is:  " + amount);
		}
	}
	
	
	return account_Id;
	
}
	
	
	
	public String hashRead(String input) throws IOException, InvalidFormatException{
		
		HashMap<String , String> values= new HashMap<String, String>();
		workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		Sheet sheet= workbook.getSheetAt(0);
		Iterator<Row> rowIterator=sheet.rowIterator();
		int counter=0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Cell cell = row.getCell(counter);
			values.put(cell.toString(), row.getCell(counter+1).toString());
		}
		
		//System.out.println(values);
		Set keys = values.keySet();
        Iterator itr = keys.iterator();
        String key;
        String value;
        while(itr.hasNext())
        {	
        	String regex = "[0-9]+";
        	Pattern pattern = Pattern.compile(regex);
        	String key_val=itr.next().toString();
        	
        	if (key_val.equals(input)) {
        		
        		if (input.contains("Telephone") | input.contains("Amount") | input.contains("Pin")) {
        		value = (String)values.get(key_val);
        		value = value.substring(1, Math.min(value.length()-1, value.length()-1));
        		return value;
        		}else
        		{
        			value = (String)values.get(key_val);
        			return value;
        			
        		}

        	}
            
        }
        return "";
	}

}
