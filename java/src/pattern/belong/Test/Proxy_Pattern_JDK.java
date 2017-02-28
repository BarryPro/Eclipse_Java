package pattern.belong.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk动态代理（通过反射机制实现动态代理）
 * （核心：InvocationHandler.invoke）
 * @author belong
 * 优点：能够代理各种类型的对象
 * 接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理
 * （InvocationHandler.invoke）。这样，在接口方法数量比较多的时候
 * 我们就可以灵活的处理，而不需要像静态代理那样每一个方法进行中转
 * 而且动态代理的应用使我们的类职责更加单一，复用性更强。
 * 缺点：jdk动态代理依靠接口实现，如果类没有实现接口，就不能使用jdk代理了，要用cglib代理
 */
public class Proxy_Pattern_JDK {
	public static void main(String[] args) {
		IAnimal cat = new Cat();
		MyProxy p = new MyProxy(cat);
		Object obj = Proxy.newProxyInstance(cat.getClass().getClassLoader(),//得到运行时代理的类
				cat.getClass().getInterfaces(),//得到运行时要代理类实现的全部接口
				p);//自己定义的代理器必须实现Invocationhandler接口
		if(obj instanceof IAnimal){
			IAnimal c = (IAnimal)obj;
			c.say();
			c.action();
			c.drunk();
		}else {
			System.out.println("不是IAnimal 的子类");
		}
	}
}
/**
 * 代理类必须实现Invocationhandler 接口
 * @author belong
 * （jdk）动态代理
 */
class MyProxy implements InvocationHandler{
	private Object obj;
	public MyProxy(Object obj){
		this.obj = obj;
	}
	/**
	 * @param obj 指被代理的对象
	 * @param method 要调用的方法（被代理类的方法）
	 * @param args 方法调用时所需要的参数
	 */
	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("我是代理器：");//在核心方法实现之前要实现的功能
		return method.invoke(this.obj, args);//调用反射的方法
	}
	
}
/*
 *动物接口实现它的类必须实现它的方法
 */
interface IAnimal {
	public void say();
	public void action();
	public void drunk();
}
class Cat implements IAnimal{
	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("喵喵喵");
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("蹭蹭蹭爬");
	}
	public void drunk(){
		System.out.println("口渴了");
	}
	
}