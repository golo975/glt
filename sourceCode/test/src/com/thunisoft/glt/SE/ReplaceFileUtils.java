package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReplaceFileUtils {

	public static void main(String[] args) throws IOException {
		String srcDir = "C:/Users/gaolong/Desktop/laxt";
		String destDir = "C:/Users/gaolong/Desktop/立案系统替换文件/laxt";
		String[] filePathArray = {"WEB-INF/classes/com/thunisoft/fy/yyla/YYLALogic.class", 
				"jsp/ladj/ms/caseEditPage.jsp"};
		
		for(String filePath : filePathArray){
			File from = FileUtils.getFile(srcDir, filePath);
			File to = FileUtils.getFile(destDir, filePath);
			System.out.println(from.getName());
			copySubFile(from, to);
			FileUtils.copyFile(from, to);
		}
	}
	
	private static void copySubFile (File from, File to) throws IOException{
		Collection<File> fileList = FileUtils.listFiles(from.getParentFile(), null, false);
		for(File f : fileList){
			if(f.getName().indexOf(FilenameUtils.getBaseName(from.getName()) + "$") >= 0 ){
				File subTo = FileUtils.getFile(to.getParentFile(), f.getName());
				FileUtils.copyFile(f, subTo);
				System.out.println(subTo.getName());
			}
		}
	}
}
