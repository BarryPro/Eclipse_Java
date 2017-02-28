package pattern.belong.Test;
/**
 * ����ģʽ ������ ��˫�ؼ�������棩
 * @author belong
 * (�Ƽ�������д��)
 */
public class Singleton_Pattern4 {
	//1.˽�й�����
	private Singleton_Pattern4(){
		
	}
	//2.ͨ��volatile��ʵ�־�̬�ı������
	//volatile�ؼ���ֻ��jvm��֤�������ж����߳��е�ֵ�����µ�ֵ
	private volatile static Singleton_Pattern4 sp;
	//3.�ھ�̬ʵ����������ʵ��ͬ��
	public static Singleton_Pattern4 getInstance(){
		synchronized(Singleton_Pattern4.class){
			if(sp == null){
				sp = new Singleton_Pattern4();
			}
		}
		return sp;
	}
}
