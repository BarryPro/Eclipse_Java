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
	public void AsychronousFilechannel(){//�첽���̶߳�ȡ�ļ�
		Path path = Paths.get("D:\\javafile\\10.txt");
		try {
			AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer buffer = ByteBuffer.allocate(1024);//���ٻ���1k
			Future<Integer> fu = afc.read(buffer, 0);
			while(!fu.isDone()){//�ڲ��Ƕ�ȡ������ִ���������
				System.out.println("ok");
			}
			System.out.println("��С�ǣ�"+fu.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void callRead(){//�ص���ȡ
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
	
	public void ReadChannel(){//����ͨ������
		Path path = Paths.get("D:\\javafile\\10.txt");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try (FileChannel pc = FileChannel.open(path, StandardOpenOption.READ);){			
			pc.read(buffer);
			buffer.flip();//�����ļ�ͷ������
			System.out.println(new String(buffer.array()));//��buffer�е����ݶ�����
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
			pc.write(ByteBuffer.wrap("�����".getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setAttribute(){//��������
		Path source = Paths.get("D:\\javafile\\5.mp3");//����һ���յ��ļ�
		DosFileAttributeView view = Files.getFileAttributeView(source, DosFileAttributeView.class);
		try {
			view.setReadOnly(true);//���ó�ֻ����
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void creatFile(){
		Path source = Paths.get("D:\\javafile\\5.mp3");//����һ���յ��ļ�
		try {
			Files.createFile(source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fundetele(){//����ɾ���ļ�
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
			//�൱�ڼ���
			Files.move(source, target, REPLACE_EXISTING,ATOMIC_MOVE);//�������Ѵ��ڵ���ͬ���͵��ļ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void funcopyFile(){//��������copy
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
