package Amazon.AmazonWebUITestProject.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

public class ExcelDataReader {
	static ArrayList<String> dataList=new ArrayList<String>();
	static int rowCount=0;
	static int columnCount=0;
	public static ArrayList<String> getDataForLogintoOrderTest() throws IOException {
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\AmazonWebUITestProject\\data\\DataDrivenExcel.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		int scheetCount=workbook.getNumberOfSheets();
		for(int i=0;i<scheetCount;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("testData")) {
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				
				int k=0;

				while(rows.hasNext()) {
					Row row=rows.next();
					Iterator<Cell> cells=row.cellIterator();
					while(cells.hasNext()) {
						Cell cell=cells.next();
						if(cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
							//country=cells.next().getStringCellValue();
							while(rows.hasNext()) {
								row=rows.next();
								cells=row.cellIterator();
								if(cells.next().getStringCellValue().equalsIgnoreCase("LoginToOrderTest")) {
									k=columnCount+1;
									//System.out.println(row.getCell(k).getStringCellValue());
									while((row.getCell(k))!=null) {
										dataList.add(row.getCell(k).getStringCellValue());
										k++;
									}
									break;
								}
								
								
							}	
						}
						columnCount++;
						
					}
					rowCount++;
				}
				
			}
			
		}
		return dataList;
	}

// public  void main() throws IOException {
////public static void main(String[] args) throws IOException{
//	ExcelDataReader edr=new ExcelDataReader();
//	System.out.println("hello  1");
//	System.out.println(edr.getDataForLogintoOrderTest());
//	System.out.println("RowCount and ColumnCount:  "+edr.rowCount+" and  "+edr.columnCount);
//	
//}
}
