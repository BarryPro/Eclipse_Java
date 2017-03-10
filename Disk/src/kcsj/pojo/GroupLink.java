package kcsj.pojo;

import java.util.List;
import java.util.Stack;

/**
 * 成组链接法
 * @author belong
 *
 */
public class GroupLink {
	private Stack<Integer> stack; // 存放空闲块号栈
	private List<Block> blocks; // 用于存放空间块的信息
	
	public Stack<Integer> getStack() {
		return stack;
	}
	public void setStack(Stack<Integer> stack) {
		this.stack = stack;
	}
	public List<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
}
