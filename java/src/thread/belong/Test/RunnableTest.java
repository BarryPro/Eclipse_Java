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
		t1.setDaemon(true);//�ػ��̵߳����ֳ�����run�����ſ���
		t1.start();
		// A a = new A();
		// a.setName("A:");
		// a.setDaemon(true);//�ػ��߳̾��ǵȵ������̣߳������̣߳���������������ֹͣ���У�����û�������ǰ���£�
		// a.start();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// try {//join������start�����ĺ���
			// //t1.join();//��˭���߳������˭�ͱ�������ֱ������join�Ķ����߳��Ѿ�ִ������
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

			// Thread.currentThread().yield();//���Լ��׵��γ��߳̽��棨���ϸ�
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // �׳��ж��쳣 ����״̬wait sleep join
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
			} // �׳��ж��쳣 ����״̬wait sleep join
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}