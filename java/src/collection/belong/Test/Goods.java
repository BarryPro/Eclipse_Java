package collection.belong.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	Map<String,String > map = new HashMap<String,String>();
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public Goods(Map<String, String> map, int count, float price) {
		super();
		this.map = map;
		this.count = count;
		this.price = price;
	}
	private int count;
	private float price;	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}	
}