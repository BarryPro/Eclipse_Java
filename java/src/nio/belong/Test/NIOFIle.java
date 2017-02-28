package nio.belong.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOFIle {
	public static void main(String[] args) {
		new NIOFIle().readallLine();
	}
	public void readallLine(){//�򵥶�ȡ
		Path source = Paths.get("d:\\javafile\\belong.txt");
		try {
			byte[] buffer = Files.readAllBytes(source);//�����е����ݶ��ŵ��ֽ�������
			System.out.println(new String(buffer,StandardCharsets.UTF_8));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void reader(){
		Path source = Paths.get("d:\\javafile\\belong.txt");
		try (BufferedReader reader = Files.newBufferedReader(source,StandardCharsets.UTF_8)){
			String line = null;
			while((line = reader.readLine())!=null){
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void writer(){//д����
		Path source = Paths.get("d:\\javafile\\belong.txt");
		try {
			Files.createFile(source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�����Զ��ر���
		try (BufferedWriter writer = Files.newBufferedWriter(source,StandardCharsets.UTF_8, StandardOpenOption.WRITE)){
			writer.write("�Ұ����й�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
