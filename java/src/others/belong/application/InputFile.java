package others.belong.application;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * ������ļ������ݶ��������ַ�����ʽ��
 * 
 * @author belong
 *
 */
public class InputFile {
	public void inputFile(String name,double weight) {
		Properties p = new Properties();// �ļ��в�����û��=�Ŷ������������=�Ų���û�оͼ���=�ţ�
		Reader read = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			read = new FileReader("D:\\javafile\\calorielist.txt");
			p.load(read);// �ļ��������Ǽ�ֵ�ԵĶ���������p
			Enumeration<Object> e = p.keys();// ��ȡ �ļ��е�map�ļ���
			while (e.hasMoreElements()) {
				Object o = e.nextElement();// ������ôд����Ϊe.nextElementÿһ�ζ���������һ����
				System.out.printf("%s=%s\n", o, p.get(o));
				map.put(o.toString(), p.get(o).toString());// ���ļ��еļ�ֱ��д��map��
			}
			for (String s : map.keySet()) {
				System.out.println(s);
			}
			calculateCalorie(name, weight, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param name ��Ʒ����
	 * @param weight ��Ʒ����
	 * @param map Դ�ļ��еĿ�·���
	 */
	private void calculateCalorie(String name, double weight, Map<String, String> map) {
		List<Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		double total = 0.0;
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getKey().equals(name)){
			total = Double.parseDouble(list.get(i).getValue())*weight/100;
			}
		}
		System.out.println("�ܿ�·�"+total+"��·��");
	}
}
