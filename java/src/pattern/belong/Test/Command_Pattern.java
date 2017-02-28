package pattern.belong.Test;
/**
 * ����ģʽ
 * ���壺
 * ����ģʽ��һ��������߲�����װ��һ�������У����԰ѷ�����������κ�ִ��
 * ��������ηָ��ί�ɸ���ͬ�Ķ���
 * ����ģʽ���������һ���ͷ��͵�һ������������ʹ�������һ������֪������
 * ��һ���Ľӿڣ�������֪����������ô�����գ��Լ������Ƿ�ִ�У���ʱ��ִ��
 * �Լ�����ô��ִ�С�
 * ϵͳ֧������ĳ�����
 * @author Belong
 * �ŵ㣺
 * �����˵����ߺͽ�����֮����ϵ�������ߵ���һ�������������߽�������ִ����
 * Ӧ�Ķ�������Ϊʹ��Commandģʽ�������������֪���������κνӿڡ�
 * ȱ�㣺
 * ��ɳ��ֹ���ľ���������
 * 
 * Ӧ�ó�����
 * 1.���������ĳ�������У�һ�����������һ������һ������µĵ��ù�����
 * ����Ŀ�����ʵ�������õ��ò���������Ŀ�����ķ�����������Щ������б�Ҫ
 * ʹ��һ��ר�ŵ�������ֵ��ù��̼��Է�װ�����ǰ�����ר�ŵ������Command�ࡣ
 * 2.�������ù��̱ȽϷ��ӣ����ߴ��ڶദ���ֵ��á���ʱ��ʹ��Command��Ըõ�
 * �ü��Է�װ�����ڹ��ܵ������á�
 * 3.����ǰ����Ҫ�Ե��ò�������ĳЩ����
 * 4.����ǰ����Ҫ����ĳЩ���⴦��������־�����棬��¼��ʷ�����ȡ�
 * 
 *
 */
public class Command_Pattern {
	public static void main(String[] args) {		
		Receiver receiver = new Receiver();
		new Invoker(new ConcreteCommand(receiver)).go();//�����߷�������
	}
}
//�����������װ��һЩ�������ò�����
interface Command{
	public void execute();
}
//�����������ĵ��ò�������װ�˽�������Ľ����ߣ�
class ConcreteCommand implements Command{//ͨ���������������ת
	private Receiver receiver;//������Ҫִ�еĵ��õĶ���
	public ConcreteCommand(Receiver receiver){
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("��������");
		this.receiver.action();
	}	
}
//�����ߣ��������
class Receiver {
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("Receiver:"+"�յ�");
	}	
}
//�����ߣ����𷢳�����
class Invoker{
	private Command command;
	public Invoker(Command command){
		this.command = command;
	}
	public void go(){//�������
		command.execute();
	}
	
}