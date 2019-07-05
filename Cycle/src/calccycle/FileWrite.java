package calccycle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileWrite {
	
	private String dir;
	private String fileName;
	
	private ArrayList<ArrayList> data;
	
	XSSFWorkbook workbook;
	
	public FileWrite(String dir, String fileName, ArrayList<ArrayList> data) {
		this.dir = dir;
		this.fileName = fileName;
		this.data = data;
		this.workbook = new XSSFWorkbook();
		
		toExcel();
	}
	
	private void toExcel() {
		XSSFSheet sheet = workbook.createSheet();
		
		for(int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(i);
			for(int j = 0; j < data.get(i).size(); j++) {
				Cell cell = row.createCell(j);
				Object value = data.get(i).get(j);
				if(value instanceof String) {
					cell.setCellValue((String) value);
				} else if(value instanceof Formula) {
					CellStyle style = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					style.setDataFormat(
							createHelper.createDataFormat().getFormat(((Formula) value).getFormulaFormat())
							);
					cell.setCellFormula(((Formula) value).toString());
					cell.setCellStyle(style);
				} else if(value instanceof Double) {
					cell.setCellValue((Double) value);
				} else 
					continue;
			}
		}
		
		for(int i = 0; i < data.get(0).size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
		sheet.setColumnWidth(3, sheet.getColumnWidth(3) + 10 * 256);	// 이번 프로그램에서만 사용
	}
	
	public boolean write() {
		try {
			FileOutputStream outputStream;
			if(dir == "")
				outputStream = new FileOutputStream(fileName + ".xlsx");
			else
				outputStream = new FileOutputStream(dir + "\\" + fileName + ".xlsx");
			workbook.write(outputStream);
			workbook.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
