package collection.belong.Test;

import java.util.Map;
import java.util.Scanner;
/**
 * ����map���ﳵ
 * @author belong
 *
 */
public class CartTest2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Cart2 cart = new Cart2();
		Map<Integer, Goods2>map = cart.cartGoods();
		Scanner in = new Scanner(System.in);
		System.out.println("������\n���:(1) ��Ʒ����(���) ������(5) �۸�(1.5)");
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
