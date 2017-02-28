package pattern.belong.Test;
/**
 * ��������ģʽ
 * �Ǽ򵥹�������ģʽ�����������ĺ��Ĺ����಻�ٸ���
 * ��Ʒ�Ĵ������������Ϊһ�����󹤳���ɫ���������
 * �幤���������ʵ�ֵĽӿڣ�ʹ��ϵͳ�����ڲ��޸ľ�
 * �幤����ɫ������������µĲ�Ʒ��
 * �ص㣺һ�����󹤳���Ӧһ�������Ʒ��һ�����幤��
 * ��Ӧһ������Ĳ�Ʒ��
 * @author belong
 * �ŵ㣺�������������Ʒ
 * ȱ�㣺ͬһ�ȼ��Ĺ̶���Ʒ
 */
public class Factory_Method {
	public static void main(String[] args) {
		Creator creator1 = new BulbCreator();//��ͬ�Ĺ���ʵ�� ��ʵ�ֲ�ͬ�Ĳ�Ʒʵ��
		Creator creator2 = new TubeCreator();
		
		creator1.factory().turnon();
		creator1.factory().turndown();
		creator2.factory().turnon();		
		creator2.factory().turndown();
	}
}
//���󹤳���ɫ�������ģ��κ���ģʽ�д����Ķ���
//�Ĺ�������ʵ������ӿ�
interface Creator {
	public Light factory();

}
//�����Ʒ����Ʒ����Ĺ�ͬ�����ͬӵ�еĽӿ�
interface Light{
	public void turndown();
	public void turnon();
}
//���幤����ʵ�ֳ��󹤳��ӿڵľ��幤���࣬����
//Ӧ�ó�����߼�
class BulbCreator implements Creator{
	@Override
	public Light factory() {
		// TODO Auto-generated method stub
		return new BulbLight();//���幤����Ӧ����Ĳ�Ʒ
	}	
}
class TubeCreator implements Creator{
	@Override
	public Light factory() {
		// TODO Auto-generated method stub
		return new TubeLight();
	}	
}
//�����Ʒ��ʵ���˳����Ʒ��ɫ����Ľӿ�
//������Ʒ��ר�ŵľ��幤������
class BulbLight implements Light{
	@Override
	public void turndown() {
		// TODO Auto-generated method stub
		System.out.println("����down");
	}
	@Override
	public void turnon() {
		// TODO Auto-generated method stub
		System.out.println("����on");
	}
}
class TubeLight implements Light{
	@Override
	public void turndown() {
		// TODO Auto-generated method stub
		System.out.println("Tubedown");
	}
	@Override
	public void turnon() {
		// TODO Auto-generated method stub
		System.out.println("Tubeon");
	}
}