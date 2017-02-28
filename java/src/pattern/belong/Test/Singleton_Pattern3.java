package pattern.belong.Test;
/**
 * 单子模式 第三种
 * @author belong
 * 通过内部类实现单子模式实质是（classloader的机制来保证实例化instance时只有一个线程）
 * 该种方式是Singleton类被加载了但instance不一定被初始化。因为SingletonHolder没有被
 * 主动使用，只有显示的调用getInstance sp才会被实例化
 */
public class Singleton_Pattern3 {
	//1.定义一个私有静态内部类在内部类里面实现初始化单子类
	private static class Singleton_PatternHolder{
		private final static Singleton_Pattern3 sp = new Singleton_Pattern3();
	}
	//2.通过静态初始化方法返回内部类所创建的对象
	public static Singleton_Pattern3 getInstance(){
		return Singleton_PatternHolder.sp;
	}
}
