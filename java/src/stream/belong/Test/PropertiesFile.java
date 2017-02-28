package stream.belong.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Properties;
/**
 * 把文件中的星座信息读出来
 * @author belong
 *
 */
public class PropertiesFile {
	public void fileTransformString(String constellation) {
		Properties p = new Properties();
		Reader read = null;
		try {
			read = new FileReader("D:\\javafile\\constellation.txt");
			p.load(read);
			Enumeration<Object> e = p.keys();//获得关键字的键
			while (e.hasMoreElements()) {
				Object o = e.nextElement();
				if (constellation.equals(o)) {
					System.out.println(p.get(o));	//输出对应的星座信息				
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
