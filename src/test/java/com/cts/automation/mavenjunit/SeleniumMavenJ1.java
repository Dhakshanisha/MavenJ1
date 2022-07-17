package com.cts.automation.mavenjunit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.sevenz.CLI;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumMavenJ1 {
	static WebDriver driver;
	String EmailId;
	String Password;
	@BeforeClass
	public static void fb()
	{
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		driver.navigate().to("https://www.facebook.com");
	}	
	@Before
	public void intitalize() throws IOException
	{
		File f =new File("C:\\Users\\Dell\\eclipse-workspace\\MavenJ1\\src\\test\\resources\\TestData\\New Microsoft Excel Worksheet.xlsx");
		FileInputStream fis =new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet s1=w.getSheet("Sheet1");
		Row r0 = s1.getRow(0);
		Cell c0 =r0.getCell(0);
		EmailId=c0.getStringCellValue();
		Cell c1 =r0.getCell(1);
		Password =c1.getStringCellValue();
	}
	@Test
	public void info() throws IOException
	{
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(EmailId);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Password);
		driver.findElement( By.xpath("//button[@ name='login']")).click();
	}
	@After
	public void finalize() throws IOException
	{
		String txtvalue =  driver.findElement( By.xpath("//div [@ role='alert' ]")).getText();
		File f =new File("C:\\Users\\Dell\\eclipse-workspace\\MavenJ1\\src\\test\\resources\\TestData\\New Microsoft Excel Worksheet.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s =w.createSheet("sheet1");
		Row r =s.createRow(2);
		Cell c= r.createCell(2);
		c.setCellValue(txtvalue);
		FileOutputStream fos =new FileOutputStream(f);
		w.write(fos);
	}
	@AfterClass
	public static void end()
	{
		driver.close();
	}

}