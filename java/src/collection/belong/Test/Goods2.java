package collection.belong.Test;
/**
 * 物品定义
 * @author belong
 *
 */
public class Goods2 {
	private int count;//数量
	private double price;//价格
	private int no;//编号
	private String name;//物品名
	public Goods2(int no, String name,int count, double price) {
		super();
		this.count = count;
		this.price = price;
		this.no = no;
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
