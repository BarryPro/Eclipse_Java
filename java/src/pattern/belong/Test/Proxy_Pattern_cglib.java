package pattern.belong.Test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/** 
 * cglib�������ģ�MethodInterceptor.intercept��
 * @author belong
 * jdk��cglib�ıȽϣ�
 * JDK�Ķ�̬�������ֻ�ܴ���ʵ���˽ӿڵ��࣬
 * ������ʵ�ֽӿڵ���Ͳ���ʵ��JDK�Ķ�̬����
 * cglib���������ʵ�ִ���ģ�����ԭ���Ƕ�ָ����Ŀ��������һ�����࣬
 * ���������з���ʵ����ǿ������Ϊ���õ��Ǽ̳У����Բ��ܶ�final���ε�����д���
 * ȱ�㣺 ���ܶ�final���ε�����д���
 */
public class Proxy_Pattern_cglib {
	public static void main(String[] args) {
		BookFacadeCglib b = new BookFacadeCglib();
		BookFacadeImpll bookimpl = (BookFacadeImpll)b.getInstance(new BookFacadeImpll());
		bookimpl.addBook();
	}
}
//û������ֻ�Ǻ�jdk���������Ա�
interface BookFacade{
	public void addBook();
}
/**
 * û��ʵ��BookFacade�ķ���
 */
class BookFacadeImpll{
	public void addBook(){
		System.out.println("����ͼ�����ͨ����");
	}
}
/**
 * ����ԭ��ָ��Ŀ����ʵ����������
 */
class BookFacadeCglib implements MethodInterceptor{
	private Object target;
	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();//��ǿ����
		enhancer.setSuperclass(this.target.getClass());//ָ��Ŀ����ʵ����������
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/**
	 * @param obj ���������Ķ���
	 * @param method ���������еķ���
	 * @param args ��������ķ�������ʱ����Ҫ�Ĳ���
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("starting");
		proxy.invokeSuper(obj, args);
		System.out.println("the end");
		return null;
	}
	
}