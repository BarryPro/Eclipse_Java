package pattern.belong.Test;
/**
 * 适配器模式
 * 定义：把一个类的接口变换成客户端所期待的另外一个接口
 * 使得原本由于接口不兼容而不能在一起工作的那些类可以一
 * 起工作
 * @author belong
 * 1.系统需要使用现有的类，而此类的接口不符合系统的需要
 * 2.想要建立一个可以重复使用的类，用于与一些彼此之间没
 * 有太大关联的一些类吗，包括一些可能在将来引进的类一起
 * 工作。这些源类不一定有复杂的接口
 * 3.（对对象适配器而言）在设计里，需要改变多个已有之类
 * 的接口，如果使用类的适配器模式，就要针对每一个子类做
 * 一个适配器，而这不太实际。
 */
public class Adapter_Pattern {
	public static void main(String[] args) {
		Target a = new Adapter();
		a.Request();
	}
}
//是客户接口
interface Target{
	public void Request();	
}
//被适配的接口适配器要实现它（要被人用的类）
class Adaptee{//原接口
	public void SpecificRequest(){
		System.out.println("我是三项插座");
	}
}
//适配器类
class Adapter extends Adaptee implements Target{
	private Adaptee adaptee = new Adaptee();//新建原接口类
	@Override
	public void Request() {
		// TODO Auto-generated method stub
		adaptee.SpecificRequest();//原接口调用原方法
	}
	
}