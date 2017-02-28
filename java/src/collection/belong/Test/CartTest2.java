package collection.belong.Test;

import java.util.Map;
import java.util.Scanner;
/**
 * 测试map购物车
 * @author belong
 *
 */
public class CartTest2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Cart2 cart = new Cart2();
		Map<Integer, Goods2>map = cart.cartGoods();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入\n编号:(1) 物品名：(面包) 数量：(5) 价格：(1.5)");
		while(in.hasNext()){
			int no = in.nextInt();
			String name = in.next();
			int count = in.nextInt();
			double price = in.nextDouble();
			cart.buyGoods(map, new Goods2(no,name,count,price));
			cart.showCart(map);
		}
		
		
	}
}
