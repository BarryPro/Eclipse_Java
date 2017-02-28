package thread.belong.pool;

import java.io.File;
import java.util.Timer;

public class Test {
	public static void main(String[] args) {
		File fileS = new File("d:\\JavaFile");
		File fileD = new File("e:\\ww");
		System.out.println("线程池拷贝进度条");
		System.out.println("copying...");
		Timer timer = new Timer();
		CopyDirectory copyDirectory = new CopyDirectory();
		copyDirectory.setTimer(timer);
		timer.schedule(copyDirectory, 10);		
		CopyDirectory.copydirectory(fileS, fileD);
		CopyDirectory.shutdown();		
	}
}
