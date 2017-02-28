package pattern.belong.Test;
/**
 * ����ģʽ �ڶ���
 * @author belong
 * �ȵ�һ�ֶ��ͬ����synchronized��
 * �ŵ㣺���Ժܺõ��ڶ��߳��й���
 * ȱ�㣺Ч�ʵͣ�99%������²���Ҫͬ��
 */ 
public class Singleton_Pattern2 {
	//1.˽�й�����
	private Singleton_Pattern2(){
		
	}
	//2.����һ������Ķ������ڴ�Ŷ���
	private static Singleton_Pattern2 sp;
	//3.ͬ����̬ʵ��������
	public synchronized static Singleton_Pattern2 getInstance(){		
		if(sp == null){
			sp = new Singleton_Pattern2();
		}
		return sp;
	}
}
