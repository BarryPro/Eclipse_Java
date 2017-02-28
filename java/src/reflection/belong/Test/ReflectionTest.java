package reflection.belong.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

import algorithm.belong.Test.QuickSort;
import others.belong.application.Animal;

public class ReflectionTest {
	public static void main(String[] args) {
		new ReflectionTest().reflectArray();
	}
	public void reflectArray(){//������������ʱ��������
		try {
			Object o = Array.newInstance(String.class, 10);
			Array.set(o, 0, "mao");
			Array.set(o, 2, "goo");
			Array.set(o, 1, "hai");
			Array.set(o, 3, "lan");
			for(int i = 0;i<10;i++){//��Ŀ���븳ֵ��������
				System.out.println(Array.get(o, i)==null?"":Array.get(o, i));
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ��Ĭ�Ϲ����� �������Զ�
	 */
	public void reflectField(){
		try {
			Class <?> c = Class.forName("others.belong.application.Animal");
			Constructor<?>  con = c.getConstructor(int.class,String.class);//��ΪAnimal��û��Ĭ�Ϲ�����
			Animal a = (Animal)con.newInstance(20,"long");//��Ĭ�Ϲ�����
//			Field [] fs = c.getDeclaredFields();//�õ���������Լ��� �÷������Է���˽�еĳ�Ա 
//			Field [] fs = c.getFields(); ���ܷ���˽�г�Ա
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);//ͻ��˽����ʾ
			name.set(a, "belong");
			System.out.println(name.get(a));
//			for(Field f:fs){
//				System.out.println(f.getName());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void reflectMethod(){
		try {
			Class<?> c = Class.forName("others.belong.application.Animal");
			Constructor<?> con = c.getConstructor(int.class,String.class);//����õ���Ĭ�Ϲ�����
			Animal a = (Animal)con.newInstance(100,"gou");			
			Method m1 = c.getMethod("getName");
			System.out.println(m1.invoke(a));
			Method m2 = c.getMethod("setName",String.class);//���������Ƿ���ʱ��Ķ�̬����(eg:...class)
			m2.invoke(a, "mao");
			System.out.println(m1.invoke(a));
//			Method [] ms = c.getMethods();//����ĵ����������ط�������
//			for(Method m:ms){
//				System.out.println(m.getName());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reflect(){
		Properties pro = readTxt();
		Set<Object> set = pro.keySet();
		for(Object o:set){
			try {
				Class<?> c = Class.forName(pro.get(o).toString());//��������ʱ��
				//Constructor con = c.getConstructor(); ��������Ĭ�Ϲ�����
				QuickSort q = (QuickSort) c.newInstance(); //����Ĭ�Ϲ���������
				if(q instanceof QuickSort){
					int a[] = {3,6,5,3,2,5,7,6,39,89,10,90,7};
					int start = 0;
					int end = a.length-1;
					q.quickSort(a, start, end);
					for(int i:a){
						System.out.println(i);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Properties readTxt(){
		InputStream is = null;
		Properties p = null;
		try {
			is = new FileInputStream("D:\\javafile\\reflection.txt");
			p = new Properties();
			p.load(is);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return p;
	}
}
