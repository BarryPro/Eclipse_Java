package pattern.belong.Test;
/**
 * 装饰者模式
 * 定义：在不必改变原类文件和使用继承的情况下，动态地扩展一个对象
 * 的功能。它是通过创建一个包装对象，也就是装饰来包裹真是的对象。
 * @author belong
 * 应用场景：
 * 1.想透明并且动态地给对象增加新的职责的时候。
 * 2.给对象增加的职责，在未来存在增加或减少可能。
 * 3.用继承扩展功能不太现实的情况下，应该考虑用组合的方式
 * 缺点:1.这种比继承更加灵活机动的特性，也同时意味着更加多的复杂性
 *    	2.装饰模式会导致设计中出现许多小类，如果过度使用，会使程序变得很复杂。
 *      3.装饰模式是针对抽象组件（Component）类型编程。但是，如果你要
 *      针对具体组件编程时，就应该和重新思考你的应用架构，以及装饰者是否合适。
 *      当然也可以改变Component接口，增加新的公开的行为，实现“半透明”的装饰模式
 *      在实际项目中要做出最佳选择。
 */
public class Decorator_Pattern {
	public static void main(String[] args) {
		ChineseGirl c = new ChineseGirl();
		c.getDeco();
		System.out.println(new AmericanGirl().getDeco());
		System.out.println(new Science(c).getDeco());
		System.out.println(new Art(c).getDeco());
		
	}
}
//抽象构件角色：定义一个抽象接口，来规范准备附加功能的类
abstract class Girl{
	private String deco = "我是个平凡的女孩";
	public String getDeco(){
		return deco;
	}
	public void setDeco(String deco){
		this.deco = deco;
	}
}
//抽象装饰者角色：持有对具体构件角色的引用并定义与抽象构件角色一致的接口
abstract class GirlDecorator extends Girl{
	//重新抽象概括
	public abstract String getDeco();
}

class ChineseGirl extends Girl{
	
	public ChineseGirl(){
		super.setDeco(super.getDeco()+"中国的：");
	}
}

class AmericanGirl extends Girl {
	public AmericanGirl(){
		super.setDeco(super.getDeco()+"美国的：");
	}
}
//具体装饰类：实现抽象装饰者角色，负责为具体构件添加额外功能
class Art extends GirlDecorator{	
	private Girl girl ;
	public Art(Girl girl){
		this.girl = girl;
	}
	@Override
	public String getDeco() {
		// TODO Auto-generated method stub
		return girl.getDeco()+"我会艺术";
	}
	
}

class Science extends GirlDecorator{
	private Girl girl ;
	public Science(Girl girl){
		this.girl = girl;
	}	
	@Override
	public String getDeco() {
		// TODO Auto-generated method stub
		return girl.getDeco()+"我会科学";
	}
	
}