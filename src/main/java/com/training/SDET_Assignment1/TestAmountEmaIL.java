package com.training.SDET_Assignment1;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xerces.stax.EmptyLocation;

public class TestAmountEmaIL {
	
	public static void main (String args[]) throws IOException, InvalidFormatException {
		Excel_Readandwrite exc = new Excel_Readandwrite();
		ExcelWriter ex = new ExcelWriter();
		//Guru99_Page g9= new Guru99_Page();
		System.out.println("Amount from Excel is " + exc.hashRead("Amount").toString()+ " " + exc.readEmail());
		//exc.hashRead();
		//g9.generate_Login_password();
				
	}

}
