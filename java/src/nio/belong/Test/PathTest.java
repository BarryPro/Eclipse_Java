package nio.belong.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PathTest {
	public static void main(String[] args) {
		new PathTest().lookFile();
	}
	public void lookFile(){
		Path path = Paths.get("d:\\javafile");//����·����
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>(){//ͨ���������Ŀ¼�µ������ļ�
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					if(file.toString().endsWith(".txt")){//����.txt�ļ�
						//������дҪʵ�ֵ�����
						System.out.println(file.getParent()+"==="+file.getFileName());
					}
					return FileVisitResult.CONTINUE;//ʵ�֣��ݹ飩
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
