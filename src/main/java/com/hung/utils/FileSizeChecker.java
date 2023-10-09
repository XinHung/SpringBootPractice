package com.hung.utils;

public class FileSizeChecker {

	public static boolean checkFileSize(Long len, int size, String unit) {
		double fileSize = 0;
		
		if("B".equals(unit.toUpperCase())) {
			fileSize = (double) len;
		} else if("K".equals(unit.toUpperCase())) {
			fileSize = (double) len/1024;
		} else if("M".equals(unit.toUpperCase())) {
			fileSize = (double) len/1048576;
		} else if("G".equals(unit.toUpperCase())) {
			fileSize = (double) len/1073741824;
		} else {
			fileSize = 0;
		}
		
		if(fileSize > size)
			return false;
		else
			return true;
	}
}
