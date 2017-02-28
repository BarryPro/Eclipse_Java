package pattern.belong.Test;
/**
 * �򵥹���ģʽ��Simple Factory��
 * �ֽо�̬����ģʽ
 * @author belong
 * ȱ�㣺�ڹ������м���������ʵ���Ĵ����߼���Υ��
 * �˸��ھ����η���ԭ�򣬽�ȫ�������߼����е���һ��
 * �������У���ϵͳ��ά������չ�������޷������²�Ʒ��
 */
public class SimpleFactory_Pattern {
	public static void main(String[] args) {
		new Factory().make(1).make();
		new Factory().make(2).make();
	}
}
//����Ĳ�Ʒ��������������ʵ�������еĹ����ӿ�
interface IProduce {
	public void make();
}
//���� ������ʵ�ִ�������ʵ�����ڲ��߼���
class Factory {
	public IProduce make(int no) {
		switch (no) {
		case 1:
			return new ProduceA();
		case 2:
			return new ProduceB();
		}
		return null;
	}
}
//�����Ʒ������Ŀ��
class ProduceA implements IProduce {
	public ProduceA() {

	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		System.out.println("����");
	}
}

class ProduceB implements IProduce {
	public ProduceB() {

	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		System.out.println("����");
	}
}