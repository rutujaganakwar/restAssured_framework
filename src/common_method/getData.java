package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData{
	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		//step 1 locate excel data file, by creating the object of fileinputstream
		FileInputStream fis = new FileInputStream("D:\\restassuredevidence\\testdata.xlsx");
		
		//setp 2 create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//step 3 Access the desired sheet 
		//step3.1 fetch the count of sheets available in the excel file
		int countOfSheet = workbook.getNumberOfSheets();
		
		//step 3.2 fetch the name of sheet and compare again desired sheet name
		for (int i=0; i<countOfSheet; i++ )
		{
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testSheetName))
			{
				//step 4 access the sheet and iterate through rows to fetch the columns in which testcasename is count
				XSSFSheet Sheet = workbook.getSheetAt(i);
				
				//step 4.1 create iterator for rows
				Iterator<Row> Rows = Sheet.iterator();
				Row firstRow = Rows.next();
				
				//step 4.2 create iterator for cells
				Iterator<Cell> Cells = firstRow.cellIterator();
				int j=0;
				int tc_column=0;
				
				//step 4.3 read the cell value of row no1 to compare against the test case name
				while(Cells.hasNext())
				{
					Cell cellvalue = Cells.next();
					if(cellvalue.getStringCellValue().equalsIgnoreCase("test_tc"))
					{
						tc_column= j;
						//System.out.println(tc_column);
					}
					j++;
				}
				
				//step 5 fetch the data for designated test case
				while(Rows.hasNext())
				{
					Row dataRow = Rows.next();
					if(dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator<Cell> dataCellvalue = dataRow.cellIterator();
						while(dataCellvalue.hasNext())
						{
							Cell dataOfCell = dataCellvalue.next();
							//method 1 to extract cell value by using try and catch
							try
							{
								String testdata = dataOfCell.getStringCellValue();
								arrayData.add(testdata);
							}
							catch (IllegalStateException e)
							{
								int inttestdata = (int) dataOfCell.getNumericCellValue();
								String StringtestData = Integer.toString(inttestdata);
								arrayData.add(StringtestData);
							}
						/*	//method 2 To extract the cell value by using if and else
							CellType dataType =dataOfCell.getCellType();
							
							if (dataType.toString() == "NUMERIC")
							{
								int inttestData = (int) dataOfCell.getNumericCellValue();
								String stringtestdata = Integer.toString(inttestData);
								arrayData.add(stringtestdata);
							}
							else if (dataType.toString() == "STRING")
							{
								String testData = dataOfCell.getStringCellValue();
								arrayData.add(testData);
							}
							//method 3 Extract the data by converting it into string
							
							String testData = dataCellvalue.next().toString().replaceAll("\\.\\d+$","");
							System.out.println(testData);
							
							//method 4 Extract the data by using Dataformatter
							DataFormatter format = new DataFormatter();
							String testData = format.formatCellValue(dataCellvalue.next());
							System.out.println(testData);*/
						}
					}
				}
			}
		}
	return arrayData;	
	}

}
