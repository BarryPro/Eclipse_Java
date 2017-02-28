package collection.belong.Test;

import java.util.HashMap;
import java.util.Map;
/**
 * 购物车
 * @author belong
 *
 */
public class Cart2 {
	/**
	 * 
	 * @param map
	 * @param newgood 新商品
	 */
	public void buyGoods(Map<Integer, Goods2> map,Goods2 newgood){
		boolean flag = false;//看看是否与旧商品一样
		for(int i:map.keySet()){
			if(i==newgood.getNo()){//判断新商品是否存在
				map.get(i).setCount(map.get(i).getCount()+newgood.getCount());
				flag = true;//标记以存在
			}
		}
		if(!flag){//如果没有新商品就添加购物车
			map.put(newgood.getNo(),newgood);
		}
	}
	/**
	 * 初始化购物车
	 * @return map
	 */
	public Map<Integer, Goods2> cartGoods(){
		Map<Integer, Goods2> map = new HashMap<Integer, Goods2>();
		Goods2 good1 = new Goods2(1,"面包",2,1.5);
		Goods2 good2 = new Goods2(2,"香蕉",5,1.5);
		Goods2 good3 = new Goods2(3,"黄瓜",10,1.2);
		map.put(good1.getNo(), good1);
		map.put(good2.getNo(), good2);
		map.put(good3.getNo(), good3);
		return map;
	}
	/**
	 * 输出购物车的物品并小计
	 * @param map
	 */
	public void showCart(Map<Integer, Goods2> map){
		for(int i:map.keySet()){
			System.out.println("编号: "+i+" "
					+"商品名："+map.get(i).getName()+" "
					+"商品数量："+map.get(i).getCount()
					+"价格："+map.get(i).getPrice()+" "
					+"小计："+map.get(i).getPrice()*map.get(i).getCount());
		}
	}
}
