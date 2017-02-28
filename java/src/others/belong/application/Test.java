package others.belong.application;

public class Test {
	public static void main(String[] args) {
		new Test().fun();		
	}
	public void fun(){
		System.out.println("加载中...");
		for(int i=0 ;i<=20;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.print("■");	
			if(i==20){
				System.out.println();
				System.out.println("加载完成！");
			}
		}
	}
}
