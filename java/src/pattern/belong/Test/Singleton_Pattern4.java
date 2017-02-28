package pattern.belong.Test;
/**
 * 单子模式 第四种 （双重检查锁定版）
 * @author belong
 * (推荐第四种写法)
 */
public class Singleton_Pattern4 {
	//1.私有构造器
	private Singleton_Pattern4(){
		
	}
	//2.通过volatile来实现静态的本类对象
	//volatile关键字只是jvm保证从主存中读到线程中的值是最新的值
	private volatile static Singleton_Pattern4 sp;
	//3.在静态实例化方法中实现同步
	public static Singleton_Pattern4 getInstance(){
		synchronized(Singleton_Pattern4.class){
			if(sp == null){
				sp = new Singleton_Pattern4();
			}
		}
		return sp;
	}
}
