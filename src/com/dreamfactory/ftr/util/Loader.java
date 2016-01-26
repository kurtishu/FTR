package com.dreamfactory.ftr.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import com.dreamfactory.ftr.config.Configs;

public class Loader {

	public static String extractFile(String filename) {
		String outputFile = null;
		try {
			File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
			String desktopPath = desktopDir.getAbsolutePath();
			InputStream input = null;
			if (Configs.ISJAR) {
				String str = desktopPath + "\\" + geOutputFileName();
				outputFile = str.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
				input = new BufferedInputStream(Loader.class.getClassLoader()
						.getResourceAsStream(filename));
			} else {
				String temp = System.getProperty("user.dir") + "\\" + filename;
				outputFile = desktopPath+ "\\" + geOutputFileName();
				input = new BufferedInputStream(new FileInputStream(temp));
			}

			OutputStream output = new BufferedOutputStream(
					new FileOutputStream(outputFile));
			byte[] buffer = new byte[2048];
			for (;;) {
				int content = input.read(buffer);
				if (content <= 0) {
					break;
				}
				output.write(buffer, 0, content);
			}
			System.out.println(filename + " file extracted to " + outputFile);
			output.flush();
			output.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputFile;
	}

	private static String geOutputFileName() {
		Date date = new Date();
        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd"); 
        String str = myFmt1.format(date);
		str = "FTR_" + str + ".xls";
		return str;
	}
}
