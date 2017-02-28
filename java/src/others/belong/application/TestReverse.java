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
		if(stack.empty()){//判断栈空结束递归
			return result;//返回栈底元素
		} else {
			int last = getAndRemoveLastElement(stack);//递归查询栈的最后一个元素（栈底元素）
			stack.push(result);//如果不是栈底元素就重新入栈
			return last;//返回栈底元素
		}		
	}
	public void reverse(Stack<Integer> stack){
		if(stack.empty()){
			return ;
		}
		int i = getAndRemoveLastElement(stack);//得到栈底元素
		reverse(stack);//用递归实现把原栈倒序输出
		stack.push(i);//把倒序的元素压入栈(最后输出的就是正序的了)
	}
}
