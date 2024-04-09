package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil
{
	Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String Excelpath) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = WorkbookFactory.create(fi);
	}
	//method for counting no. of rows in a sheet
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	//method for reading cell data
	public String getCellData(String sheetName, int row, int column) 
	{
		String data = "";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//method for writing status into workbook
	public void setCellData(String sheetName, int row, int column, String status, String WriteExcelPath) throws Throwable
	{
		//get sheet from workbook
		Sheet ws = wb.getSheet(sheetName);
		//get row from sheet
		Row rowNum = ws.getRow(row);
		//create cell
		Cell cell = rowNum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcelPath);
		wb.write(fo);
	}
	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil("D://Live_Project_Automation/Sample_1.xlsx");
		//count no. of rows in emp sheet
		int rc = xl.rowCount("Emp");
		System.out.println(rc);
		for(int i=1; i<=rc; i++)
		{
			String firstname = xl.getCellData("Emp", i, 0);
			String middlename = xl.getCellData("Emp", i, 1);
			String lastname = xl.getCellData("Emp", i, 2);
			String eid = xl.getCellData("Emp", i, 3);
			System.out.println(firstname+"     "+middlename+"     "+lastname+"     "+eid);
			xl.setCellData("Emp", i, 4, "pass","D://Live_Project_Automation/Results_2.xlsx");
		}
	}
}
