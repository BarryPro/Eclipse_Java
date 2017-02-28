package others.belong.application;

import java.io.IOException;

public class Hello {
	public static void main(String[] args) {
		try {
			/*文件路径及其可执行文件名。
			C://Windows//notepad.exe指c盘下的notepad.exe文件。
			cmd /c Start 固定形式开始.exe文件*/
			Runtime.getRuntime().exec("cmd /c Start G://软件大全//相机//conewpaizhao//conewpaizhao.exe ");			
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}	
}
