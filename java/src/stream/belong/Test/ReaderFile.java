package stream.belong.Test;

import java.io.FileInputStream;
import java.io.InputStream;
/**
 * 测试读如字节输出是乱码
 * @author belong
 *
 */
public class ReaderFile {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		InputStream is = null;
		try {
			is = new FileInputStream("D:\\JavaFile\\2.txt");
			byte [] buffer = new byte[1024];
			int n = -1;
			while((n = is.read(buffer))!=-1){
				System.out.println(new String(buffer));
			}
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
