//package myjavaqq.belong.com;
//
//import java.net.*;
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
//import java.util.*;
//import javax.swing.JDialog;
//
//@SuppressWarnings("serial")
//public class AboutQQ extends JFrame implements ActionListener, Runnable { // 定义qq界面所需的组件
//	static JPanel p1 = new JPanel(), p2 = new JPanel();
//	TextArea output = new TextArea("", 20, 18, TextArea.SCROLLBARS_BOTH),
//			input = new TextArea("", 20, 18, TextArea.SCROLLBARS_VERTICAL_ONLY);
//	JButton b_biaoqing = new JButton("表情");
//	JButton b_zhiti = new JButton("字体");
//	JButton b_zhitiys = new JButton("字体颜色");
//	JButton b_jilu = new JButton("清空消息");
//	JButton b_fasong = new JButton("发送");
//
//	JLabel lb1 = new JLabel(" 对方IP");
//	JTextField IPAdd = new JTextField("192.168.4.88", 15);
//	String s, kongzhi;// 用于控制和判断的变量
//	int port = 5858;
//	InetAddress group = null;
//	MulticastSocket socket = null;
//	// 群聊和私聊的线程
//	Thread thread1 = new Thread(this);
//	Thread thread2 = new Thread(this);
//
//	JButton siliao = new JButton("私聊");
//	JButton qunliao = new JButton("群聊");
//	Color ys = new Color(157, 242, 173);
//	caidan cd = new caidan(); // 获得菜单对象
//
//	public AboutQQ() { // 布局qq界面
//		super("小小聊天室");
//		setMenuBar(cd);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		b_jilu.addActionListener(this);
//		b_fasong.addActionListener(this);
//		b_biaoqing.addActionListener(this);
//		b_zhiti.addActionListener(this);
//		b_zhitiys.addActionListener(this);
//		siliao.addActionListener(this);
//		qunliao.addActionListener(this);
//
//		Container cc = getContentPane();
//		setBounds(150, 150, 600, 510);
//		cc.setLayout(new BorderLayout());
//		output.setBackground(Color.white);
//		output.setForeground(Color.blue);
//		output.setBounds(4, 0, 420, 250);
//		output.setFont(new Font("楷体", Font.PLAIN, 14));
//		output.setEditable(false);
//
//		p1.setLayout(null);
//		p1.setBackground(new Color(220, 220, 220));
//		p1.add(output);
//		b_biaoqing.setBounds(4, 254, 60, 30);
//		p1.add(b_biaoqing);
//		b_zhiti.setBounds(65, 254, 60, 30);
//		p1.add(b_zhiti);
//		b_zhitiys.setBounds(126, 254, 90, 30);
//		p1.add(b_zhitiys);
//		input.setFont(new Font("楷体", Font.PLAIN, 14));
//		input.setBackground(Color.white);
//		input.setForeground(Color.blue);
//		input.setBounds(4, 290, 420, 125);
//		p1.add(input);
//		b_jilu.setBounds(4, 420, 120, 30);
//		p1.add(b_jilu);
//		b_fasong.setBounds(360, 420, 60, 30);
//		p1.add(b_fasong);
//		p1.setBackground(ys);
//		cc.add(p1, "Center");
//
//		lb1.setFont(new Font("楷体", Font.BOLD, 18));
//		lb1.setBackground(ys);
//		IPAdd.setEditable(false);
//		p2.setBackground(ys);
//		p2.setLayout(new GridLayout(10, 1, 1, 10));
//		p2.add(lb1, "Center");
//		p2.add(IPAdd);
//		p2.add(siliao);
//		p2.add(qunliao);
//		cc.add(p2, "East");
//		setVisible(true);
//	}
//
//	public void AboutQQ1() {
//	}
//
//	public AboutQQ(AboutQQ aboutQQ) {
//		// TODO Auto-generated constructor stub
//	}
//
//	// 执行按钮动作的方法
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == b_fasong) {
//			String ss = input.getText();
//			if (kongzhi == "sl") {
//				if (ss.equals("")) {
//					AboutQQ test = new AboutQQ(this);
//				} else {
//					sendData1();
//				}
//
//			} else if (kongzhi == "ql")
//				if (ss.equals("")) {
//					AboutQQ test = new AboutQQ(this);
//				} else {
//					sendData2();
//				}
//			else {
//				JOptionPane.showMessageDialog(this, "对不起！你还没选择聊天方式，请先选择聊天方式！", "提醒", JOptionPane.WARNING_MESSAGE);
//			}
//
//		} else if (e.getSource() == b_zhiti) {
//			ziti zt = new ziti(this);
//			input.setFont(zt.f);
//			output.setFont(zt.f);
//		} else if (e.getSource() == b_zhitiys) {
//			Color newcolor = JColorChooser.showDialog(this, "调色板", input.getForeground());
//			input.setForeground(newcolor);
//			output.setForeground(newcolor);
//		} else if (e.getSource() == b_jilu) {
//			output.setText("");
//		} else if (e.getSource() == siliao) {
//			String ip = JOptionPane.showInputDialog("请输入对方的IP地址：", "192.168.4.13");
//			if (ip != null) {
//				IPAdd.setText(ip);
//			}
//			kongzhi = "sl";
//			if (!(thread1.isAlive())) {
//				thread1 = new Thread(this);
//			}
//			try {
//				thread1.start();
//			} catch (Exception eee) {
//			}
//
//		} else if (e.getSource() == qunliao) {
//			thread1.interrupt();
//			int n = JOptionPane.showConfirmDialog(null, "你确定要群聊吗？你的消息会被所有人看到！！", "警告", JOptionPane.YES_NO_OPTION,
//					JOptionPane.INFORMATION_MESSAGE);
//			JOptionPane.showMessageDialog(this, "你最好先设置好你的昵称，然后再进行聊天！", "温馨提醒", JOptionPane.WARNING_MESSAGE);
//			if (n == JOptionPane.YES_OPTION)
//				kongzhi = "ql";
//			if (!(thread2.isAlive())) {
//				thread2 = new Thread(this);
//			}
//
//			try {
//				thread2.start();
//			} catch (Exception eee) {
//			}
//		}
//	}
//
//	// 发送及接受数据的方法
//	void sendData1() {
//		try {
//			Calendar rightNow = Calendar.getInstance(); // 获取当前系统日期和时间
//			int hour = rightNow.get(Calendar.HOUR_OF_DAY); // 获取当前时间的整点数
//			int year = rightNow.get(Calendar.YEAR);
//			int month = rightNow.get(Calendar.MONTH) + 1;
//			int day = rightNow.get(Calendar.DATE);
//			int minute = rightNow.get(Calendar.MINUTE);
//			int second = rightNow.get(Calendar.SECOND);
//
//			String msg = input.getText();
//			if (msg.equals("")) {
//				return;
//			}
//			input.setText("");
//			String ad = IPAdd.getText();
//			InetAddress tea = InetAddress.getLocalHost();
//			String asd = tea.getHostAddress(); // 发送方的IP地址
//			output.append(cd.nicheng1 + "(" + asd + ") " + year + "-" + month + "-" + day + " " + hour + ":" + minute
//					+ ":" + second + "\n" + " " + msg + "\n");
//			msg = cd.nicheng2 + "(" + asd + ") " + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
//					+ second + "\n" + " " + msg + "\n";
//			InetAddress address = InetAddress.getByName(ad);
//			byte[] message = msg.getBytes();
//			DatagramPacket packet = new DatagramPacket(message, message.length, address, 9999);
//			DatagramSocket socket = new DatagramSocket();
//			socket.send(packet);
//		} catch (Exception e) {
//		}
//	}
//
//	void sendData2() {
//		Calendar rightNow = Calendar.getInstance(); // 获取当前系统日期和时间
//		int hour = rightNow.get(Calendar.HOUR_OF_DAY); // 获取当前时间的整点数
//		int year = rightNow.get(Calendar.YEAR);
//		int month = rightNow.get(Calendar.MONTH) + 1;
//		int day = rightNow.get(Calendar.DATE);
//		int minute = rightNow.get(Calendar.MINUTE);
//		int second = rightNow.get(Calendar.SECOND);
//
//		try {
//			group = InetAddress.getByName("239.255.8.0");
//			socket = new MulticastSocket(port);
//			socket.setTimeToLive(1);
//			socket.joinGroup(group);
//
//			s = input.getText();
//			if (s.equals("")) {
//				return;
//			}
//			String ss = (cd.nicheng1 + " " + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second
//					+ "\n" + " " + s + "\n");
//
//			input.setText("");
//			DatagramPacket packet = null;
//			byte date[] = ss.getBytes();
//			packet = new DatagramPacket(date, date.length, group, port);
//			socket.send(packet);
//		} catch (Exception e) {
//			System.out.println("Error:" + e);
//		}
//	}
//
//	// 等待接受数据的方法
//	public void run() {
//		if (Thread.currentThread() == thread1) {// 负责私聊的线程
//			try {
//				byte[] buffer = new byte[1024];
//				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//				DatagramSocket socket = new DatagramSocket(9999);
//				while (true) {
//					socket.receive(packet);
//					String s = new String(packet.getData(), 0, packet.getLength());
//					output.append(s + "\n");
//					packet = new DatagramPacket(buffer, buffer.length);
//				}
//			} catch (Exception e) {
//			}
//		}
//		if (Thread.currentThread() == thread2) {// 负责群聊的线程
//
//			try {
//				while (true) {
//					group = null;
//					group = InetAddress.getByName("239.255.8.0");
//					socket = new MulticastSocket(port);
//					socket.joinGroup(group);
//					byte[] buffer = new byte[8192];
//					DatagramPacket packet = null;
//					packet = new DatagramPacket(buffer, buffer.length, group, port);
//
//					socket.receive(packet);
//					String message = new String(packet.getData(), 0, packet.getLength());
//					output.append(message);
//					packet = new DatagramPacket(buffer, buffer.length);
//				}
//			} catch (Exception e) {
//			}
//		}
//	}
//}