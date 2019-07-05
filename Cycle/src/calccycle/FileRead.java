package calccycle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileRead {
	
	private String src;
	
	private ArrayList<ArrayList> dataList;
	
	public FileRead(String src) throws IOException {
		this.src = src;
		this.dataList = new ArrayList<ArrayList>();
		
		read();
	}
	
	public void read() throws IOException {
		FileInputStream excelFile = new FileInputStream(new File(src));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			ArrayList tempList = new ArrayList();
			
			while(cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				if(currentCell.getCellType() == CellType.NUMERIC) {
					tempList.add(Double.valueOf(currentCell.getNumericCellValue()));
				} else if(currentCell.getCellType() == CellType.STRING){
					tempList.add(currentCell.getStringCellValue());
				} else {
					break;
				}
			}
			dataList.add(tempList);
		}
	}
	
	public ArrayList<ArrayList> getData() {
		return this.dataList;
	}

}
