package pattern.belong.Test;
/**
 * 静态代理模式
 * @author belong
 * 优点:代理使客户端不需要知道实现类是什么，怎么做的，
 * 而客户端只需要知道代理即可（解耦合）
 * 缺点:代理的东西一旦增多 代理类会成倍增加
 * 1）代理类和委托类实现了相同的接口，代理类通过委托类实现
 * 了相同的方法。这样就出现了大量的代码重复。如果接口增加
 * 一个方法，除了所有实现类需要实现这个方法外，所有代理类
 * 也需要实现此方法。增加的代码维护的复杂度。
 * 2）代理对象只服务于一种类型的对象，如果要服务多类型的对象
 * 势必要为每一个对象都进行代理，静态代理在程序规模稍大时就
 * 无法胜任了。如上的代码是只为Subject类的访问提供了代理，但
 * 是如果还要为其他类如Department类提供代理的话，就需要我们再次
 * 添加代理Department的代理类。
 */
public class Proxy_Pattern_Static {
	public static void main(String[] args) {
		ISubject sub = new RealSubject();
		Proxy p = new Proxy(sub);
		p.print();
		p.say();
	}
}
interface ISubject {
	public void print();
	public void say();
}
class RealSubject implements ISubject{

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("我说：在吗？");
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("我说：我是***");
	}
	
}
/**
 * 代理器
 * 
 */
class Proxy implements ISubject {
	private ISubject sub;
	public Proxy(ISubject sub){
		this.sub = sub;
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		sub.print();
		System.out.println("代理器说：滚");
	}
	@Override
	public void say() {
		// TODO Auto-generated method stub
		sub.say();
		System.out.println("代理器说：原来是你呀！");
	}
	
}
