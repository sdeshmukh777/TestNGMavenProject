package main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelApiTest {
	/*public FileInputStream fi=null;
	public XSSFWorkbook	workbook=null;
	public XSSFSheet sheet=null;
	public XSSFRow row=null;
	public XSSFCell cell=null;*/
	
	public FileInputStream fi=null;
	public HSSFWorkbook	workbook=null;
	public HSSFSheet sheet=null;
	public HSSFRow row=null;
	public HSSFCell cell=null;
	
	
	String 	xfilepath;
	
	
	
	

	public ExcelApiTest(String xfilepath,String sheetName) throws Exception
	{
		this.xfilepath=xfilepath;
		fi=new FileInputStream(xfilepath);
		workbook=new HSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
	}
	
	public int getRowCount(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum()+1;
		return rowCount;
		
	}
	
	public int getColumnCount(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		int colCount=row.getLastCellNum();
		return colCount;
	}
	
	public String getData(int RowNum,int ColNum)
	{
			try 
			{
				
		    cell = sheet.getRow(RowNum).getCell(ColNum);
		    String CellData = cell.getStringCellValue();
			 			 
			return CellData;
			}
			catch(Exception e)
			{
				System.out.println(e);
				return "";
			}
	}
	
	
}

