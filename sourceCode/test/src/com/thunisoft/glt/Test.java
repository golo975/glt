package com.thunisoft.glt;

import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringBetween;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class Test {

	public static void main(String[] args) throws IOException {
//		List<String> scopeList = readLines(new File(
//				"C:/Users/gaolong/Desktop/glt/glt.txt"));
//		for (int i = 0; i < scopeList.size(); i++) {
//			scopeList.set(i, scopeList.get(i) + ".hbm.xml");
//		}
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		Resource[] resourceArray = resolver
//				.getResources("file:D:/MyWorkspace10/fycommon/**/*.hbm.xml");
//		for (Resource r : resourceArray) {
//			if (scopeList.contains(r.getFilename())) {
//				List<String> lines = readLines(r.getFile());
//				for (String s : lines) {
//					if (s.contains("composite-id")) {
//						System.out.println(r.getFilename());
//						break;
//					}
//				}
//			}
//		}
//		System.out.println(Test.isEquals("1"));
		
//		List<String> list = Arrays.asList(new String[6]);
//		System.out.println(list.size());
		
//		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		boolean b = Object.class.isAssignableFrom("a".getClass());
		System.out.println(b);
	}

	
	@org.junit.Test
	public void getDbTableBeans() throws IOException {
		List<String> scopeList = readLines(new File(
				"C:/Users/gaolong/Desktop/glt/glt2.txt"));
		for (String s : scopeList) {
			if (isNotBlank(s)) {
				String rs = substringBetween(s, "class, ", ");");
				if (isNotBlank(rs) && !rs.equals("null")) {
					System.out.println(rs);
				}
			}
		}
	}

	@org.junit.Test
	public void distinct() {
		Set<Tuple<String, String>> set = new HashSet<Tuple<String, String>>();
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		
		set.add(new Tuple<String, String>("K_MS", "B_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_WL", "T_MS"));
		set.add(new Tuple<String, String>("K_ZS", "B_MS"));
		set.add(new Tuple<String, String>("K_ZS", "B_MS"));
		set.add(new Tuple<String, String>("K_MS", "B_MS"));
		set.add(new Tuple<String, String>("DB_GD", "B_MS"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZX"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZX"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZ"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZ"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZ"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZ"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZ"));
		set.add(new Tuple<String, String>("K_XS", "B_XS"));
		set.add(new Tuple<String, String>("DB_WL", "T_XS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XS"));
		set.add(new Tuple<String, String>("K_XS", "B_XS"));
		set.add(new Tuple<String, String>("DB_GD", "B_XS"));
		set.add(new Tuple<String, String>("K_PC", "B_PC"));
		set.add(new Tuple<String, String>("DB_WL", "T_PC"));
		set.add(new Tuple<String, String>("K_ZS", "B_PC"));
		set.add(new Tuple<String, String>("K_PC", "B_PC"));
		set.add(new Tuple<String, String>("DB_GD", "B_PC"));
		set.add(new Tuple<String, String>("K_MS", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSCQJF"));
		set.add(new Tuple<String, String>("K_MS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_ZS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSDSR"));
		set.add(new Tuple<String, String>("K_MS", "B_MSGSCG"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSGSCG"));
		set.add(new Tuple<String, String>("K_MS", "B_MSKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSKCSX"));
		set.add(new Tuple<String, String>("K_MS", "B_MSKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSKT"));
		set.add(new Tuple<String, String>("K_MS", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("K_MS", "B_MSYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSYCSX"));
		set.add(new Tuple<String, String>("K_MS", "B_MSZJJH"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSZJJH"));
		set.add(new Tuple<String, String>("K_MS", "T_MS_HB"));
		set.add(new Tuple<String, String>("DB_WL", "T_MS_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_MS_HB"));
		set.add(new Tuple<String, String>("K_MS", "T_MSGXYY"));
		set.add(new Tuple<String, String>("K_ZS", "T_MSGXYY"));
		set.add(new Tuple<String, String>("K_PC", "B_PCCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCCQJF"));
		set.add(new Tuple<String, String>("K_PC", "B_PCDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCDSR"));
		set.add(new Tuple<String, String>("K_PC", "B_PCKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCKCSX"));
		set.add(new Tuple<String, String>("K_PC", "B_PCKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCKT"));
		set.add(new Tuple<String, String>("K_PC", "B_PCQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCQZCS"));
		set.add(new Tuple<String, String>("K_PC", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("K_PC", "B_PCSSDLR"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCSSDLR"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCSSDLR"));
		set.add(new Tuple<String, String>("K_PC", "B_PCYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCYCSX"));
		set.add(new Tuple<String, String>("K_PC", "B_PCZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCZJJH"));
		set.add(new Tuple<String, String>("K_PC", "T_PC_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_PC_HB"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGR"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSBGR"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRXFGB"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRXFGB"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRXFGB"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRZM"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRZM"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRZM"));
		set.add(new Tuple<String, String>("K_XS", "B_XSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSCQJF"));
		set.add(new Tuple<String, String>("K_XS", "B_XSFDMSDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSFDMSDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSFDMSDSR"));
		set.add(new Tuple<String, String>("K_XS", "B_XSKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSKCSX"));
		set.add(new Tuple<String, String>("K_XS", "B_XSQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSQZCS"));
		set.add(new Tuple<String, String>("K_XS", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("K_XS", "B_XSYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSYCSX"));
		set.add(new Tuple<String, String>("K_XS", "B_XSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSZJJH"));
		set.add(new Tuple<String, String>("K_XS", "B_XSZSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSZSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSZSR"));
		set.add(new Tuple<String, String>("K_XS", "T_XS_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_XS_HB"));
		set.add(new Tuple<String, String>("K_XS", "T_XSFDMS"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSFDMS"));
		set.add(new Tuple<String, String>("K_ZS", "T_XSFDMS"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZBGXZXW"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZBGXZXW"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZBGXZXW"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZCQJF"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZDSR"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZKCSX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZKT"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZQZCS"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZSFJN"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZSFJN"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZSFJN"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZYCSX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZZJJH"));
		set.add(new Tuple<String, String>("K_XZ", "T_XZ_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_XZ_HB"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZXDSR"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXKCSX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXKT"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXQZCS"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXYAJ"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXYAJ"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXYAJ"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXYCSX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXZXFJN"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXZXFJN"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXZXFJN"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXZXZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("K_ZX", "T_HFZX_YZXAJ"));
		set.add(new Tuple<String, String>("K_ZS", "T_HFZX_YZXAJ"));
		set.add(new Tuple<String, String>("K_ZX", "T_ZX_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_ZX_HB"));
		set.add(new Tuple<String, String>("K_ZX", "T_ZXXX_ZXDB"));
		set.add(new Tuple<String, String>("K_ZS", "T_ZXXX_ZXDB"));

		for(Tuple<String, String> t : set){
			System.out.println(t.toString());
		}
	}
	
	@org.junit.Test
	public void find() throws IOException{
//		String s = "com.thunisoft.fy.support.wssy.wsjz.autocheck.ws.db.hbm.cass";
//		String s = "com.thunisoft.fy.spxt.bean.fy0.gd";
		String s = "extends EntityZxYaj";
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resourceArray = resolver
				.getResources("file:D:/MyWorkspace10/fycommon/**/*.java");
		for(Resource r : resourceArray){
			List<String> lines = FileUtils.readLines(r.getFile());
			for(String line : lines){
				if(line.contains(s)){
					System.out.println(r.getFile().getAbsoluteFile());
					break;
				}
			}
		}
		
	}

	private static class Tuple<T1, T2> {

		private final T1 value1;
		private final T2 value2;

		public Tuple(T1 value1, T2 value2) {
			this.value1 = value1;
			this.value2 = value2;
		}

		public T1 getValue1() {
			return value1;
		}

		public T2 getValue2() {
			return value2;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else {
				if (this.getClass().equals(obj.getClass())) {
					@SuppressWarnings("rawtypes")
					Tuple another = (Tuple) obj;
					if (this.value1.equals(another.getValue1())
							&& this.value2.equals(another.getValue2())) {
						return true;
					}
				}
				return false;
			}

		}

		@Override
		public int hashCode() {
			return this.value1.hashCode() ^ this.value2.hashCode();
		}

		@Override
		public String toString() {
			return "Tuple [value1=" + value1 + ", value2=" + value2 + "]";
		}
	}
	@org.junit.Test
	public void test() throws IOException{
		Set<Tuple<String, String>> set = new HashSet<Tuple<String, String>>();

		set.add(new Tuple<String, String>("K_MS", "B_MS"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MS"));
		set.add(new Tuple<String, String>("DB_WL", "T_MS"));
		set.add(new Tuple<String, String>("K_ZS", "B_MS"));
		set.add(new Tuple<String, String>("K_ZS", "B_MS"));
		set.add(new Tuple<String, String>("K_MS", "B_MS"));
		set.add(new Tuple<String, String>("DB_GD", "B_MS"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZX"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZX"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZ"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZ"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZ"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZ"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZ"));
		set.add(new Tuple<String, String>("K_XS", "B_XS"));
		set.add(new Tuple<String, String>("DB_WL", "T_XS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XS"));
		set.add(new Tuple<String, String>("K_XS", "B_XS"));
		set.add(new Tuple<String, String>("DB_GD", "B_XS"));
		set.add(new Tuple<String, String>("K_PC", "B_PC"));
		set.add(new Tuple<String, String>("DB_WL", "T_PC"));
		set.add(new Tuple<String, String>("K_ZS", "B_PC"));
		set.add(new Tuple<String, String>("K_PC", "B_PC"));
		set.add(new Tuple<String, String>("DB_GD", "B_PC"));
		set.add(new Tuple<String, String>("K_MS", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSCQJF"));
		set.add(new Tuple<String, String>("K_MS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_ZS", "B_MSDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSDSR"));
		set.add(new Tuple<String, String>("K_MS", "B_MSGSCG"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSGSCG"));
		set.add(new Tuple<String, String>("K_MS", "B_MSKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSKCSX"));
		set.add(new Tuple<String, String>("K_MS", "B_MSKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSKT"));
		set.add(new Tuple<String, String>("K_MS", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_MSSPZZCY"));
		set.add(new Tuple<String, String>("K_MS", "B_MSYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSYCSX"));
		set.add(new Tuple<String, String>("K_MS", "B_MSZJJH"));
		set.add(new Tuple<String, String>("DB_LASC", "T_MSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_MSZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_MSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "B_MSZJJH"));
		set.add(new Tuple<String, String>("K_MS", "T_MS_HB"));
		set.add(new Tuple<String, String>("DB_WL", "T_MS_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_MS_HB"));
		set.add(new Tuple<String, String>("K_MS", "T_MSGXYY"));
		set.add(new Tuple<String, String>("K_ZS", "T_MSGXYY"));
		set.add(new Tuple<String, String>("K_PC", "B_PCCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCCQJF"));
		set.add(new Tuple<String, String>("K_PC", "B_PCDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCDSR"));
		set.add(new Tuple<String, String>("K_PC", "B_PCKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCKCSX"));
		set.add(new Tuple<String, String>("K_PC", "B_PCKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCKT"));
		set.add(new Tuple<String, String>("K_PC", "B_PCQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCQZCS"));
		set.add(new Tuple<String, String>("K_PC", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_PCSPZZCY"));
		set.add(new Tuple<String, String>("K_PC", "B_PCSSDLR"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCSSDLR"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCSSDLR"));
		set.add(new Tuple<String, String>("K_PC", "B_PCYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCYCSX"));
		set.add(new Tuple<String, String>("K_PC", "B_PCZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_PCZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_PCZJJH"));
		set.add(new Tuple<String, String>("K_PC", "T_PC_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_PC_HB"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGR"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSBGR"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRLXQJ"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRXFGB"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRXFGB"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRXFGB"));
		set.add(new Tuple<String, String>("K_XS", "B_XSBGRZM"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSBGRZM"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSBGRZM"));
		set.add(new Tuple<String, String>("K_XS", "B_XSCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSCQJF"));
		set.add(new Tuple<String, String>("K_XS", "B_XSFDMSDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSFDMSDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSFDMSDSR"));
		set.add(new Tuple<String, String>("K_XS", "B_XSKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSKCSX"));
		set.add(new Tuple<String, String>("K_XS", "B_XSQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSQZCS"));
		set.add(new Tuple<String, String>("K_XS", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_XSSPZZCY"));
		set.add(new Tuple<String, String>("K_XS", "B_XSYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSYCSX"));
		set.add(new Tuple<String, String>("K_XS", "B_XSZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSZJJH"));
		set.add(new Tuple<String, String>("K_XS", "B_XSZSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSZSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XSZSR"));
		set.add(new Tuple<String, String>("K_XS", "T_XS_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_XS_HB"));
		set.add(new Tuple<String, String>("K_XS", "T_XSFDMS"));
		set.add(new Tuple<String, String>("DB_WL", "T_XSFDMS"));
		set.add(new Tuple<String, String>("K_ZS", "T_XSFDMS"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZBGXZXW"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZBGXZXW"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZBGXZXW"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZCQJF"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZCQJF"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZCQJF"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZCQJF"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZDSR"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZKCSX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZKT"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZQZCS"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZSFJN"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZSFJN"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZSFJN"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZSPZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_XZSPZZCY"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZYCSX"));
		set.add(new Tuple<String, String>("K_XZ", "B_XZZJJH"));
		set.add(new Tuple<String, String>("DB_WL", "T_XZZJJH"));
		set.add(new Tuple<String, String>("K_ZS", "B_XZZJJH"));
		set.add(new Tuple<String, String>("K_XZ", "T_XZ_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_XZ_HB"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXDSR"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXDSR"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXDSR"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZXDSR"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXKCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXKCSX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXKT"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXKT"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXQZCS"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXQZCS"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXYAJ"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXYAJ"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXYAJ"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXYCSX"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXYCSX"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXZXFJN"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXZXFJN"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXZXFJN"));
		set.add(new Tuple<String, String>("K_ZX", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("DB_WL", "T_ZXZXZZCY"));
		set.add(new Tuple<String, String>("K_ZS", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("DB_GD", "B_ZXZXZZCY"));
		set.add(new Tuple<String, String>("K_ZX", "T_HFZX_YZXAJ"));
		set.add(new Tuple<String, String>("K_ZS", "T_HFZX_YZXAJ"));
		set.add(new Tuple<String, String>("K_ZX", "T_ZX_HB"));
		set.add(new Tuple<String, String>("K_ZS", "T_ZX_HB"));
		set.add(new Tuple<String, String>("K_ZX", "T_ZXXX_ZXDB"));
		set.add(new Tuple<String, String>("K_ZS", "T_ZXXX_ZXDB"));
		
		
		List<Tuple<String, String>> list = new ArrayList<Tuple<String, String>>(set);
		List<String> tags = FileUtils.readLines(FileUtils.getFile("D:", "MyWorkspace10", "laxt", "src", "config", "topicConfig", "laxt-topic-config.xml"));
		Set<Tuple<String, String>> setTag = new HashSet<Tuple<String, String>>();
		
		
		for(String t : tags){
			if(t.contains("<table name=")){
				String table = StringUtils.substringBetween(t, "\"");
				String db = StringUtils.substringBetween(StringUtils.substringAfter(t, "db"), "\"");
				
				Tuple<String, String> dbtable = new Tuple<String, String>(db, table);
				setTag.add(dbtable);
				
//				if(!list.contains(dbtable)){
//					System.out.println(t.trim());
//					System.out.println(dbtable);
//				}
			}
		}
		List<Tuple<String, String>> listTag = new ArrayList<Tuple<String, String>>(setTag);
		
		String template = "<tag id=\"0002_0%1$3d\"><tables><table name=\"%2$s\" db=\"%3$s\" hpk=\"\" spk=\"\" /></tables><services><service name=\"commonSaveOrUpdateChain\" /></services></tag>";
		int id = 195;
		
		@SuppressWarnings("unchecked")
		List<Tuple<String, String>> rs = ListUtils.subtract(list, listTag);
		System.out.println(list.size());
		System.out.println(listTag.size());
		for(Tuple<String, String> t : rs){
			System.out.println(String.format(template, + ++id, t.getValue2(), t.getValue1()));
		}
		
		
		System.out.println("-------------------------------");
		@SuppressWarnings("unchecked")
		List<Tuple<String, String>> rs2 = ListUtils.subtract(listTag, list);
		for(Tuple<String, String> t : rs2){
			System.out.println(t);
		}

	}
	
	@org.junit.Test
	public void test2() throws IOException{
//		"repository/eform/视说诉讼/视频申请/其他视频直播申请一级导航.form.xml",
//		"repository/eform/视说诉讼/视频申请/申请一级导航.form.xml"
		
//		"repository/eform/视说诉讼/审核人/其他视频审核二级导航.form.xml",
//		"repository/eform/视说诉讼/审核人/审核人一级导航.form.xml",
		
//		"repository/eform/个人发布/节点公开二级导航.form.xml",
		
//		"repository/eform/个人发布/文书公开.form.xml",

//		String filePath = "审核人";
//		filePath = new String(filePath.getBytes("utf8"), "gbk");
//		System.out.println(filePath);
		
	}
	private static  <T>boolean isEquals(T target, T...range){
    	Arrays.sort(range);
    	return Arrays.binarySearch(range, target) >= 0;
    }
	

}
