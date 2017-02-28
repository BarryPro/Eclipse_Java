package collection.belong.Test;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapTest {
	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "����");
		map.put(7, "���");
		map.put(3, "�ƹ�");
		map.put(4, "����");
		map.put(5, "�㽶");
		map.put(6, "���");
		map.put(10, "����");
		map.put(2, "����");
		//System.out.println(map.entrySet());
		List<Map.Entry<Integer,String>> list = new ArrayList<Map.Entry<Integer,String>>(map.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<Integer,String>>(){
			// -1 :o1<o2  0 : o1==o2  1: o1>o2 
			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				// TODO Auto-generated method stub
				return o1.getKey().compareTo(o2.getKey());
			}			
		});
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//System.out.println(list);
	}
}

class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
}