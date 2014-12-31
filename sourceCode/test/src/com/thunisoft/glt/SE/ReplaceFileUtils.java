package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReplaceFileUtils {

	public static void main(String[] args) throws IOException {
		// 文件的查询目录：把项目打包之后，解压后的输出目录
		String srcDir = "C:/Users/gaolong/Desktop/sfpt";
		// 替换文件的输出目录
		String destDir = "C:/Users/gaolong/Desktop/glt/sfpt替换文件";
		/**
		 * 下面是需要替换的文件的路径，可以从tortoise svn的日志中获得<br/>
		 * 注意这里的路径都是编译后的文件的目录，可能和源码的路径不同<br/>
		 * 所有的路径为相对于  srcDir 的相对路径<br/>
		 */
		String[] filePathArray = {
				"WEB-INF/classes/com/thunisoft/sfpt/artery/ssfwpt/fwjk/dpm/Ssjk12368.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/ssfwpt/fwjk/dpm/Zxxxjkztxg.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/ssfwpt/zhbl/Plbjxq.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/ssfwpt/zhbl/Plfp.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/ssfwpt/zhbl/Zhblkjjm.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/fg/Fgdh.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/fg/Fkhfbd.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/fg/Lxfglb.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/fg/Yzbtgym.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/sfb/Lxfglb.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/sfb/Lxfylb.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/sfb/Sfbejdh.class",
				"WEB-INF/classes/com/thunisoft/sfpt/artery/wsfk/sjtj/Qsfyfkqk.class",
				"WEB-INF/classes/com/thunisoft/sfpt/wsfk/fg/Fpyybd.class",
				"WEB-INF/classes/com/thunisoft/sfpt/wsfk/fg/Thyybd.class"
				};
		
		for(String filePath : filePathArray){
			File from = FileUtils.getFile(srcDir, filePath.trim());
			File to = FileUtils.getFile(destDir, filePath.trim());
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
