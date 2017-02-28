package algorithm.belong.Test;
/**
 * 
 * @author Belong
 * ���Զ��߳�
 */
public class Quick extends Thread{
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Quick q1 = new Quick();
		q1.setName("q1:");
		q1.start(); //ͨ��start �������߳�
		Quick q2 = new Quick();
		q2.setName("q2:");
		q2.start();
		long end = System.currentTimeMillis();
		System.out.println("��ʱ��"+(end-start)+"ms");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int a[] = {2,5,4,3,6,7,8,9,90,87,6,55,47};
		int start = 0;
		int end = a.length - 1;
		new Sort().quickSort(a,start,end);
		for(int i:a){
			System.out.println(Thread.currentThread().getName()+i);
		}
	}
	
}
class Sort {
	/**
	 * ����
	 * @param a
	 * @param start
	 * @param end
	 */
	public void quickSort(int [] a ,int start,int end){
		boolean flag = true; //�ȴ���������
		int i = start;
		int j = end;
		if(i>=j){
			return ;
		}
		while(i!=j){
			if(a[i]>a[j]){
				int temp = a[i];
				a[i] = a[j];
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
		quickSort(a,start,i);
		quickSort(a,j,end);
	}
}
