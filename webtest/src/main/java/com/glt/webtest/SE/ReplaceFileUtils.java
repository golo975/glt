package main.java.com.glt.webtest.SE;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReplaceFileUtils {

	public static void main(String[] args) throws IOException {
		// �ļ��Ĳ�ѯĿ¼������Ŀ���֮�󣬽�ѹ������Ŀ¼
		String srcDir = "C:/Users/gaolong/Desktop/spxxpt";
		// �滻�ļ������Ŀ¼
		String destDir = "C:/Users/gaolong/Desktop/spxxpt�滻�ļ�2";
		/**
		 * ��������Ҫ�滻���ļ���·�������Դ�tortoise svn����־�л��<br/>
		 * ע�������·�����Ǳ������ļ���Ŀ¼�����ܺ�Դ���·����ͬ<br/>
		 * ���е�·��Ϊ�����  srcDir �����·��<br/>
		 */
		String[] filePathArray = {
				"/WEB-INF/lib/susong51-core-1.0.0-SNAPSHOT/com/thunisoft/susong51/sfgk/pojo/TSfgkBgkwsaj.class",
				"/WEB-INF/lib/susong51-core-1.0.0-SNAPSHOT/hibernate/sfgk/TSfgkBgkwsaj.hbm.xml",
				"/WEB-INF/lib/susong51-core-1.0.0-SNAPSHOT/com/thunisoft/susong51/ajxx/pojo/TAjWrit.class",
				"/WEB-INF/lib/susong51-core-1.0.0-SNAPSHOT/hibernate/ajxx/TAjWrit.hbm.xml"
				};
		
		for(String filePath : filePathArray){
//			filePath = new String(filePath.getBytes("utf8"), "gbk");
			File from = FileUtils.getFile(srcDir, filePath.trim());
			File to = FileUtils.getFile(destDir, filePath.trim());
			copySubFile(from, to);
			FileUtils.copyFile(from, to);
			System.out.println(from.getName());
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
