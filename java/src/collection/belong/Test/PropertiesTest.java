package collection.belong.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
		Properties p = new Properties();
		InputStream is = PropertiesTest.class.getClassLoader().getResourceAsStream("1.txt");// 的到根目录下的文件路径
		try {
			p.load(is);
			Enumeration<Object> e = p.keys();
			while (e.hasMoreElements()) {
				Object o = e.nextElement();
				System.out.printf("%s=%s\n", o, p.get(o));//只能用printf （） 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
