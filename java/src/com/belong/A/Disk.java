package com.belong.A;

import java.util.HashMap;
import java.util.Map;

public class Disk {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("f", "1");
		map.put("g", "2");
		System.out.println(map.containsKey("f"));
	}

}
