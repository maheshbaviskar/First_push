package com.training.SDET_Assignment1;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.xmlbeans.impl.xb.xsdschema.LengthDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Read_Excel_file {

	public static String sample_file="C:\\Users\\maheshba\\SDET_Siggnment1\\Input_value.xlsx";
	
	public static void main(String args[]) throws IOException, InvalidFormatException {
		
		Workbook workbook= WorkbookFactory.create(new File(sample_file));
		System.out.println(workbook.getNumberOfSheets());
		java.util.List<String> sheetsNames= new ArrayList<String>();
		for(int i=0 ; i < workbook.getNumberOfSheets(); i++ ) {
		sheetsNames.add(workbook.getSheetName(i));
		}
		
		
		System.out.println(sheetsNames.size());
		for (int i=0;i<sheetsNames.size();i++) {
			//System.out.println(sheetsNames.get(i));
			Sheet sheet_read=workbook.getSheetAt(i);
		System.out.println(sheet_read.getRow(0).getCell(0));
		System.out.println(sheet_read.getRow(0).getCell(1));
		}
	}
	
}
