package pattern.belong.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * 观察者模式
 * 定义：
 * 解除了观察者和目标之间的耦合关系。目标不需要知道它的观察者的任何关系
 * 相反，目标只是允许观察者订阅事件。当目标产生一个事件时，它简单地将事件传给
 * 每一个观察者。
 * 简单地说，观察者模式定义了一个一对多的依赖关系，
 * 让一个或多个观察者对象监察一个主题对象。这样一个主题对
 * 象在状态上的变化能够通知所有的依赖于此对象的那些观察者
 * 对象，使这些观察者对象能够自动更新。
 * @author Belong
 * 优点：
 * 1.观察者模式在被观察者之间建立一个抽象的耦合。
 * 被观察者角色所知道的只是一个具体观察者的列表，每一个
 * 具体观察者都符合一个抽象观察者的的接口。被观察者并不认识任何
 * 一个具体的观察者，它只知道它们都有一个公共的接口（update）。
 * 由于被观察者和观察者没有紧密的耦合在一起，因此它们可以属于不同的抽象化层次
 * 如果被观察者和观察者都被扔到一起，那么这个对象必然跨越抽象化和具体化层次
 * 2.观察者模式支持广播通讯。被观察者会向所有的登记过的观察者发出通知。
 * 缺点：
 * 1.如果一个观察者对象有很多的直接和间接的观察者的化，将所有的观察者都通知到会花费很多时间。
 * 2.如果在被观察者之间有循环依赖的花，被观察者会触发它们之间进行循环调用，导致系统崩溃。
 * 在使用观察者模式是要特别注意这一点。
 * 3.如果对观察者的通知是通过另外的线程进行异步投递的话，系统必须保证投递是以自洽的方式进行的。
 * 4.虽然观察者模式可以随时使观察者知道所观察的对象发生了变化，但是观察者模式没有相应的机制是观察者
 * 知道所观察的对象是怎么发生变化的，仅仅知道发生了什么变化而已。
 */
public class Oberver_Pathern {
	public static void main(String[] args) {
		ConcreteSubject c =	new ConcreteSubject();
		c.attach(new ConcreteObserver(c,"maik"));
		c.attach(new ConcreteObserver(c,"lileli"));
		c.attach(new ConcreteObserver(c,"hah"));
		c.setMessage("美女...");
		c.Notify();		
	}
}
//抽象主题角色：负责记录订阅者，和通知订阅者
class Subject {
	List<Observer> observers = new ArrayList<Observer>();
	//加入
	public void attach(Observer o) {
		observers.add(o);
	}
	//删除
	public void delete(Observer o) {
		observers.remove(o);
	}
	//通知函数(循环链表（订阅者名单）去更新主题)
	public void Notify() {
		for (Observer o : observers) {
			o.update();//抽象的耦合
		}
	}
}
//具体主题
class ConcreteSubject extends Subject {
	private String message;//具体的主题

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
//抽象观察者：可以更新自己的主题
abstract class Observer {
	//更新主题方法
	public abstract void update();
}
//具体的观察者：
class ConcreteObserver extends Observer{
	private ConcreteSubject sub;//订阅的具体主题
	private String name;//观察者的名字
	public ConcreteObserver(ConcreteSubject sub,String name){
		this.sub = sub;
		this.name = name;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("我是："+this.name+" 我关注了 "+this.sub.getMessage()+"主题");
	}	
}
