package others.belong.application;

import java.util.Timer;
import java.util.TimerTask;

public class Tupian extends TimerTask{
	private int imgcount;
	private int imgcurrent = 0;
	Timer timer;//�����ʱ���������timertask����timer�����ƣ������
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(++imgcurrent<=imgcount){
			System.out.println("��Ϸ��ʼ���ص�ͼƬ�ǵ�"+imgcurrent+"��");
		} else {
			timer.cancel();
			System.out.println("��Ϸ���سɹ���������Ϸ��");
		}
	}
	public void setImgcount(int imgcount){
		this.imgcount = imgcount;
	}
	public void setTimer(Timer timer){
		this.timer = timer;
	}
}
