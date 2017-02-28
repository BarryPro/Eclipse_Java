package collection.belong.Test;

import java.util.HashMap;
import java.util.Map;
/**
 * ���ﳵ
 * @author belong
 *
 */
public class Cart2 {
	/**
	 * 
	 * @param map
	 * @param newgood ����Ʒ
	 */
	public void buyGoods(Map<Integer, Goods2> map,Goods2 newgood){
		boolean flag = false;//�����Ƿ������Ʒһ��
		for(int i:map.keySet()){
			if(i==newgood.getNo()){//�ж�����Ʒ�Ƿ����
				map.get(i).setCount(map.get(i).getCount()+newgood.getCount());
				flag = true;//����Դ���
			}
		}
		if(!flag){//���û������Ʒ����ӹ��ﳵ
			map.put(newgood.getNo(),newgood);
		}
	}
	/**
	 * ��ʼ�����ﳵ
	 * @return map
	 */
	public Map<Integer, Goods2> cartGoods(){
		Map<Integer, Goods2> map = new HashMap<Integer, Goods2>();
		Goods2 good1 = new Goods2(1,"���",2,1.5);
		Goods2 good2 = new Goods2(2,"�㽶",5,1.5);
		Goods2 good3 = new Goods2(3,"�ƹ�",10,1.2);
		map.put(good1.getNo(), good1);
		map.put(good2.getNo(), good2);
		map.put(good3.getNo(), good3);
		return map;
	}
	/**
	 * ������ﳵ����Ʒ��С��
	 * @param map
	 */
	public void showCart(Map<Integer, Goods2> map){
		for(int i:map.keySet()){
			System.out.println("���: "+i+" "
					+"��Ʒ����"+map.get(i).getName()+" "
					+"��Ʒ������"+map.get(i).getCount()
					+"�۸�"+map.get(i).getPrice()+" "
					+"С�ƣ�"+map.get(i).getPrice()*map.get(i).getCount());
		}
	}
}
