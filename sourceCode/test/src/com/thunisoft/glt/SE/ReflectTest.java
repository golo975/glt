package com.thunisoft.glt.SE;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class ReflectTest {

	@Test
	public void from(){
		DbTableBean d1 = null;
		DbTableBean d2 = null;
		String[] dbArray = {"a", "b", "c", "d", "e"};
		
		Random r = new Random(48);
		
		for(byte b : "abcdefgllkdkfj;lskf;lsk".getBytes()){
//			System.out.println("" + r.nextInt(2) + r.nextInt(5));
			d1 = new DbTableBean(APP_TYPE.values()[r.nextInt(2)], dbArray[r.nextInt(5)], dbArray[r.nextInt(5)]);
			d2 = new DbTableBean(APP_TYPE.values()[r.nextInt(2)], dbArray[r.nextInt(5)], dbArray[r.nextInt(5)]);
			if(d1.equals(d2)){
				System.out.println(d1);
				System.out.println(d2);
			}
		}
	}
	
	
	private static enum APP_TYPE{
		SPXT,
		LAXT;
	}
	private static class DbTableBean{
		private APP_TYPE appType;
		private String db;
		private String table;
		public DbTableBean(APP_TYPE appType, String db, String table){
			this.appType = appType;
			this.db = db;
			this.table = table;
		}
		public APP_TYPE getAppType() {
			return appType;
		}
		public String getDb() {
			return db;
		}
		public String getTable() {
			return table;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof DbTableBean) {
				DbTableBean anotherDTB = (DbTableBean) obj;
				return this.appType.equals(anotherDTB.getAppType())
						&& this.db.equals(anotherDTB.getDb())
						&& this.table.equals(anotherDTB.getTable());
			}
			return false;
		}
		@Override
		public int hashCode() {
			// TODO 这里需要重写hashCode by gaolong
			return super.hashCode();
		}
		@Override
		public String toString() {
			return super.toString() + " appType:" + this.appType + ", db:" + this.db + ", table:" + this.table;
		}
	}
}
