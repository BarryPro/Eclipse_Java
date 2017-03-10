package com.belong.oracle;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import oracle.net.aso.e;

public class SQLTest {
	public static void main(String[] args) {
		new SQLTest().replace();
	}
	
	private void replace(){
		try {
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("sql.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
