package others.belong.application;

import java.util.Scanner;

public class QuickSortTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("��ӭʹ�ÿ����㷨\nauthor:belong\n������һ������n��eg��5��"
				+ "\n������������n��������eg��2��4��6��3��2��");
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
			System.out.println("���Ž�����£�");
			for(int i:a){
				System.out.println(i);
			}
		}
	}
	public void quickSort(int[] a, int start, int end) {
		boolean flag = true; // �ȴ���������
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
