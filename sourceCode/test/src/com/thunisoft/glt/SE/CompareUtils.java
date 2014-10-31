package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CompareUtils {
	private static final Log logger = LogFactory.getLog(CompareUtils.class);

	private CompareUtils(){
	}
	
	/**
	 * 读取文件，文件中的每一行以String形式作为List中的一个元素返回
	 * @param file
	 * @return
	 * @throws NullPointerException 如果参数为空或者他所指向的文件不存在
	 */
	@SuppressWarnings("unchecked")
	public static List<String> readFile2List(File file){
		if(file == null || !file.exists()){
			throw new NullPointerException("参数为空或者所指向的文件不存在。");
		}
		try {
			return FileUtils.readLines(file);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return ListUtils.EMPTY_LIST;
		}
	}
	
//	public static class CompareStrategy {
//		
//	}
	
	public static List<String> intersection(List<String> oldL, List<String> newL){
		List<String> rs = new ArrayList<String>();
		for(Iterator<String> iter_old = oldL.iterator(); iter_old.hasNext();){
			String str_old = iter_old.next();
			for(Iterator<String> iter_new = newL.iterator(); iter_new.hasNext();){
				String str_new = iter_new.next();
				boolean b1 = StringUtils.contains(str_old, str_new);
				boolean b2 = StringUtils.contains(str_new, str_old);
				if(b1 || b2){
					rs.add(str_old);
					break;
				}
			}
		}
		return rs;
	}
	
	public static List<String> moreThan(List<String> l_0, List<String> l_1){
		List<String> rs = new ArrayList<String>();
		for(Iterator<String> iter_0 = l_0.iterator(); iter_0.hasNext();){
			String str_0 = iter_0.next();
			boolean hasSame = false;
			for(Iterator<String> iter_1 = l_1.iterator(); iter_1.hasNext();){
				String str_1 = iter_1.next();
				boolean b1 = StringUtils.contains(str_0, str_1);
				boolean b2 = StringUtils.contains(str_1, str_0);
				if(b1 || b2){
					hasSame = true;
					break;
				}
			}
			if(!hasSame){
				rs.add(str_0);
			}
		}
		return rs;
	}
	
	private static void printList(List<?> list){
		for(Object o : list){
			System.out.println(String.valueOf(o));
		}
	}
	private static void replace(List<String> list , String regex, String newStr){
		for(int i = 0 ; i < list.size() ; i++){
			list.set(i, list.get(i).replaceAll(regex, newStr));
		}
	}
	
	public static void main(String[] args) {
//		File oldF = FileUtils.getFile("C:", "Users", "gaolong", "Desktop", "temp", "odl.sql");
//		File newF = FileUtils.getFile("C:", "Users", "gaolong", "Desktop", "temp", "new.sql");
		
		//这里的新旧是反的
		File newF = FileUtils.getFile("C:", "Users", "gaolong", "Desktop", "temp", "odl.sql");
		File oldF = FileUtils.getFile("C:", "Users", "gaolong", "Desktop", "temp", "new.sql");
		
		List<String> oldFList = CompareUtils.readFile2List(oldF);
		List<String> newFList = CompareUtils.readFile2List(newF);
		
//		CompareUtils.replace(newFList, ".*\\\\([\\w0-9\\.-]+)", "$1");
//		CompareUtils.replace(oldFList, ".*\\\\([\\w0-9\\.-]+)", "$1");
//		CompareUtils.printList(newFList);
//		CompareUtils.printList(oldFList);
		
//		List<String> rs = CompareUtils.intersection(oldFList, newFList);
		List<String> rs = CompareUtils.moreThan(oldFList, newFList);
		
		CompareUtils.printList(rs);
	}
	
}
