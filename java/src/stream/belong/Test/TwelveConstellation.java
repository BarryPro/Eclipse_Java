package stream.belong.Test;

import java.util.Scanner;
/**
 * ��������
 * @author belong
 *
 */
public class TwelveConstellation {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PropertiesFile p = new PropertiesFile();
		System.out.println("��ӭʹ�ã�\n�������������(����/��������)����˫������"
				+ "\n(��ʾ)��������һ�����밴��س�����������"
				+ "������q����");
		while(in.hasNext()){
			String s = in.next();
			if(!s.equals("q")){
				p.fileTransformString(s);
			} else {
				System.out.println("�������! ��ӭ�´�ʹ��");
				break;
			}
		}		
	}
}
