package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReplaceFileUtils {

	public static void main(String[] args) throws IOException {
		String srcDir = "C:/Users/gaolong/Desktop/spxxpt/WEB-INF/classes/";
		String destDir = "C:/Users/gaolong/Desktop/Ìæ»»ÎÄ¼þ/spxxpt/WEB-INF/classes/";
		String[] filePathArray = {"com/thunisoft/spxxpt/common/Consts.class",
				"com/thunisoft/spxxpt/fbsh/service/impl/FbshServiceImpl.class", 
				"com/thunisoft/spxxpt/grfb/service/impl/WsgkServiceImpl.class", 
				"com/thunisoft/spxxpt/webservice/IYwxtWebService.class",
				"com/thunisoft/spxxpt/webservice/SPXTWebServiceClient.class", 
				"com/thunisoft/spxxpt/webservice/bean/WritInfo.class", 
				"com/thunisoft/spxxpt/webservice/impl/YwxtWebServiceImpl.class", 
				"com/thunisoft/spxxpt/ywxt/service/IAppService.class", 
				"com/thunisoft/spxxpt/ywxt/service/impl/AppServiceImpl.class", 
				"com/thunisoft/spxxpt/ywxt/service/impl/YwxtAjxxServiceImpl.class"};
		
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
