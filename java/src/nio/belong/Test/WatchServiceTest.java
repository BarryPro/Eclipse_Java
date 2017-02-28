package nio.belong.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;
public class WatchServiceTest {
	public static void main(String[] args) {
		new WatchServiceTest().watchServer();
	}
	@SuppressWarnings("rawtypes")
	public void watchServer(){
		try {
			WatchService service = FileSystems.getDefault().newWatchService();//建立检测类
			Path path = FileSystems.getDefault().getPath("D:\\javafile");//要监测的文件夹
			WatchKey wk = path.register(service,ENTRY_MODIFY);
			System.out.println("监测开始");			
			boolean flag = true;
			while(flag){
				wk = service.take();
				for(WatchEvent we:wk.pollEvents()){
					if(we.kind() == ENTRY_MODIFY||we.kind() == ENTRY_DELETE
							||we.kind() == ENTRY_CREATE||we.kind() == OVERFLOW){
						System.out.println(we.kind().name());
						Runtime.getRuntime().exec("cmd /c Start C://Windows//D8Ecap.exe");//打开摄像机
					}
				}
				wk.reset();
				flag = false;
			}
			System.out.println("检测结束了");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
