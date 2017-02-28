package others.belong.application;

import java.util.Stack;
public class TestReverse{
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer> ();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		new Reverse().reverse(stack);
		for(int i = 0;i<3;i++){
			System.out.println(stack.pop());
		}
	}
}

class Reverse {
	public int getAndRemoveLastElement(Stack<Integer> stack){
		int result = stack.pop();
		if(stack.empty()){//�ж�ջ�ս����ݹ�
			return result;//����ջ��Ԫ��
		} else {
			int last = getAndRemoveLastElement(stack);//�ݹ��ѯջ�����һ��Ԫ�أ�ջ��Ԫ�أ�
			stack.push(result);//�������ջ��Ԫ�ؾ�������ջ
			return last;//����ջ��Ԫ��
		}		
	}
	public void reverse(Stack<Integer> stack){
		if(stack.empty()){
			return ;
		}
		int i = getAndRemoveLastElement(stack);//�õ�ջ��Ԫ��
		reverse(stack);//�õݹ�ʵ�ְ�ԭջ�������
		stack.push(i);//�ѵ����Ԫ��ѹ��ջ(�������ľ����������)
	}
}
