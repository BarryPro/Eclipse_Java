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
		Path path = Paths.get("d:\\javafile");//声明路径类
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>(){//通过该类遍里目录下的所有文件
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					if(file.toString().endsWith(".txt")){//查找.txt文件
						//在这里写要实现的内容
						System.out.println(file.getParent()+"==="+file.getFileName());
					}
					return FileVisitResult.CONTINUE;//实现（递归）
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
