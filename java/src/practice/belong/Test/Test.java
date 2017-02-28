package practice.belong.Test;

public class Test {
	public static void main(String[] args) {
		B b1 = new B();
		b1.start();
		B b2 = new B();
		b2.start();
		B b3 = new B();
		b3.start();
		
	}
}
class B extends Thread{
	C c = new C();
	@Override	
	public void run() {
		// TODO Auto-generated method stub
		c.setI(c.getI()+1);
		System.out.println(c.getI());
		
	}
	{
		//System.out.println(c);
	}
}
class C {
	private static int i = 0;
	public int getI(){
		return i;
	}
	@SuppressWarnings("static-access")
	public void setI(int i){
		this.i = i;
	}
}