package collection.belong.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart1 {
	List<Map.Entry<String, String>> listTemp1;
	List<Map.Entry<String, String>> listTemp2;

	public void buyGoods(List<Goods> list, Goods newgoods) {
		// TODO Auto-generated method stub

		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			listTemp1 = new ArrayList<Map.Entry<String, String>>(list.get(i).map.entrySet());
			listTemp2 = new ArrayList<Map.Entry<String, String>>(newgoods.map.entrySet());
			for (int j = 0; j < listTemp1.size(); j++) {
				if (listTemp1.get(j).getKey().equals(listTemp2.get(j).getKey())) {
					list.get(i).setCount(list.get(i).getCount() + newgoods.getCount());
					flag = true;
				}
			}

		}
		if (!flag) {
			list.add(newgoods);
		}
	}

	public void print(List<Goods> list) {
		for (int i = 0; i < list.size(); i++) {
			listTemp1 = new ArrayList<Map.Entry<String, String>>(list.get(i).map.entrySet());
			for (int j = 0; j < listTemp1.size(); j++) {
				System.out.println("编号：" + listTemp1.get(j).getKey() + "物品：" + listTemp1.get(j).getValue() + "数量："
						+ list.get(i).getCount() + "价格：" + list.get(i).getCount() * list.get(i).getPrice());
			}
		}
	}
}
