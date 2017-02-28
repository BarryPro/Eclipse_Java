package pattern.belong.Test;
/**
 * 简单工厂模式（Simple Factory）
 * 又叫静态工厂模式
 * @author belong
 * 缺点：在工厂类中集中了所有实例的创建逻辑，违反
 * 了高内聚责任分配原则，将全部创建逻辑集中到了一个
 * 工厂类中；对系统的维护和扩展不利，无法增加新产品；
 */
public class SimpleFactory_Pattern {
	public static void main(String[] args) {
		new Factory().make(1).make();
		new Factory().make(2).make();
	}
}
//抽象的产品：负责描述所有实例所共有的公共接口
interface IProduce {
	public void make();
}
//核心 ：负责实现创建所有实例的内部逻辑。
class Factory {
	public IProduce make(int no) {
		switch (no) {
		case 1:
			return new ProduceA();
		case 2:
			return new ProduceB();
		}
		return null;
	}
}
//具体产品：创建目标
class ProduceA implements IProduce {
	public ProduceA() {

	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		System.out.println("汉堡");
	}
}

class ProduceB implements IProduce {
	public ProduceB() {

	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		System.out.println("鸡腿");
	}
}