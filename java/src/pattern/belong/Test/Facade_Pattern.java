package pattern.belong.Test;
/**
 * 门面模式（外观模式）
 * 简而言之：就是把一堆复杂的流程封装成一个接口供给用
 * 户更简单的使用。
 * 1.作用：
 * 一般而言，Facade模式是为了降低子系统之间，客户端
 * 与实现化层之间的依赖性。当在构建一个层次化的系统
 * 时，也可以通过使用Facade模式定义系统中每一层的入
 * 口，从而简化层与层之间的依赖性。
 * 2.定义：
 * 外部与子系统的通信必须通过一个统一的门面对象进行。
 * 门面模式提供一个高层的接口，使得子系统更容易使用。
 * 每一个子系统只有一个门面类，而且此门面类只有一个
 * 实例，也就是说它是一个单例模式。但整个系统可以有
 * 多个门面类
 * @author belong *
 */
public class Facade_Pattern {
	public static void main(String[] args) {//客户角色
		new B().show();//统一的门面对象
	}
}
class B{// 门面角色（核心、门面类）：统一的门面对象调用（高层接口）
	public void show(){
		new A0().show();
		new A1().show();
		new A2().show();
	}
}
class A0{//客户角色 子系统 （门面类） 只有一个实例
	public void show(){
		System.out.println("A0");
	}
}
class A1{//客户角色 子系统（门面类） 只有一个实例
	public void show(){
		System.out.println("A1");
	}
}
class A2{//客户角色 子系统（门面类） 只有一个实例
	public void show(){
		System.out.println("A2");
	}
}