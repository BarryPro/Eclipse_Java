package stream.belong.Test;

import java.util.Scanner;
/**
 * 测试星座
 * @author belong
 *
 */
public class TwelveConstellation {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PropertiesFile p = new PropertiesFile();
		System.out.println("欢迎使用！\n请输入你的星座(或他/她的星座)：（双鱼座）"
				+ "\n(提示)如果想测下一个人请按完回车再输入星座"
				+ "，否则按q结束");
		while(in.hasNext()){
			String s = in.next();
			if(!s.equals("q")){
				p.fileTransformString(s);
			} else {
				System.out.println("程序结束! 欢迎下次使用");
				break;
			}
		}		
	}
}
