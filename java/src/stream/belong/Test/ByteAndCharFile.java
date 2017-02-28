package stream.belong.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteAndCharFile {
	// TODO Auto-generated constructor stub
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		InputStream is= null;
		OutputStream os = null;
		try {
			is = new FileInputStream("D:\\javafile\\inputnumber.txt");
			os = new FileOutputStream("D:\\javafile\\3.txt");
			int n = -1;
			byte[] buffer = new byte[1024];
			try {
				
				while((n = is.read(buffer))!=-1){
					String s= new String(buffer);
					System.out.println(s);
					String str[] = s.trim().split(",");
					int a[] = new int[str.length];
					for(int i = 0 ;i<str.length;i++){
						a[i] = Integer.parseInt(str[i]);
						System.out.print(a[i]);
					}
					int start = 0;
					int end = a.length-1;
					new QuickSortFile().quickSort(a, start, end);
					for(int i:a){
						//System.out.println(i);
					}
					String s2 = "";
					byte[] buffer2 = new byte[a.length];
					for(int i = 0;i<a.length;i++){
						s2 = a[i]+",";
						buffer2[i] = Byte.parseByte(str[i]);
						System.out.println(s2);
					}
					os.write(buffer2, 0, buffer2.length);
				}
				//String str[] = s.split(",");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {				
				os.flush();
				os.close();
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
