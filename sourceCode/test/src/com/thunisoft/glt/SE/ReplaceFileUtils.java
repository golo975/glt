package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReplaceFileUtils {

	public static void main(String[] args) throws IOException {
		// �ļ��Ĳ�ѯĿ¼������Ŀ���֮�󣬽�ѹ������Ŀ¼
		String srcDir = "C:/Users/gaolong/Desktop/sfpt";
		// �滻�ļ������Ŀ¼
		String destDir = "C:/Users/gaolong/Desktop/glt/sfpt�滻�ļ�";
		/**
		 * ��������Ҫ�滻���ļ���·�������Դ�tortoise svn����־�л��<br/>
		 * ע�������·�����Ǳ������ļ���Ŀ¼�����ܺ�Դ���·����ͬ<br/>
		 * ���е�·��Ϊ�����  srcDir �����·��<br/>
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
