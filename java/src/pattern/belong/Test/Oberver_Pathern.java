package pattern.belong.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * �۲���ģʽ
 * ���壺
 * ����˹۲��ߺ�Ŀ��֮�����Ϲ�ϵ��Ŀ�겻��Ҫ֪�����Ĺ۲��ߵ��κι�ϵ
 * �෴��Ŀ��ֻ������۲��߶����¼�����Ŀ�����һ���¼�ʱ�����򵥵ؽ��¼�����
 * ÿһ���۲��ߡ�
 * �򵥵�˵���۲���ģʽ������һ��һ�Զ��������ϵ��
 * ��һ�������۲��߶�����һ�������������һ�������
 * ����״̬�ϵı仯�ܹ�֪ͨ���е������ڴ˶������Щ�۲���
 * ����ʹ��Щ�۲��߶����ܹ��Զ����¡�
 * @author Belong
 * �ŵ㣺
 * 1.�۲���ģʽ�ڱ��۲���֮�佨��һ���������ϡ�
 * ���۲��߽�ɫ��֪����ֻ��һ������۲��ߵ��б�ÿһ��
 * ����۲��߶�����һ������۲��ߵĵĽӿڡ����۲��߲�����ʶ�κ�
 * һ������Ĺ۲��ߣ���ֻ֪�����Ƕ���һ�������Ľӿڣ�update����
 * ���ڱ��۲��ߺ͹۲���û�н��ܵ������һ��������ǿ������ڲ�ͬ�ĳ��󻯲��
 * ������۲��ߺ͹۲��߶����ӵ�һ����ô��������Ȼ��Խ���󻯺;��廯���
 * 2.�۲���ģʽ֧�ֹ㲥ͨѶ�����۲��߻������еĵǼǹ��Ĺ۲��߷���֪ͨ��
 * ȱ�㣺
 * 1.���һ���۲��߶����кܶ��ֱ�Ӻͼ�ӵĹ۲��ߵĻ��������еĹ۲��߶�֪ͨ���Ứ�Ѻܶ�ʱ�䡣
 * 2.����ڱ��۲���֮����ѭ�������Ļ������۲��߻ᴥ������֮�����ѭ�����ã�����ϵͳ������
 * ��ʹ�ù۲���ģʽ��Ҫ�ر�ע����һ�㡣
 * 3.����Թ۲��ߵ�֪ͨ��ͨ��������߳̽����첽Ͷ�ݵĻ���ϵͳ���뱣֤Ͷ��������Ǣ�ķ�ʽ���еġ�
 * 4.��Ȼ�۲���ģʽ������ʱʹ�۲���֪�����۲�Ķ������˱仯�����ǹ۲���ģʽû����Ӧ�Ļ����ǹ۲���
 * ֪�����۲�Ķ�������ô�����仯�ģ�����֪��������ʲô�仯���ѡ�
 */
public class Oberver_Pathern {
	public static void main(String[] args) {
		ConcreteSubject c =	new ConcreteSubject();
		c.attach(new ConcreteObserver(c,"maik"));
		c.attach(new ConcreteObserver(c,"lileli"));
		c.attach(new ConcreteObserver(c,"hah"));
		c.setMessage("��Ů...");
		c.Notify();		
	}
}
//���������ɫ�������¼�����ߣ���֪ͨ������
class Subject {
	List<Observer> observers = new ArrayList<Observer>();
	//����
	public void attach(Observer o) {
		observers.add(o);
	}
	//ɾ��
	public void delete(Observer o) {
		observers.remove(o);
	}
	//֪ͨ����(ѭ������������������ȥ��������)
	public void Notify() {
		for (Observer o : observers) {
			o.update();//��������
		}
	}
}
//��������
class ConcreteSubject extends Subject {
	private String message;//���������

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
//����۲��ߣ����Ը����Լ�������
abstract class Observer {
	//�������ⷽ��
	public abstract void update();
}
//����Ĺ۲��ߣ�
class ConcreteObserver extends Observer{
	private ConcreteSubject sub;//���ĵľ�������
	private String name;//�۲��ߵ�����
	public ConcreteObserver(ConcreteSubject sub,String name){
		this.sub = sub;
		this.name = name;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("���ǣ�"+this.name+" �ҹ�ע�� "+this.sub.getMessage()+"����");
	}	
}
