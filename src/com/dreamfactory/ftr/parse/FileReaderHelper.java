package com.dreamfactory.ftr.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dreamfactory.ftr.config.Configs;
import com.dreamfactory.ftr.mode.ResultMode;

public class FileReaderHelper {
	
	public static List<ResultMode> parseFile(File file) {
		if (isInWhiteListing(file)) {
			return getComments(file);
		}
		return null;
	}
	
	public static boolean isInWhiteListing(File file) {
		String fileName=file.getName();
	    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	    boolean isInWhiteListing = false;
		for(int i = 0 ; i < Configs.WHITELISTING.length; i++) {
			if (null != prefix && prefix.equalsIgnoreCase(Configs.WHITELISTING[i])) {
				isInWhiteListing = true;
				break;
			}
		}
		
		return isInWhiteListing;
	}
	
	public static List<ResultMode> getComments(File file) {
		List<ResultMode> parseResults = new ArrayList<ResultMode>();
		BufferedReader bf = null;
		try {
			FileReader fr = new FileReader(file);
		    bf = new BufferedReader(fr);
			String str;
			int line = 0;
			while( null != (str = bf.readLine())) {
				//System.out.println(str);
				line++;	
				ResultMode mode = paserComment(file.getName(), str, line);
				if (null != mode) {
					parseResults.add(mode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bf) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return parseResults;
	}
	
	public static ResultMode paserComment(String fileName, String str, int line) {
		if(null != str && str.contains("[FTR]")) {
			ResultMode mode = new ResultMode();
			mode.setCommentLine(String.valueOf(line));
			mode.setFileName(fileName);
			mode.parse(str);
			return mode;
		}
		return null;
	}
}
