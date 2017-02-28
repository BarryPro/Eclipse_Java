package pattern.belong.Test;
/**
 * ����ģʽ ��һ��
 * @author belong
 * ����һ����ֻ�ܴ����һ������ʵ�������Ҹ����ṩ��һ��ȡ��
 * ����ʵ���ķ���
 * ȱ�ݣ��ڶ��߳��в�������������
 */
public class Singleton_Pattern1 {
	// 1.˽�еĹ�����
	private Singleton_Pattern1() {

	}

	// 2.һ��˽�е��Լ�����������ڴ�Ŷ���
	private static Singleton_Pattern1 sp;

	// 3.��̬ʵ��������
	public static Singleton_Pattern1 getInstance() {
		if (sp == null) {
			sp = new Singleton_Pattern1();
		}
		return sp;
	}
}
