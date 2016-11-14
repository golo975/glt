package main.java.com.glt.webtest.SE;

/**
 * ���Զ�·����
 * java֧�ֶ�̬�ĵ����ɺ;�̬�Ķ����
 * ��̬�ĵ����ɼ���̬
 * ��̬�Ķ���ɼ�����������
 * ���Զ��ʹ�ö�̬�ĵ�����ʵ�ֶ�̬�Ķ����
 * ����˵�����ʹ�ö�̬�����ɡ�ָ����ʹ��Ҫ��̬ȷ���Ķ��������Ϊһ�ε����ߣ�
 * ����һ�Σ��Ϳ���ȷ��һ����
 * ��ȷ��һ��������ʱ�򣬶��ڲ�����ֻ�������侲̬���ͣ�Ҳ�������õ����ͣ����޷��������ʵ�����͡�
 * ���ܹ�ȷ��һ�������ʵ�����͵ģ�ֻ�ж�̬���ƣ�����Ҫʵ�ֶ�̬����ɣ�
 * ֻ�ܶ��ʹ�ö�̬���ƣ�ʹ�漰�Ķ���ֱ���Ϊһ�η����ĵ����ߣ�������һ�ζ�̬����
 * ��̬��ַ���Ӧ�ó����ǣ���Ҫͨ�������Ĳ�ͬ������������ͬ�Ĵ����߼���
 * �������˼·��ʹ��instanceOf���жϳ������ʵ�����ͣ�Ȼ��������ͬ�Ĵ���
 * ������ȱ���ǿ���ʹ��������߳����������Ķ���
 * ����̬����ɵ�ȱ���ǲ���ֱ�ۣ���Ҫ�Ƚ���ѧϰ���˽⡣
 * ������Ϊ�������࣬�������Food�࣬���ļ̳���ϵ�е�ÿһ�����Ͷ���Ҫ�ڵ����ߣ��������Biont�࣬����һ����Ӧ�Ĵ�������
 * �����Ļ������Food�ļ̳�ϵͳ�����仯�Ļ����������еĵ����߶���Ҫ�޸ġ�
 * ���ԣ����Ҫʹ�ö�·���ɣ���Visitorģʽ������Ҫ��֤��Ϊ�����ļ̳���ϵ�ǳ��ȶ���������Ҫ�����䶯��
 * ������Ա�֤��һ�㣬�������Ҫ�����һ������ߵĻ����ͺ������ˣ�ֻ��Ҫֱ����Ӽ��ɣ���ԭ�е������඼��Ҫ���Ӱ�졣
 * Visitorģʽ��һ�����������ģʽ����Ϊ����ȷ���ǳ����ԡ�
 * �����java��ģʽ��
 * @author gaolong
 *
 */
public class MultiAssign {
	public static void main(String[] args) {
		Biont h = new Human();
		Food m = new Meat();
		Food v = new Vegetable();
		
		h.eat(m);
		h.eat(v);
		
		h = new Animal();
		System.out.println("--------");
		
		h.eat(m);
		h.eat(v);
	}
	
	/**
	 * �ӿ�Ҳ���Զ���Ϊ˽�еģ�����˽�о�̬��Ա������һ�¡�
	 * @author gaolong
	 *
	 */
	private interface Biont{
		void eat(Food f);
		void eatMeat(Meat m);
		void eatVegetable(Vegetable v);
	}
	
	private interface Food{
		void accept(Biont b);
	}
	
	/**
	 * ��̬��Ա���������Ϊ˽�еģ�����������̬��Ա������һ�¡�
	 * @author gaolong
	 *
	 */
	private static class Human implements Biont{ 
		@Override
		public void eat(Food f) {
			f.accept(this);
		}
		@Override
		public void eatMeat(Meat m){
			System.out.println("���ڳ�");
			m.meatBusiness();
		}
		@Override
		public void eatVegetable(Vegetable v){
			System.out.println("���ڳ�");
			v.vegetableBusiness();
		}
	}
	private static class Animal implements Biont{
		@Override
		public void eat(Food f) {
			f.accept(this);
		}
		@Override
		public void eatMeat(Meat m){
			System.out.println("�����ڳ�");
			m.meatBusiness();
		}
		@Override
		public void eatVegetable(Vegetable v){
			System.out.println("�����ڳ�");
			v.vegetableBusiness();
		}
	}
	private static class Meat implements Food{
		/**
		 * ҵ�񷽷�������������������ģ�Meat �� Vegetable ��ҵ�񷽷�ôʲô��ϵ��
		 */
		public void meatBusiness(){
			System.out.println("�ⱻ����");
		}

		@Override
		public void accept(Biont b) {
			b.eatMeat(this);
		}
	}
	private static class Vegetable implements Food{
		public void vegetableBusiness(){
			System.out.println("�߲˱�����");
		}

		@Override
		public void accept(Biont b) {
			b.eatVegetable(this);
		}
	}
	
}
