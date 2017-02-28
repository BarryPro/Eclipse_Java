package stream.belong.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class QuickSortFile {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Reader read = null;
		Writer write = null;
		try {
			read = new FileReader("D:\\javafile\\inputnumber.txt");
			write = new FileWriter("D:\\javafile\\outputnumber.txt");
			int n = -1;
			char[] buffer = new char[1024];
			while ((n = read.read(buffer)) != -1) {
				System.out.println(buffer);

			}

			String s = new String(buffer);
			String[] str = s.trim().split(",");
			int[] a = new int[str.length];
			for (int i = 0; i < str.length; i++) {
				a[i] = Integer.parseInt(str[i]);//把字符串数组转换成整数数组
			}
			int start = 0;
			int end = a.length - 1;
			new QuickSortFile().quickSort(a, start, end);
			for (int i = 0;i<a.length;i++) {
				System.out.print((i<end)?(a[i]+" "):a[i]+"\n");
			}
			String s2 = "";
			for (int i = 0; i < a.length; i++) {//把有序的字符串数组转换成字符串在写到输出文件中
				if (i < a.length-1) {
					s2 += a[i] + ",";
				}
				else {
					s2 += a[i];
				}
			}

			write.write(s2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				read.close();
				write.flush();
				write.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 快排
	 * @param a
	 * @param start
	 * @param end
	 */
	public void quickSort(int a[], int start, int end) {
		boolean flag = true;
		int i = start;
		int j = end;
		if (i >= j) {
			return;
		}
		while (i != j) {
			if (a[i] > a[j]) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				flag = !flag;
			}
			if (flag) {
				j--;
			} else {
				i++;
			}
		}
		i--;
		j++;
		quickSort(a, start, i);
		quickSort(a, j, end);
	}
}
