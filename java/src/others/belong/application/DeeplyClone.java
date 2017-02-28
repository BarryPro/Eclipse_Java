package others.belong.application;

public class DeeplyClone {
	public static void main(String[] args) {
		Dog dog = new Dog("黄狗",14);
		Cat cat = new Cat("黑猫",15);
		Animal1 a1 = new Animal1("小强",13,dog,cat);
		Animal1 a2 = (Animal1)a1.clone();//深克隆 a2与a1完全一样 对象也一样 
		System.out.println("改变之前 a1对象");
		System.out.println(a1.getName() +"   "+ a1.getAge() +"===="+a1.getDog().getName()+"   "+
				a1.getDog().getAge() +a1.getCat().getName()+""+a1.getCat().getAge());
		System.out.println("改变之前 a2对象");
		System.out.println(a2.getName() +"   "+ a2.getAge() +"===="+a2.getDog().getName()+"   "+
		a2.getDog().getAge());
		dog.setName("大黑");//改变狗的名字 和年龄
		dog.setAge(23);
		cat.setName("黄猫");
		System.out.println("改变之后 a2对象");
		System.out.println(a2.getName() +"   "+ a2.getAge() +"===="+a2.getDog().getName()+"   "+
		a2.getDog().getAge() +a2.getCat().getName()+""+a2.getCat().getAge());
	}
	
}
class Animal1 implements Cloneable{//实现克隆接口
	private String name;//名字
	private int age;//年龄
	private Dog dog;//狗类
	private Cat cat;//猫类
	Animal1(String name,int age,Dog dog,Cat cat){//组合了狗这个类  狗 和 动物 是 组合关系
		this.age = age;
		this.name = name;
		this.dog = dog;
		this.cat = cat;
	}
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}	
	@Override
	public Object clone(){// 重写Object 的 clone 方法
		Animal1 a = null;
		try{//可能有异常出现
			/**
			 * 因为基本类型只有stack 而没有heap 所以fleet clone就可以直接使用
			 * 而引用类型既有stack 又有heap fleet clone 只是把 引用类型像基本类型一样给复制出来了但是没
			 * 有接handle （也就是引用没有赋值） 深克隆就是把 handle 给接上了，让stack里的引用指向heap
			 */
			a = (Animal1)super.clone();//本类的克隆 已经把dog clone出来了 但是还没有把dog 的 handle 连上
			a.dog =(Dog) this.dog.clone();//深克隆 把animal克隆出来的 dog属性 ，调用dog 类的clone方法接上handle
			a.cat = (Cat) this.cat.clone();//有几个组合对象就要接几个
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
}
class Dog implements Cloneable{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String name;
	private int age;
	public Dog(String name,int age){
		this.name = name;
		this.age = age;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {//重写Object中的clone方法
		// TODO Auto-generated method stub
		return super.clone();
	}
}
class Cat implements Cloneable{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
	public Cat(String name,int age){
		this.name = name;
		this.age = age;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {//重写Object中的clone方法
		// TODO Auto-generated method stub
		return super.clone();
	}
}
