package main.java.com.glt.webtest.SE;

import java.util.Observable;
import java.util.Observer;

public class ObserverPatternTest {
	
	public static void main(String[] args) {
		MySubject boss = new MySubject();
		new MyObserver(boss);
		new MyObserver(boss);
		new MyObserver(boss);
		boss.setChanged();
		boss.notifyObservers("dead");
//		boss.setChanged();
		boss.notifyObservers("ok");
	}

	private static class MySubject extends Observable{
		@Override
		protected synchronized void setChanged() {
			super.setChanged();
		}
	}
	private static class MyObserver implements Observer{
		public MyObserver(MySubject subject){
			subject.addObserver(this);
		}
		@Override
		public void update(Observable o, Object arg) {
			System.out.println("boss is " + arg.toString());
		}
		
	}
}
