package com.training.SDET_Assignment1;
import org.openqa.selenium.chrome.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.stax.EmptyLocation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StrBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.SDET_Assignment1.CommonLib;

public class Guru99_Page {
	
	WebDriver oDriver = null;
	Workbook workook = new XSSFWorkbook();
	Excel_Readandwrite exc= new Excel_Readandwrite();
	String cust_id= null, account_id=null;

	
	@BeforeTest
	public void automationSetup() throws Exception{
		
		
		System.out.println("preset check completed");
		oDriver = CommonLib.getDriver("ie");
		//oDriver.get(sUrl);
		Thread.sleep(15000);
		
		
	}
	
	//----------------------------------------------------------------------------------------
	
	
	@Test
	public void generate_Login_password() throws IOException, InvalidFormatException{
		
		oDriver.get("http://demo.guru99.com/");
		System.out.println(oDriver.getTitle());
		String emailId = exc.readEmail();
		WebElement email=oDriver.findElement(By.name("emailid"));
		
		email.sendKeys(emailId);
		oDriver.findElement(By.name("btnLogin")).click();
		WebDriverWait wb= new WebDriverWait(oDriver,60L);
		
		WebElement wait1=wb.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody")));
		
		Guru99_Page g9=new Guru99_Page();
		g9.excel_handle_logic("LoginDetails",wait1,oDriver, workook);
		
	
	}	
	
	
	@Test(dependsOnMethods="generate_Login_password")
	public void create_Customer() throws InvalidFormatException, IOException 
	{
		
		String username=exc.readUserName();
		String password =exc.readPassword();
		oDriver.get("http://demo.guru99.com/V4/index.php");
		oDriver.findElement(By.name("uid")).sendKeys(username);
		oDriver.findElement(By.name("password")).sendKeys(password);;
		oDriver.findElement(By.name("btnLogin")).click();
		WebElement wait2=new WebDriverWait(oDriver,30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		oDriver.findElement(By.partialLinkText("New Customer")).click();
		WebElement wait3=new WebDriverWait(oDriver, 60L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		oDriver.findElement(By.name("name")).sendKeys(exc.hashRead("Name"));
		oDriver.findElement(By.name("dob")).sendKeys(exc.hashRead("DOB"));
		oDriver.findElement(By.name("addr")).sendKeys(exc.hashRead("Address"));
		oDriver.findElement(By.name("city")).sendKeys(exc.hashRead("City"));
		oDriver.findElement(By.name("state")).sendKeys(exc.hashRead("State"));
		oDriver.findElement(By.name("pinno")).sendKeys(exc.hashRead("Pin"));
		oDriver.findElement(By.name("telephoneno")).sendKeys(exc.hashRead("Telephone"));
		oDriver.findElement(By.name("emailid")).sendKeys(exc.hashRead("Email_id"));
		oDriver.findElement(By.name("password")).sendKeys(exc.hashRead("Password"));
		oDriver.findElement(By.name("sub")).click();
		//WebElement wait4=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		
		//Store tale Values
		WebElement iwait4=new WebDriverWait(oDriver,30L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customer\"]/tbody")));
		Guru99_Page g9=new Guru99_Page();
		g9.excel_handle_logic("CustomerDeatils",iwait4,oDriver, workook);
		
		
		
	}
		
