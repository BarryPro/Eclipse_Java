package collection.belong.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapSort {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("A", "100");
		map.put("b", "200");
		map.put("d", "300");
		map.put("f", "500");
		System.out.println(map);
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<String, String>>(){

			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}			
		});
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

}
