package com.dreamfactory.ftr.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dreamfactory.ftr.mode.ResultMode;
import com.dreamfactory.ftr.util.Loader;

public class ExportResult {

	private static final String TEMPLATE_NAME = "template.xls";
	private String templatePath;
	
	public ExportResult() {
		templatePath = Loader.extractFile(TEMPLATE_NAME);
		
	}
	
	public void writeData2Xls(List<ResultMode> results) throws Exception {
		
		InputStream is = new FileInputStream(templatePath);
		HSSFWorkbook hhsfWorkbook = new HSSFWorkbook(is);
		HSSFSheet sheet = hhsfWorkbook.getSheetAt(0);
		
		HSSFCellStyle cellStyle = hhsfWorkbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle descCellStyle = hhsfWorkbook.createCellStyle();
		descCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		descCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		descCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		descCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		descCellStyle.setWrapText(true);
		descCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		
		for (int i = 0 ; i < results.size() ; i++) {
			
			ResultMode mode = results.get(i);
			HSSFRow row = sheet.getRow(i + 1);
			HSSFCell idCell = row.createCell(0);
			idCell.setCellValue(i + 1);
			idCell.setCellStyle(cellStyle);
			
			HSSFCell fileNameCell = row.createCell(1);
			fileNameCell.setCellValue(mode.getFileName());
			fileNameCell.setCellStyle(cellStyle);
			
			HSSFCell commentLineCell = row.createCell(2);
			commentLineCell.setCellValue(mode.getCommentLine());
			commentLineCell.setCellStyle(cellStyle);
			
			HSSFCell reviewerCell = row.createCell(3);
			reviewerCell.setCellValue(mode.getReviewer());
			reviewerCell.setCellStyle(cellStyle);
			
			HSSFCell priorityLevelCell = row.createCell(4);
			priorityLevelCell.setCellValue(mode.getPriorityLevel());
			priorityLevelCell.setCellStyle(cellStyle);
			
			HSSFCell descriptionCell = row.createCell(5);
			descriptionCell.setCellValue(mode.getDescription());
			descriptionCell.setCellStyle(descCellStyle);
		}
		
		OutputStream op = new FileOutputStream(templatePath);
		hhsfWorkbook.write(op);
		op.close();
		hhsfWorkbook.close();
		System.out.println("File Exoprt Successfully!!!");
	}
}
