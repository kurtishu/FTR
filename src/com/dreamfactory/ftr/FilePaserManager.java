package com.dreamfactory.ftr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dreamfactory.ftr.mode.ResultMode;
import com.dreamfactory.ftr.parse.FileReaderHelper;
import com.dreamfactory.ftr.poi.ExportResult;

public class FilePaserManager {
	
	private File targetFile;
	private List<ResultMode> parseResults = new ArrayList<ResultMode>();
	
	public FilePaserManager(String fileName) {
		this.targetFile = new File(fileName);
		parseResults.clear();
	}
	
	public void start(Callback callback) {
		traverseFiles(targetFile);
		 System.out.println("Length:"+parseResults.size() + "parseResults" +parseResults.toString());
	    
		 try {
			new ExportResult().writeData2Xls(parseResults);
			 callback.success();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void paseFile(File file) {
		List<ResultMode> results = FileReaderHelper.parseFile(file);
		if (null != results && results.size() > 0) {
		   System.out.println("paseFile Length:"+results.size() + "results" +results.toString());
		   parseResults.addAll(results);
		}
	}
	
	private void traverseFiles(File root) {
		if (null == root || !root.exists()) {
			return;
		}
		
		if (!root.isDirectory()) {
			paseFile(root);
			return;
		}
		
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				traverseFiles(file);
			} else {
				paseFile(file);
			}
		}
	}
	
	public interface Callback {
		  
		public void success();
	}
}
