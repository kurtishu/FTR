package com.dreamfactory.ftr.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dreamfactory.ftr.mode.ResultMode;

public class ExportResult {

	private static final String TEMPLATE_NAME = "template.xls";
	private String templatePath;
	private String outputFilePath;
	
	
	public ExportResult() {
		templatePath = System.getProperty("user.dir") + "/" + TEMPLATE_NAME;
		outputFilePath = System.getProperty("user.dir") + "/" + "FTR.xls";
		
		System.out.println(templatePath);
	}
	
	public void writeData2Xls(List<ResultMode> results) throws Exception {
		InputStream is = new FileInputStream(templatePath);
		HSSFWorkbook hhsfWorkbook = new HSSFWorkbook(is);
		HSSFSheet sheet = hhsfWorkbook.getSheetAt(0);
		for (int i = 0 ; i < results.size() ; i++) {
			
			ResultMode mode = results.get(i);
			HSSFRow row = sheet.getRow(i + 1);
			HSSFCell idCell = row.createCell(0);
			idCell.setCellValue(i + 1);
			
			HSSFCell fileNameCell = row.createCell(1);
			fileNameCell.setCellValue(mode.getFileName());
			
			HSSFCell commentLineCell = row.createCell(2);
			commentLineCell.setCellValue(mode.getCommentLine());
			
			HSSFCell reviewerCell = row.createCell(3);
			reviewerCell.setCellValue(mode.getReviewer());
			
			HSSFCell priorityLevelCell = row.createCell(4);
			priorityLevelCell.setCellValue(mode.getPriorityLevel());
			
			HSSFCell descriptionCell = row.createCell(5);
			descriptionCell.setCellValue(mode.getDescription());
		}
		
		
		
		
		
		OutputStream op = new FileOutputStream(outputFilePath);
		hhsfWorkbook.write(op);
		op.close();
		hhsfWorkbook.close();
		System.out.println("File Exoprt Successfully!!!");
	}
}
