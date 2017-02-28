package pattern.belong.Test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/** 
 * cglib代理（核心：MethodInterceptor.intercept）
 * @author belong
 * jdk与cglib的比较：
 * JDK的动态代理机制只能代理实现了接口的类，
 * 而不能实现接口的类就不能实现JDK的动态代理，
 * cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，
 * 并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 * 缺点： 不能对final修饰的类进行代理
 */
public class Proxy_Pattern_cglib {
	public static void main(String[] args) {
		BookFacadeCglib b = new BookFacadeCglib();
		BookFacadeImpll bookimpl = (BookFacadeImpll)b.getInstance(new BookFacadeImpll());
		bookimpl.addBook();
	}
}
//没有用上只是和jdk代理做个对比
interface BookFacade{
	public void addBook();
}
/**
 * 没有实现BookFacade的方法
 */
class BookFacadeImpll{
	public void addBook(){
		System.out.println("增加图书的普通方法");
	}
}
/**
 * 核心原理：指定目标类实现它的子类
 */
class BookFacadeCglib implements MethodInterceptor{
	private Object target;
	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();//增强器类
		enhancer.setSuperclass(this.target.getClass());//指定目标类实现它的子类
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/**
	 * @param obj 被代理的类的对象
	 * @param method 被代理类中的方法
	 * @param args 被代理类的方法调用时所需要的参数
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("starting");
		proxy.invokeSuper(obj, args);
		System.out.println("the end");
		return null;
	}
	
}