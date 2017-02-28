package others.belong.application;

import java.util.Scanner;

public class QuickSortTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("欢迎使用快排算法\nauthor:belong\n请输入一个整数n（eg：5）"
				+ "\n接下来请输入n个整数（eg：2，4，6，3，2）");
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			int a[] = new int[n];
			int start = 0;
			int end = a.length-1;
			for(int i = 0;i<n;i++){
				a[i] = in.nextInt();
			}
			new QuickSortTest().quickSort(a, start, end);
			System.out.println("快排结果如下：");
			for(int i:a){
				System.out.println(i);
			}
		}
	}
	public void quickSort(int[] a, int start, int end) {
		boolean flag = true; // 先从右往左找
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
