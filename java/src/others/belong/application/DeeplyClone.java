package others.belong.application;

public class DeeplyClone {
	public static void main(String[] args) {
		Dog dog = new Dog("�ƹ�",14);
		Cat cat = new Cat("��è",15);
		Animal1 a1 = new Animal1("Сǿ",13,dog,cat);
		Animal1 a2 = (Animal1)a1.clone();//���¡ a2��a1��ȫһ�� ����Ҳһ�� 
		System.out.println("�ı�֮ǰ a1����");
		System.out.println(a1.getName() +"   "+ a1.getAge() +"===="+a1.getDog().getName()+"   "+
				a1.getDog().getAge() +a1.getCat().getName()+""+a1.getCat().getAge());
		System.out.println("�ı�֮ǰ a2����");
		System.out.println(a2.getName() +"   "+ a2.getAge() +"===="+a2.getDog().getName()+"   "+
		a2.getDog().getAge());
		dog.setName("���");//�ı乷������ ������
		dog.setAge(23);
		cat.setName("��è");
		System.out.println("�ı�֮�� a2����");
		System.out.println(a2.getName() +"   "+ a2.getAge() +"===="+a2.getDog().getName()+"   "+
		a2.getDog().getAge() +a2.getCat().getName()+""+a2.getCat().getAge());
	}
	
}
class Animal1 implements Cloneable{//ʵ�ֿ�¡�ӿ�
	private String name;//����
	private int age;//����
	private Dog dog;//����
	private Cat cat;//è��
	Animal1(String name,int age,Dog dog,Cat cat){//����˹������  �� �� ���� �� ��Ϲ�ϵ
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
	public Object clone(){// ��дObject �� clone ����
		Animal1 a = null;
		try{//�������쳣����
			/**
			 * ��Ϊ��������ֻ��stack ��û��heap ����fleet clone�Ϳ���ֱ��ʹ��
			 * ���������ͼ���stack ����heap fleet clone ֻ�ǰ� �����������������һ�������Ƴ����˵���û
			 * �н�handle ��Ҳ��������û�и�ֵ�� ���¡���ǰ� handle �������ˣ���stack�������ָ��heap
			 */
			a = (Animal1)super.clone();//����Ŀ�¡ �Ѿ���dog clone������ ���ǻ�û�а�dog �� handle ����
			a.dog =(Dog) this.dog.clone();//���¡ ��animal��¡������ dog���� ������dog ���clone��������handle
			a.cat = (Cat) this.cat.clone();//�м�����϶����Ҫ�Ӽ���
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
	protected Object clone() throws CloneNotSupportedException {//��дObject�е�clone����
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
	protected Object clone() throws CloneNotSupportedException {//��дObject�е�clone����
		// TODO Auto-generated method stub
		return super.clone();
	}
}
