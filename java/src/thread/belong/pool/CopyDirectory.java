package thread.belong.pool;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyDirectory extends TimerTask {
	private static int fileCount;
	private static int tempfile;
	private Timer timer;
	
	public int getFileCount(){
		return fileCount;
	}
	
	public int getTempFile(){
		return tempfile;
	}
	public void setTimer(Timer timer){
		this.timer = timer;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int filecount = getFileCount();
		int tempfile = getTempFile();
		for(int i = 0;i<=tempfile*100/filecount;i++){	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("■");				
		}
		timer.cancel();
		System.out.println();
		System.out.println("copy finish!.");
	}
	
	private final static int THREAD_COUNT=10;
    private static ExecutorService service=Executors.newFixedThreadPool(THREAD_COUNT);
    public static void copydirectory(File fileS,File fileD) {
        if(!fileS.isDirectory()) {//是文件
        	CopyFile copyFile = new CopyFile(fileS,fileD);
        	Thread t = new Thread(copyFile);
        	t.start();
            service.submit(copyFile);
            tempfile++;
        }
        else {//是文件夹
        	if(!fileD.exists()){
        		fileD.mkdir();
        	}
        	String [] spath = fileS.list();//得到源文件的路径名的字符串表示
        	fileCount = fileCount+spath.length;
			for(String path:spath){
				File tempfileS = new File(fileS,path);//中间临时变量源文件
				File tempfileD = new File(fileD,path);//中间临时变量目的文件
				copydirectory(tempfileS,tempfileD);
            }
        }
    }
    public static void shutdown() {
        service.shutdown();
    }

}
