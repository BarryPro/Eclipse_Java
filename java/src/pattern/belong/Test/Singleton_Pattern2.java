package pattern.belong.Test;
/**
 * 单子模式 第二种
 * @author belong
 * 比第一种多个同步（synchronized）
 * 优点：可以很好的在多线程中工作
 * 缺点：效率低，99%的情况下不需要同步
 */ 
public class Singleton_Pattern2 {
	//1.私有构造器
	private Singleton_Pattern2(){
		
	}
	//2.定义一个本类的对象用于存放对象
	private static Singleton_Pattern2 sp;
	//3.同步静态实例化方法
	public synchronized static Singleton_Pattern2 getInstance(){		
		if(sp == null){
			sp = new Singleton_Pattern2();
		}
		return sp;
	}
}