		//Account creation Screen
	@Test(dependsOnMethods="create_Customer")
	public void create_Account() throws IOException, InvalidFormatException {
		String cust_id =exc.readCustomer();
		oDriver.findElement(By.partialLinkText("New Account")).click();
		WebElement wait5=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		
		
		oDriver.findElement(By.name("cusid")).sendKeys(cust_id);
		//System.out.println("Amount is "+ exc.hashRead("Amount"));
		oDriver.findElement(By.name("inideposit")).sendKeys(exc.hashRead("Amount"));;
		oDriver.findElement(By.name("button2")).click();
		WebElement wait7=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		wait7=new WebDriverWait(oDriver,30L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"account\"]/tbody")));
		Guru99_Page g9=new Guru99_Page();
		g9.excel_handle_logic("AccountDetails",wait7,oDriver, workook);
		
	}
		
	@Test(dependsOnMethods="create_Account")
	public void edit_Customer() throws IOException, InvalidFormatException
	{	
		String cust_id=exc.readCustomer();
		oDriver.findElement(By.partialLinkText("Edit Customer")).click();
		WebElement wait6=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		oDriver.findElement(By.name("cusid")).sendKeys(cust_id);
		oDriver.findElement(By.name("AccSubmit")).click();
		
		WebElement wait8=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		oDriver.findElement(By.name("city")).clear();
		oDriver.findElement(By.name("city")).sendKeys("Pune city17");
		oDriver.findElement(By.name("sub")).click();
		wait8=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
		WebElement iwait4=new WebDriverWait(oDriver,30L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customer\"]/tbody")));
		Guru99_Page g9=new Guru99_Page();
		g9.excel_handle_logic("Edited_customer",iwait4,oDriver, workook);
		
		
	}
		
	@Test(dependsOnMethods="edit_Customer")	
	public void customsed_Statement() throws IOException, InvalidFormatException{
		
	oDriver.findElement(By.partialLinkText("Customised Statement")).click();
	WebElement wait8=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
	oDriver.findElement(By.name("accountno")).sendKeys(exc.readAccount());
	oDriver.findElement(By.name("fdate")).sendKeys("01/01/1991");
	oDriver.findElement(By.name("tdate")).sendKeys("15/04/2018");
	oDriver.findElement(By.name("AccSubmit")).click();
	wait8=new WebDriverWait(oDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(By.className("barone")));
	WebElement iwait4=new WebDriverWait(oDriver,30L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customstmt\"]/tbody")));
	Guru99_Page g9=new Guru99_Page();
	g9.excel_handle_logic("customized_statement",wait8,oDriver, workook);
	
	oDriver.close();
		
		
	}
	
	
	public void excel_handle_logic(String sheet_name, WebElement wait8, WebDriver oDriver, Workbook workook) throws IOException {
		Sheet sheet= workook.createSheet(sheet_name);
    	Font headerFond= workook.createFont();
    	Row headerRow=sheet.createRow(0);	
		WebElement iwait4=wait8;
		System.out.println(iwait4.findElements(By.tagName("tr")).size());
		int irow_size= iwait4.findElements(By.tagName("tr")).size();
		List<WebElement> itrow= iwait4.findElements(By.tagName("tr"));
		ArrayList<String> tvalues=new ArrayList<String> ();
		for (int i=0 ; i< irow_size; i++ ) {
			if (itrow.get(i).findElements(By.tagName("th")).size() >0) {
				List<WebElement> column = itrow.get(i).findElements(By.tagName("th"));
				//System.out.println("Vaue of coulumn length" + column.size());
				Row row= sheet.createRow(i);
				for (int j=0 ; j< column.size(); j++ ) {
					//System.out.println("Row Value is " + column.get(j).getText());
					//System.out.println("row cols value " + i + ""+ j);
					row.createCell(j).setCellValue(column.get(j).getText());
				}
			}else
			{
			List<WebElement> column = itrow.get(i).findElements(By.tagName("td"));
			//System.out.println("Vaue of coulumn length" + column.size());
			Row row= sheet.createRow(i);
			for (int j=0 ; j< column.size(); j++ ) {
				//System.out.println("Row Value is " + column.get(j).getText());
				//System.out.println("row cols value " + i + ""+ j);
				row.createCell(j).setCellValue(column.get(j).getText());
			}
		}
			//System.out.println("*****");
		}
		
		FileOutputStream outfile= new FileOutputStream("C:\\Users\\maheshba\\SDET_Siggnment1\\testwithjava.xlsx");
    	workook.write(outfile);
	}
	
}
