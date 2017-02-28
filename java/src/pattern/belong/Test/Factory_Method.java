package pattern.belong.Test;
/**
 * 工厂方法模式
 * 是简单工厂方法模式的衍生，它的核心工厂类不再负责
 * 产品的创建，核心类成为一个抽象工厂角色，仅负责具
 * 体工厂子类必须实现的接口，使得系统可以在不修改具
 * 体工厂角色的情况下引用新的产品。
 * 特点：一个抽象工厂对应一个抽象产品，一个具体工厂
 * 对应一个具体的产品。
 * @author belong
 * 优点：可以生产任意产品
 * 缺点：同一等级的固定产品
 */
public class Factory_Method {
	public static void main(String[] args) {
		Creator creator1 = new BulbCreator();//不同的工厂实例 来实现不同的产品实例
		Creator creator2 = new TubeCreator();
		
		creator1.factory().turnon();
		creator1.factory().turndown();
		creator2.factory().turnon();		
		creator2.factory().turndown();
	}
}
//抽象工厂角色：（核心）任何在模式中创建的对象
//的工厂必须实现这个接口
interface Creator {
	public Light factory();

}
//抽象产品：产品对象的共同父类或共同拥有的接口
interface Light{
	public void turndown();
	public void turnon();
}
//具体工厂：实现抽象工厂接口的具体工厂类，包含
//应用程序的逻辑
class BulbCreator implements Creator{
	@Override
	public Light factory() {
		// TODO Auto-generated method stub
		return new BulbLight();//具体工厂对应具体的产品
	}	
}
class TubeCreator implements Creator{
	@Override
	public Light factory() {
		// TODO Auto-generated method stub
		return new TubeLight();
	}	
}
//具体产品：实现了抽象产品角色定义的接口
//其具体产品由专门的具体工厂创建
class BulbLight implements Light{
	@Override
	public void turndown() {
		// TODO Auto-generated method stub
		System.out.println("蓝灯down");
	}
	@Override
	public void turnon() {
		// TODO Auto-generated method stub
		System.out.println("蓝灯on");
	}
}
class TubeLight implements Light{
	@Override
	public void turndown() {
		// TODO Auto-generated method stub
		System.out.println("Tubedown");
	}
	@Override
	public void turnon() {
		// TODO Auto-generated method stub
		System.out.println("Tubeon");
	}
}