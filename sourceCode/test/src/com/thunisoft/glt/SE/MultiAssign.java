package com.thunisoft.glt.SE;

/**
 * 测试多路分派
 * java支持动态的单分派和静态的多分派
 * 动态的单分派即多态
 * 静态的多分派即方法的重载
 * 可以多次使用动态的单分派实现动态的多分派
 * 这里说【多次使用动态单分派】指的是使需要动态确定的对象各自作为一次调用者，
 * 调用一次，就可以确定一个。
 * 在确定一个方法的时候，对于参数，只能依靠其静态类型，也就是引用的类型，而无法检查对象的实际类型。
 * 而能够确定一个对象的实际类型的，只有多态机制，所以要实现动态多分派，
 * 只能多次使用多态机制，使涉及的对象分别作为一次方法的调用者（即利用一次多态）。
 * 动态多分发的应用场景是，需要通过参数的不同类型来决定不同的处理逻辑。
 * 最基本的思路是使用instanceOf来判断出对象的实际类型，然后再做不同的处理。
 * 这样的缺点是可能使方法变得冗长而不易于阅读。
 * 而动态多分派的缺点是不够直观，需要先进行学习和了解。
 * 而且做为参数的类，即下面的Food类，他的继承体系中的每一种类型都需要在调用者，即下面的Biont类，中有一个对应的处理方法。
 * 这样的话，如果Food的继承系统发生变化的话，他的所有的调用者都需要修改。
 * 所以，如果要使用多路分派（即Visitor模式），需要保证作为参数的继承体系非常稳定，尽量不要发生变动。
 * 如果可以保证这一点，如果现在要新添加一类调用者的话，就很容易了，只需要直接添加即可，对原有的其他类都不要造成影响。
 * Visitor模式是一个很有争议的模式，因为他的确定非常明显。
 * 详见《java与模式》
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
	 * 接口也可以定义为私有的，他和私有静态成员的特性一致。
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
	 * 静态成员类可以设置为私有的，他和其他静态成员的特性一致。
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
			System.out.println("人在吃");
			m.meatBusiness();
		}
		@Override
		public void eatVegetable(Vegetable v){
			System.out.println("人在吃");
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
			System.out.println("动物在吃");
			m.meatBusiness();
		}
		@Override
		public void eatVegetable(Vegetable v){
			System.out.println("动物在吃");
			v.vegetableBusiness();
		}
	}
	private static class Meat implements Food{
		/**
		 * 业务方法，方法名可以是任意的，Meat 和 Vegetable 的业务方法么什么关系。
		 */
		public void meatBusiness(){
			System.out.println("肉被吃了");
		}

		@Override
		public void accept(Biont b) {
			b.eatMeat(this);
		}
	}
	private static class Vegetable implements Food{
		public void vegetableBusiness(){
			System.out.println("蔬菜被吃了");
		}

		@Override
		public void accept(Biont b) {
			b.eatVegetable(this);
		}
	}
	
}
