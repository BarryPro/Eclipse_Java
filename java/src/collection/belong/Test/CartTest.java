package collection.belong.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CartTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<Goods> list = new ArrayList<Goods>();
		Scanner in = new Scanner(System.in);
		Cart1 cart1 = new Cart1();
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("请输入 商品号：商品名：数量：价格：");
		while (in.hasNext()) {
			String no = in.next();
			String name = in.next();
			int count = in.nextInt();
			float price = in.nextFloat();
			map.put(no, name);
			Goods newgoods = new Goods(map, count, price);
			cart1.buyGoods(list, newgoods);
			cart1.print(list);
		}
	}
}
