package pattern.belong.Test;
/**
 * 抽象工厂模式（面向产品族）
 * 是指当有多个抽象角色时，使用的一种工厂模式。
 * 抽象工厂模式可以向客户端提供一个接口，使客户
 * 端在不必指定产品的具体情况下，创建多个产品
 * 族中的产品对象。
 * @author belong
 * 优点：针对产品族而生的，增加产品线很容易；
 * 缺点：无法增加新产品；
 */
public class Abstract_Factory {
	public static void main(String[] args) {
		AbstractProduceA pa1 = new ConcreteFactory1().createProduceA();
		AbstractProduceB pb1 = new ConcreteFactory1().createProduceB();
		AbstractProduceA pa2 = new ConcreteFactory2().createProduceA();
		AbstractProduceB pb2 = new ConcreteFactory2().createProduceB();
		pa1.make();
		pa2.make();
		pb1.make();
		pb2.make();
	}
}
//抽象工厂：可以创建多个产品族中的产品对象
interface AbstractFactory {
	public AbstractProduceA createProduceA();
	public AbstractProduceB createProduceB();
}

//具体工厂：创建具体的产品
class ConcreteFactory1 implements AbstractFactory {
	@Override
	public AbstractProduceA createProduceA() {
		// TODO Auto-generated method stub
		return new ProduceA1();
	}
	@Override
	public AbstractProduceB createProduceB() {
		// TODO Auto-generated method stub
		return new ProduceB1();
	}
}
class ConcreteFactory2 implements AbstractFactory {
	@Override
	public AbstractProduceA createProduceA() {
		// TODO Auto-generated method stub
		return new ProduceA2();
	}

	@Override
	public AbstractProduceB createProduceB() {
		// TODO Auto-generated method stub
		return new ProduceB2();
	}
}

//抽象产品：所有具体产品必须继承它
abstract class AbstractProduceA {
	public abstract void make();
}
abstract class AbstractProduceB {
	public abstract void make();
}

//具体产品：生产具体的商品
class ProduceB1 extends AbstractProduceB {
	@Override
	public void make(){
		System.out.println("ProduceB1");
	}
}
class ProduceA1 extends AbstractProduceA {
	@Override
	public void make(){
		System.out.println("ProduceA1");
	}
}
class ProduceB2 extends AbstractProduceB {
	@Override
	public void make(){
		System.out.println("ProduceB2");
	}
}
class ProduceA2 extends AbstractProduceA {
	@Override
	public void make(){
		System.out.println("ProduceA2");
	}
}
