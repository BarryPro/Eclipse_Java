package algorithm.belong.Test;

public class QuickSort {
	public void quickSort(int[] a, int start, int end) {
		boolean flag = true; // ÏÈ´ÓÓÒÍù×óÕÒ
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
