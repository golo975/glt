package com.thunisoft.glt.SE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class SearchUtils {
	private static Log logger = LogFactory.getLog(SearchUtils.class);
	
	private static String root = "D:/MyWorkspace10/fycommon";
	private static String filePath = "C:/Users/gaolong/Desktop/glt/glt20141212.txt";
	private static String resultPath = "C:/Users/gaolong/Desktop/glt/result.txt";
	private static String tagTemplate = "<tag id=\"%1$s\"><tables><table name=\"%2$s\" db=\"%3$s\" hpk=\"%4$s\" spk=\"%5$s\"/></tables><services><service name=\"commonSaveOrUpdateChain\"/></services></tag>";
	
	/**
	 * 步骤一：通过表名（glt20141212.txt）查找类名并格式化输出(result.txt)
	 * @throws IOException
	 */
	@Test
	public void batchSearch() throws IOException{
		File file = new File(filePath);
		File result = new File(resultPath);
		List<String> tableList = FileUtils.readLines(file);
		List<String> finalList = new ArrayList<String>();
//		Map<String, String> beanMap = new HashMap<String, String>();
		Set<String> beanSet = new HashSet<String>();
		for(String tableName : tableList){
			if(StringUtils.isNotBlank(tableName)){
				String beanname = getBeanName(tableName);
				if(!beanSet.contains(beanname)){
					beanSet.add(beanname);
				} else {
					continue;
				}
				System.out.println(beanname);
//				FileUtils.writeStringToFile(result, beanname, true);
//				finalList.add(beanname);
				List<String> list = _search(root, beanname);
//				FileUtils.writeLines(result, list, true);
				finalList.addAll(list);
				for(String s : list){
					System.out.println(s);
				}
//				FileUtils.writeStringToFile(result, "");
//				finalList.add("");
			}
		}
		FileUtils.writeLines(result, finalList, true);
	}
	
	@Test
	public void getMappingFile() throws IOException{
		int i = 0;
		
		File result = new File(resultPath);
		List<String> list = FileUtils.readLines(result);
		for(String original : list){
			System.out.println(original);
			String s = StringUtils.substringBetween(original, "(", ".class,");
			int index = s.lastIndexOf(".");
			String packageStr = s.substring(0, index);
			String beanname = s.substring(index + 1);
			String mappingFileName = beanname + ".hbm.xml";
			Collection<File> mappingFiles = FileUtils.listFiles(new File(root), FileFilterUtils.nameFileFilter(mappingFileName), TrueFileFilter.INSTANCE);
			
			// 得到的映射文件
			File mappingFile = null;
			for(File f : mappingFiles){
				boolean isPackageRight = false;// 包名是否匹配
				for(String line : FileUtils.readLines(f)){
					if(line.indexOf("package") > 0 && StringUtils.substringBetween(StringUtils.substringAfter(line, "package"), "\"").equals(packageStr)){
						isPackageRight = true;
//						break;
					}
					if(line.indexOf("class") > 0 && (packageStr + "." + beanname).equals(StringUtils.substringBetween(StringUtils.substringAfter(line, "name"), "\""))){
						isPackageRight = true;
//						break;
					}
//					if(line.indexOf("s") >= 0){
//						isPackageRight = true;
////						break;
//					}
				}
				if(isPackageRight){
					mappingFile = f;
					break;
				}
			}
			
			// 得到mapping文件中的内容：table， db， PK
			String table = null;
			String db = null;
			String spk = "";
			String hpk = "";
			
			boolean findedTable = false;
			boolean findedDb = false;
			boolean findedSpk = false;
			boolean findedHpk = false;
			
			boolean lookingAtPk = false;
			boolean isCompositeId = false;// 是否是联合主键
			
			List<String> ls = FileUtils.readLines(mappingFile);
			for(String line : ls){
//				System.out.println(line);
				// 确定数表名和据库名
				if(line.indexOf("table") >= 0){
					String tempTable = StringUtils.substringBetween(StringUtils.substringAfter(line, "table"), "\"");
					String[] tempArray = tempTable.split("\\.\\.");
					if(tempArray.length == 1){
						table = tempArray[0];
						findedTable = true;
					} else if(tempArray.length == 2){
						db = tempArray[0];
						table = tempArray[1];
						findedDb = true;
						findedTable = true;
//						System.out.println("glt");
//						break;
					} else {
						throw new RuntimeException("错措错，是我的错！");
					}
				}
				// 确定数据库名
				if(line.indexOf("catalog") >= 0){
					db = StringUtils.substringBetween(StringUtils.substringAfter(line, "catalog"), "\"");
					findedDb = true;
				}
				// 确定主键
				if(line.indexOf("<id") >= 0 || line.indexOf("<composite-id") >= 0){
//					if(StringUtils.isBlank(spk)){
						spk = StringUtils.substringBetween(StringUtils.substringAfter(line, "column"), "\"");
//					} else {
//						String _s = StringUtils.substringBetween(StringUtils.substringAfter(line, "column"), "\"");
//						if(StringUtils.isNotBlank(_s)){
//							spk = spk + "," + _s;
//						}
//					}
						if(line.indexOf("<composite-id") >= 0){
							isCompositeId = true;
						}
						if(!isCompositeId){
							hpk = StringUtils.substringBetween(StringUtils.substringAfter(line, "name"), "\"");
						}
					
					
					
					
					if(StringUtils.isBlank(spk)){
						lookingAtPk = true;
					}
					if(line.indexOf("<id") >= 0 && StringUtils.isNotBlank(spk)){
						findedSpk = true;
					}
				} else {
					if(line.indexOf("</id") >= 0 || line.indexOf("</composite-id") >= 0){
						lookingAtPk = false;
						if(StringUtils.isNotBlank(spk)){
							findedSpk = true;
						}
						if(StringUtils.isNotBlank(hpk)){
							findedHpk = true;
						}
					}
					if(lookingAtPk){
						if(StringUtils.isBlank(spk)){
							spk = StringUtils.substringBetween(StringUtils.substringAfter(line, "column"), "\"");
						} else {
							String _s = StringUtils.substringBetween(StringUtils.substringAfter(line, "column"), "\"");
							if(StringUtils.isNotBlank(_s)){
								spk = spk + "," + _s;
							}
						}
						
						if(line.indexOf("<column") < 0){
							if(StringUtils.isBlank(hpk)){
								hpk = StringUtils.substringBetween(StringUtils.substringAfter(line, "name"), "\"");
							}else {
								String _h = StringUtils.substringBetween(StringUtils.substringAfter(line, "name"), "\"");
								if(StringUtils.isNotBlank(_h)){
									hpk = hpk + "," + _h;
								}
							}
						}
					}
				}
				
				// TODO 这里需要在加上findedPk， 最后应该把 hqk 和spk都找到。
//				System.out.println(findedTable && findedDb && findedPk);
				if(findedTable && findedDb && findedSpk && findedHpk){ 
					break;
				}
			}
			if(findedTable && findedDb && findedSpk && findedHpk){
//				System.out.println(++i + " -- " + table + " -- " + db + " -- " + spk + " -- " + hpk);
				++i;
				System.out.println(String.format(tagTemplate, "0002_" + ((10022 + i) + "").substring(1), table, db, hpk, spk));
			} else {
//				System.out.println(original);
//				System.out.println(mappingFile.getAbsolutePath());
//				throw new RuntimeException("解析mapping文件失败！");
			}
			
			
			
//			System.out.println(mappingFile.getAbsolutePath());
			
		}
	}
	
	public String getBeanName(String tableName){
		String[] array = StringUtils.substringAfter(tableName, "..").split("_");
		StringBuilder sb = new StringBuilder();
		for(String s : array){
			sb.append(StringUtils.capitalize(s.toLowerCase()));
		}
		return sb.append(".java").toString();
	}

	@Test
	public void search(){
		String root = "D:/MyWorkspace10/fycommon";
		String filename = "TMsHb.java";
//		Collection<File> files = FileUtils.listFiles(new File(root), FileFilterUtils.nameFileFilter(filename), TrueFileFilter.INSTANCE);
//		for(File file : files){
//			String path = file.getAbsolutePath();
////			System.out.println(path);
//			System.out.println(formatePath(path));
//		}
		List<String> list = _search(root, filename);
		for(String s : list){
			System.out.println(s);
		}
		System.out.println();
	}
	
	private List<String> _search(String root, String filename){
		Collection<File> files = FileUtils.listFiles(new File(root), FileFilterUtils.nameFileFilter(filename), TrueFileFilter.INSTANCE);
		List<String> list = new ArrayList<String>();
		if(files.size() == 0){
			logger.error("没有搜索到任何类，可能发生了错误！");
//			list.add("没有搜索到任何类，可能发生了错误！");
		}
		for(File file : files){
			String path = file.getAbsolutePath();
//			System.out.println(path);
//			System.out.println(formatePath(path));
			list.add(formatePath(path));
		}
		return list;
//		System.out.println();
	}
	
	private String formatePath(String path){
		String s = StringUtils.substringAfter(path, "src\\").replace(".java", ".class").replace("\\", ".");
		return "dbTableBeanMap.put(" + s + ", null);";
	}
	
	@Test
	public void formatePathTest(){
		String path = "D:/MyWorkspace10/fycommon/src/com/thunisoft/fy/spxt/bean/fy0/sj/BMs.java";
		System.out.println(formatePath(path));
	}
}
