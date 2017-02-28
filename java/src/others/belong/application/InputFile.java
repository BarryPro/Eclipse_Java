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
 * 负责把文件的内容读进来（字符的形式）
 * 
 * @author belong
 *
 */
public class InputFile {
	public void inputFile(String name,double weight) {
		Properties p = new Properties();// 文件中不管有没有=号都会读进来（有=号不加没有就加上=号）
		Reader read = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			read = new FileReader("D:\\javafile\\calorielist.txt");
			p.load(read);// 文件的内容是键值对的都读进来给p
			Enumeration<Object> e = p.keys();// 获取 文件中的map的键集
			while (e.hasMoreElements()) {
				Object o = e.nextElement();// 必须这么写（因为e.nextElement每一次都会往下走一步）
				System.out.printf("%s=%s\n", o, p.get(o));
				map.put(o.toString(), p.get(o).toString());// 把文件中的键直对写到map里
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
	 * @param name 商品名字
	 * @param weight 商品重量
	 * @param map 源文件中的卡路里表
	 */
	private void calculateCalorie(String name, double weight, Map<String, String> map) {
		List<Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		double total = 0.0;
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getKey().equals(name)){
			total = Double.parseDouble(list.get(i).getValue())*weight/100;
			}
		}
		System.out.println("总卡路里："+total+"卡路里");
	}
}
