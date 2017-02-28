package pattern.belong.Test;
/**
 * ���󹤳�ģʽ�������Ʒ�壩
 * ��ָ���ж�������ɫʱ��ʹ�õ�һ�ֹ���ģʽ��
 * ���󹤳�ģʽ������ͻ����ṩһ���ӿڣ�ʹ�ͻ�
 * ���ڲ���ָ����Ʒ�ľ�������£����������Ʒ
 * ���еĲ�Ʒ����
 * @author belong
 * �ŵ㣺��Բ�Ʒ������ģ����Ӳ�Ʒ�ߺ����ף�
 * ȱ�㣺�޷������²�Ʒ��
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
//���󹤳������Դ��������Ʒ���еĲ�Ʒ����
interface AbstractFactory {
	public AbstractProduceA createProduceA();
	public AbstractProduceB createProduceB();
}

//���幤������������Ĳ�Ʒ
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

//�����Ʒ�����о����Ʒ����̳���
abstract class AbstractProduceA {
	public abstract void make();
}
abstract class AbstractProduceB {
	public abstract void make();
}

//�����Ʒ�������������Ʒ
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
