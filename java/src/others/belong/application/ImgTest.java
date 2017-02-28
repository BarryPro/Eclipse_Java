package others.belong.application;

import java.util.Timer;

public class ImgTest {
	public static void main(String[] args) {
		Tupian t = new Tupian();
		t.setImgcount(10);
		Timer timer = new Timer();
		t.setTimer(timer);
		timer.schedule(t, 0,100);
	}
}
