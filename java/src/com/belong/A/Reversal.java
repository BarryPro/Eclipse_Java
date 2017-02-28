package com.belong.A;

public class Reversal {
	public static void main(String[] args) {
		int a[] = {1,2,4,5,6,7,8};
		new Reversal().reversal(a,a.length);
		for(int i:a){
			System.out.print(i+" ");
		}
	}
	public void reversal(int a[],int n){
		for(int i = 0;i<n/2;i++){
			int tmp = a[i];
			a[i] = a[n-1-i];
			a[n-1-i] = tmp;
		}
	}
}
