package stream.belong.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Properties;
/**
 * ���ļ��е�������Ϣ������
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
			Enumeration<Object> e = p.keys();//��ùؼ��ֵļ�
			while (e.hasMoreElements()) {
				Object o = e.nextElement();
				if (constellation.equals(o)) {
					System.out.println(p.get(o));	//�����Ӧ��������Ϣ				
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
