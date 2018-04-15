package com.training.SDET_Assignment1;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.stax.EmptyLocation;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelWriter {

    Workbook workook = new XSSFWorkbook();
    
    public String writeExcel(String[] tvalues) throws IOException {
    	Sheet sheet= workook.createSheet("LoginDeatils");
    	Font headerFond= workook.createFont();
    	Row headerRow=sheet.createRow(0);
    	
    	for (int i =0;i<tvalues.length;i++) {
    		Cell cell=headerRow.createCell(i);
    		cell.setCellValue(tvalues[i]);
    		
    	}
    	
    	for (int i=0 ; i < tvalues.length; i++) {
    		sheet.autoSizeColumn(tvalues.length);
    	}
    	
    	FileOutputStream outfile= new FileOutputStream("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx");
    	workook.write(outfile);
    	outfile.close();
    	return outfile.toString();
    	
    }
    
	
	
}