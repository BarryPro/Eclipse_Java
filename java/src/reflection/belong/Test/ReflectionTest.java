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
	public void reflectArray(){//反射数组运行时建立数组
		try {
			Object o = Array.newInstance(String.class, 10);
			Array.set(o, 0, "mao");
			Array.set(o, 2, "goo");
			Array.set(o, 1, "hai");
			Array.set(o, 3, "lan");
			for(int i = 0;i<10;i++){//三目必须赋值（？：）
				System.out.println(Array.get(o, i)==null?"":Array.get(o, i));
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 非默认构造器 反射属性段
	 */
	public void reflectField(){
		try {
			Class <?> c = Class.forName("others.belong.application.Animal");
			Constructor<?>  con = c.getConstructor(int.class,String.class);//因为Animal里没有默认构造器
			Animal a = (Animal)con.newInstance(20,"long");//非默认构造器
//			Field [] fs = c.getDeclaredFields();//得到类里的属性集合 该方法可以访问私有的成员 
//			Field [] fs = c.getFields(); 不能访问私有成员
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);//突破私有显示
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
			Constructor<?> con = c.getConstructor(int.class,String.class);//反射得到非默认构造器
			Animal a = (Animal)con.newInstance(100,"gou");			
			Method m1 = c.getMethod("getName");
			System.out.println(m1.invoke(a));
			Method m2 = c.getMethod("setName",String.class);//参数必须是反射时候的动态参数(eg:...class)
			m2.invoke(a, "mao");
			System.out.println(m1.invoke(a));
//			Method [] ms = c.getMethods();//反射的到方法（返回方法集）
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
				Class<?> c = Class.forName(pro.get(o).toString());//返回运行时类
				//Constructor con = c.getConstructor(); 隐含调用默认构造器
				QuickSort q = (QuickSort) c.newInstance(); //调用默认构造器反射
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
