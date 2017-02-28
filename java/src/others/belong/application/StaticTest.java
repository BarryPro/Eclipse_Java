package others.belong.application;

public class StaticTest {
	public static void testMethod(){
		System.out.println("outtest");
	}
	public static void main(String[] args) {
		((StaticTest)null).testMethod();
	}
}
