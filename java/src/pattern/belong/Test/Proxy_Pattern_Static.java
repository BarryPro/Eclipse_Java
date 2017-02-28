package pattern.belong.Test;
/**
 * ��̬����ģʽ
 * @author belong
 * �ŵ�:����ʹ�ͻ��˲���Ҫ֪��ʵ������ʲô����ô���ģ�
 * ���ͻ���ֻ��Ҫ֪�������ɣ�����ϣ�
 * ȱ��:����Ķ���һ������ �������ɱ�����
 * 1���������ί����ʵ������ͬ�Ľӿڣ�������ͨ��ί����ʵ��
 * ����ͬ�ķ����������ͳ����˴����Ĵ����ظ�������ӿ�����
 * һ����������������ʵ������Ҫʵ����������⣬���д�����
 * Ҳ��Ҫʵ�ִ˷��������ӵĴ���ά���ĸ��Ӷȡ�
 * 2���������ֻ������һ�����͵Ķ������Ҫ��������͵Ķ���
 * �Ʊ�ҪΪÿһ�����󶼽��д�����̬�����ڳ����ģ�Դ�ʱ��
 * �޷�ʤ���ˡ����ϵĴ�����ֻΪSubject��ķ����ṩ�˴�����
 * �������ҪΪ��������Department���ṩ����Ļ�������Ҫ�����ٴ�
 * ��Ӵ���Department�Ĵ����ࡣ
 */
public class Proxy_Pattern_Static {
	public static void main(String[] args) {
		ISubject sub = new RealSubject();
		Proxy p = new Proxy(sub);
		p.print();
		p.say();
	}
}
interface ISubject {
	public void print();
	public void say();
}
class RealSubject implements ISubject{

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("��˵������");
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("��˵������***");
	}
	
}
/**
 * ������
 * 
 */
class Proxy implements ISubject {
	private ISubject sub;
	public Proxy(ISubject sub){
		this.sub = sub;
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		sub.print();
		System.out.println("������˵����");
	}
	@Override
	public void say() {
		// TODO Auto-generated method stub
		sub.say();
		System.out.println("������˵��ԭ������ѽ��");
	}
	
}
