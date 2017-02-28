package others.belong.application;

import java.util.Timer;
import java.util.TimerTask;

public class Tupian extends TimerTask{
	private int imgcount;
	private int imgcurrent = 0;
	Timer timer;//负责计时具体操作由timertask（由timer来控制）来完成
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(++imgcurrent<=imgcount){
			System.out.println("游戏开始加载的图片是第"+imgcurrent+"张");
		} else {
			timer.cancel();
			System.out.println("游戏加载成功！可以游戏了");
		}
	}
	public void setImgcount(int imgcount){
		this.imgcount = imgcount;
	}
	public void setTimer(Timer timer){
		this.timer = timer;
	}
}
