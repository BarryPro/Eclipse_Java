package pattern.belong.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk��̬����ͨ���������ʵ�ֶ�̬����
 * �����ģ�InvocationHandler.invoke��
 * @author belong
 * �ŵ㣺�ܹ�����������͵Ķ���
 * �ӿ������������з�������ת�Ƶ����ô�����һ�����еķ����д���
 * ��InvocationHandler.invoke�����������ڽӿڷ��������Ƚ϶��ʱ��
 * ���ǾͿ������Ĵ���������Ҫ��̬��������ÿһ������������ת
 * ���Ҷ�̬�����Ӧ��ʹ���ǵ���ְ����ӵ�һ�������Ը�ǿ��
 * ȱ�㣺jdk��̬���������ӿ�ʵ�֣������û��ʵ�ֽӿڣ��Ͳ���ʹ��jdk�����ˣ�Ҫ��cglib����
 */
public class Proxy_Pattern_JDK {
	public static void main(String[] args) {
		IAnimal cat = new Cat();
		MyProxy p = new MyProxy(cat);
		Object obj = Proxy.newProxyInstance(cat.getClass().getClassLoader(),//�õ�����ʱ�������
				cat.getClass().getInterfaces(),//�õ�����ʱҪ������ʵ�ֵ�ȫ���ӿ�
				p);//�Լ�����Ĵ���������ʵ��Invocationhandler�ӿ�
		if(obj instanceof IAnimal){
			IAnimal c = (IAnimal)obj;
			c.say();
			c.action();
			c.drunk();
		}else {
			System.out.println("����IAnimal ������");
		}
	}
}
/**
 * ���������ʵ��Invocationhandler �ӿ�
 * @author belong
 * ��jdk����̬����
 */
class MyProxy implements InvocationHandler{
	private Object obj;
	public MyProxy(Object obj){
		this.obj = obj;
	}
	/**
	 * @param obj ָ������Ķ���
	 * @param method Ҫ���õķ�������������ķ�����
	 * @param args ��������ʱ����Ҫ�Ĳ���
	 */
	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("���Ǵ�������");//�ں��ķ���ʵ��֮ǰҪʵ�ֵĹ���
		return method.invoke(this.obj, args);//���÷���ķ���
	}
	
}
/*
 *����ӿ�ʵ�����������ʵ�����ķ���
 */
interface IAnimal {
	public void say();
	public void action();
	public void drunk();
}
class Cat implements IAnimal{
	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("������");
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("������");
	}
	public void drunk(){
		System.out.println("�ڿ���");
	}
	
}