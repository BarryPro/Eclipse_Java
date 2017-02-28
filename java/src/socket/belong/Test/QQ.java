package socket.belong.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class QQ {
	private Map<String,Integer> friendlist = new HashMap<String,Integer>();
	public void launch(){		
		Set<String> list = this.friendlist.keySet();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Object o = it.next();
			MyServer server = new MyServer((String)o,this.friendlist.get(o));
			server.start();//�������߳�
		}
		
	}
	public QQ(){
		this.friendlist = new HashMap<String,Integer>();
	}
	/**
	 * ���칦��
	 */
	public void chat(){	
		int j = 0;
		int k = 0;
		int port[] = new int[this.friendlist.values().size()];
		String ip[] = new String[this.friendlist.keySet().size()];
		for(Integer i: this.friendlist.values()){
			port[j] = i;
			j++;
		}
		for(String i:this.friendlist.keySet()){
			ip[k] = i;
			k++;
		}
		for(int i = this.friendlist.size()-1;i>=0;i--){
			MyClient client = new MyClient(ip[i],port[i]);
			client.start();
		}
		
	}
	/**
	 * ���QQ����
	 * @param ip �û�IP
	 * @param port �˿�
	 */
	public void add (String ip,int port){
		this.friendlist.put(ip, port);		
	}
	/**
	 * ɾ��QQ����
	 * @param ip �û�IP
	 */
	public void delete(String ip){
		if(this.friendlist.isEmpty()){
			System.out.println("��");
		}
		this.friendlist.remove(ip);
	}
	public void printFriends(){
		Set<String> list = this.friendlist.keySet();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o+"==="+this.friendlist.get(o));
		}
	}
}
