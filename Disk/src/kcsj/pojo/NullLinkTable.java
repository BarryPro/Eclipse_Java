package kcsj.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 名称：空闲链表法
 * 功能：实现把空闲的磁盘以链表的形式存储，来进行分配
 * @author belong
 *
 */
public class NullLinkTable {
	public Map<String,ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
	public Block block; //盘块信息
	public Block next; //下一个盘块
	
	public class Node{
		public Block value; // 用于存储磁盘块的信息
		public Node next; // 指向下一个盘块
		
		Node(Block value,Node next){
			this.value = value;
			this.next = next;
		}
	}
	
	private Node head; // 头结点：首盘块
	private Node tail; // 尾节点：尾盘块
	private int size;
	
	/**
	 * 插入节点
	 * 尾插入的方式
	 * @param block
	 */
	public void insert(Block block){
		if(head == null){
			head = new Node(block,null);
			tail = head;
		} else {
			Node node = new Node(block,null);
			tail.next = node;
			tail = node;			
		}
		size++; // 盘块数加一
	}
	
	/**
	 * 头删除方法
	 * @param num:要删除的磁盘数量
	 */
	public void delete(int num){
		for(int i = 0;i<num;i++){
			this.head = this.head.next; //头的下一个给头，实现删除
			this.size--;
		}
	}
	
	/**
	 * 返回空闲链表的大小：也就是磁盘的空闲空间的大小
	 * @return
	 */
	public int getSize(){
		return this.size;
	}
	
	

}
