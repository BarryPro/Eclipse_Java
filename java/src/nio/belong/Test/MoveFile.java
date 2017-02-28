package nio.belong.Test;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.concurrent.Future;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import java.io.IOException;
public class MoveFile {
	public static void main(String[] args) {
//		new MoveFile().funcopyFile();
//		new MoveFile().funMove();
		new MoveFile().AsychronousFilechannel();
	}
	public void AsychronousFilechannel(){//异步多线程读取文件
		Path path = Paths.get("D:\\javafile\\10.txt");
		try {
			AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer buffer = ByteBuffer.allocate(1024);//开辟缓存1k
			Future<Integer> fu = afc.read(buffer, 0);
			while(!fu.isDone()){//在不是读取结束就执行下面语句
				System.out.println("ok");
			}
			System.out.println("大小是："+fu.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void callRead(){//回调读取
		Path path = Paths.get("D:\\javafile\\10.txt");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			afc.read(buffer, 0,null,new CompletionHandler(){

				@Override
				public void completed(Object result, Object attachment) {
					// TODO Auto-generated method stub
					System.out.println("successful");
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					// TODO Auto-generated method stub
					System.out.println("failed");
				}				
			});
			System.out.println("end");
			afc.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReadChannel(){//采用通道技术
		Path path = Paths.get("D:\\javafile\\10.txt");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try (FileChannel pc = FileChannel.open(path, StandardOpenOption.READ);){			
			pc.read(buffer);
			buffer.flip();//返回文件头继续读
			System.out.println(new String(buffer.array()));//把buffer中的内容读出来
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writechannel(){
		Path path = Paths.get("D:\\javafile\\10.txt");
//		try {
//			Files.createFile(path);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try (FileChannel pc = FileChannel.open(path, StandardOpenOption.APPEND);){			
			pc.write(ByteBuffer.wrap("你哈哈".getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setAttribute(){//设置属性
		Path source = Paths.get("D:\\javafile\\5.mp3");//创建一个空的文件
		DosFileAttributeView view = Files.getFileAttributeView(source, DosFileAttributeView.class);
		try {
			view.setReadOnly(true);//设置成只读的
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void creatFile(){
		Path source = Paths.get("D:\\javafile\\5.mp3");//创建一个空的文件
		try {
			Files.createFile(source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fundetele(){//正常删除文件
		Path source = Paths.get("D:\\javafile\\2.mp3");
		try {
			Files.delete(source);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void funMove(){
		Path source = Paths.get("D:\\javafile\\2.mp3");
		Path target = Paths.get("D:\\javafile\\777\\1\\1.mp3");
		try {
			//相当于剪切
			Files.move(source, target, REPLACE_EXISTING,ATOMIC_MOVE);//必须是已存在的相同类型的文件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void funcopyFile(){//就是正常copy
		Path source = Paths.get("D:\\javafile\\2.mp3");
		Path target = Paths.get("D:\\javafile\\777\\1\\23.mp3");
		try {
			Files.copy(source, target, COPY_ATTRIBUTES,REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
