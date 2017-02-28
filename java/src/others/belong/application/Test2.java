package others.belong.application;

public class Test2 {
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("we are family");
		String str = s.toString();
		System.out.println(str.replaceAll(" ", "%20"));
	}
}
