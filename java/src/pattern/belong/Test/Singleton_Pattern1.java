package pattern.belong.Test;
/**
 * 单子模式 第一种
 * @author belong
 * 就是一个类只能存放在一个对象实例，并且该类提供了一个取其
 * 对象实例的方法
 * 缺陷：在多线程中不能正常工作。
 */
public class Singleton_Pattern1 {
	// 1.私有的构造器
	private Singleton_Pattern1() {

	}

	// 2.一个私有的自己的类对象用于存放对象
	private static Singleton_Pattern1 sp;

	// 3.静态实例化方法
	public static Singleton_Pattern1 getInstance() {
		if (sp == null) {
			sp = new Singleton_Pattern1();
		}
		return sp;
	}
}
