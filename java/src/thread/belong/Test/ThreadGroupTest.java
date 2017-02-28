package thread.belong.Test;

public class ThreadGroupTest implements Runnable{
	public static void main(String[] args) {
		new ThreadGroupTest().func();
	}
	@SuppressWarnings("unused")
	public void func(){
		ThreadGroup pg = new ThreadGroup("父类线程组");
		ThreadGroup sg = new ThreadGroup(pg,"儿子线程组");
		Thread tp = new Thread(this);
		System.out.println("Starting"+tp.getName());
		tp.start();
		Thread ts = new Thread(this);
		System.out.println("Starting"+ts.getName());
		ts.start();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i<100;i++){
			//System.out.println(Thread.currentThread().getName()+"finally execute");
		}
		System.out.println(Thread.currentThread().getName()+"finally execute");
	}
}
