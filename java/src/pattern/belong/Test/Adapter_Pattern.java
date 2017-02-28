package pattern.belong.Test;
/**
 * ������ģʽ
 * ���壺��һ����Ľӿڱ任�ɿͻ������ڴ�������һ���ӿ�
 * ʹ��ԭ�����ڽӿڲ����ݶ�������һ��������Щ�����һ
 * ����
 * @author belong
 * 1.ϵͳ��Ҫʹ�����е��࣬������Ľӿڲ�����ϵͳ����Ҫ
 * 2.��Ҫ����һ�������ظ�ʹ�õ��࣬������һЩ�˴�֮��û
 * ��̫�������һЩ���𣬰���һЩ�����ڽ�����������һ��
 * ��������ЩԴ�಻һ���и��ӵĽӿ�
 * 3.���Զ������������ԣ���������Ҫ�ı�������֮��
 * �Ľӿڣ����ʹ�����������ģʽ����Ҫ���ÿһ��������
 * һ�������������ⲻ̫ʵ�ʡ�
 */
public class Adapter_Pattern {
	public static void main(String[] args) {
		Target a = new Adapter();
		a.Request();
	}
}
//�ǿͻ��ӿ�
interface Target{
	public void Request();	
}
//������Ľӿ�������Ҫʵ������Ҫ�����õ��ࣩ
class Adaptee{//ԭ�ӿ�
	public void SpecificRequest(){
		System.out.println("�����������");
	}
}
//��������
class Adapter extends Adaptee implements Target{
	private Adaptee adaptee = new Adaptee();//�½�ԭ�ӿ���
	@Override
	public void Request() {
		// TODO Auto-generated method stub
		adaptee.SpecificRequest();//ԭ�ӿڵ���ԭ����
	}
	
}