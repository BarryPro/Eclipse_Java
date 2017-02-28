package pattern.belong.Test;
/**
 * װ����ģʽ
 * ���壺�ڲ��ظı�ԭ���ļ���ʹ�ü̳е�����£���̬����չһ������
 * �Ĺ��ܡ�����ͨ������һ����װ����Ҳ����װ�����������ǵĶ���
 * @author belong
 * Ӧ�ó�����
 * 1.��͸�����Ҷ�̬�ظ����������µ�ְ���ʱ��
 * 2.���������ӵ�ְ����δ���������ӻ���ٿ��ܡ�
 * 3.�ü̳���չ���ܲ�̫��ʵ������£�Ӧ�ÿ�������ϵķ�ʽ
 * ȱ��:1.���ֱȼ̳и��������������ԣ�Ҳͬʱ��ζ�Ÿ��Ӷ�ĸ�����
 *    	2.װ��ģʽ�ᵼ������г������С�࣬�������ʹ�ã���ʹ�����úܸ��ӡ�
 *      3.װ��ģʽ����Գ��������Component�����ͱ�̡����ǣ������Ҫ
 *      ��Ծ���������ʱ����Ӧ�ú�����˼�����Ӧ�üܹ����Լ�װ�����Ƿ���ʡ�
 *      ��ȻҲ���Ըı�Component�ӿڣ������µĹ�������Ϊ��ʵ�֡���͸������װ��ģʽ
 *      ��ʵ����Ŀ��Ҫ�������ѡ��
 */
public class Decorator_Pattern {
	public static void main(String[] args) {
		ChineseGirl c = new ChineseGirl();
		c.getDeco();
		System.out.println(new AmericanGirl().getDeco());
		System.out.println(new Science(c).getDeco());
		System.out.println(new Art(c).getDeco());
		
	}
}
//���󹹼���ɫ������һ������ӿڣ����淶׼�����ӹ��ܵ���
abstract class Girl{
	private String deco = "���Ǹ�ƽ����Ů��";
	public String getDeco(){
		return deco;
	}
	public void setDeco(String deco){
		this.deco = deco;
	}
}
//����װ���߽�ɫ�����жԾ��幹����ɫ�����ò���������󹹼���ɫһ�µĽӿ�
abstract class GirlDecorator extends Girl{
	//���³������
	public abstract String getDeco();
}

class ChineseGirl extends Girl{
	
	public ChineseGirl(){
		super.setDeco(super.getDeco()+"�й��ģ�");
	}
}

class AmericanGirl extends Girl {
	public AmericanGirl(){
		super.setDeco(super.getDeco()+"�����ģ�");
	}
}
//����װ���ࣺʵ�ֳ���װ���߽�ɫ������Ϊ���幹����Ӷ��⹦��
class Art extends GirlDecorator{	
	private Girl girl ;
	public Art(Girl girl){
		this.girl = girl;
	}
	@Override
	public String getDeco() {
		// TODO Auto-generated method stub
		return girl.getDeco()+"�һ�����";
	}
	
}

class Science extends GirlDecorator{
	private Girl girl ;
	public Science(Girl girl){
		this.girl = girl;
	}	
	@Override
	public String getDeco() {
		// TODO Auto-generated method stub
		return girl.getDeco()+"�һ��ѧ";
	}
	
}