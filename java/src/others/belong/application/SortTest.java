package others.belong.application;

public class SortTest {
	public static void main(String[] args) {
		int a[] = {2,4,5,3,25,7,4,10,74,7};
		int start = 0;
		int end = a.length-1;
		new SortTest().quicksort(a, start, end);
		for(int i:a){
			System.out.println(i);
		}
	}
	public void quicksort(int a[],int start,int end){
		boolean flag = true;//¥””“œÚ◊ÛÀ—À˜  false £∫¥”◊ÛœÚ”“À—À˜
		int i = start;
		int j = end;
		if(i>=j){
			return;
		}
		while(i!=j){
			if(a[i]>a[j]){
				int temp = a[i];
				a[i]=a[j];
				a[j] = temp;
				flag = !flag;
			}
			if(flag){
				j--;
			} else {
				i++;
			}
		}
		i--;
		j++;
		quicksort(a,start,i);//µ›πÈ≤È’“
		quicksort(a,j,end);
	}
}
