package thread.belong.Test;

public class RunnableTest implements Runnable {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// RunnableTest r = new RunnableTest();
		// Thread t = new Thread(r);
		// t.setName("r:");
		// // t.setPriority(1);
		// t.start();
		// // t.yield();
		RunnableTest r1 = new RunnableTest();
		Thread t1 = new Thread(r1);
		t1.setName("r1:");
		// t1.setPriority(5);
		t1.setDaemon(true);//守护线程得体现出两个run方法才可以
		t1.start();
		// A a = new A();
		// a.setName("A:");
		// a.setDaemon(true);//守护线程就是等到其他线程（所有线程）都运行完了他才停止运行（在他没运行完的前提下）
		// a.start();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// try {//join必须在start方法的后面
			// //t1.join();//在谁的线程里调用谁就被阻塞，直到调用join的对象线程已经执行完了
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
	// new Thread(new Runnable(){
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// for(int i = 0;i<100;i++){
	// System.out.println(Thread.currentThread().getName()+i);
	// }
	// }
	//
	// }).start();


	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {

			// Thread.currentThread().yield();//可以简易的形成线程交叉（不严格）
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 抛出中断异常 阻塞状态wait sleep join
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}

class A extends Thread {
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 抛出中断异常 阻塞状态wait sleep join
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}