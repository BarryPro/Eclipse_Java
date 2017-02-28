package pattern.belong.Test;
/**
 * ����ģʽ ������
 * @author belong
 * ͨ���ڲ���ʵ�ֵ���ģʽʵ���ǣ�classloader�Ļ�������֤ʵ����instanceʱֻ��һ���̣߳�
 * ���ַ�ʽ��Singleton�౻�����˵�instance��һ������ʼ������ΪSingletonHolderû�б�
 * ����ʹ�ã�ֻ����ʾ�ĵ���getInstance sp�Żᱻʵ����
 */
public class Singleton_Pattern3 {
	//1.����һ��˽�о�̬�ڲ������ڲ�������ʵ�ֳ�ʼ��������
	private static class Singleton_PatternHolder{
		private final static Singleton_Pattern3 sp = new Singleton_Pattern3();
	}
	//2.ͨ����̬��ʼ�����������ڲ����������Ķ���
	public static Singleton_Pattern3 getInstance(){
		return Singleton_PatternHolder.sp;
	}
}
