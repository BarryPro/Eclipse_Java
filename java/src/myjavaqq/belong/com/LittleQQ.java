//package myjavaqq.belong.com;
//
///*public static void main(String args[]) { 
//new AboutQQ(null); 
//}
//
//}*/
////��ʾ�Ի����� 
//public class LittleQQ implements ActionListener {
//	private JLabel label;
//	private JButton queding;
//	private JDialog dialog;
//
//	public void AboutQQ(JFrame f) {
//		label = new JLabel("���ܷ��Ϳ���Ϣ");
//		queding = new JButton("Yes");
//		dialog = new JDialog(f, "��ʾ", true);
//		dialog.setLocation(350, 380);
//		dialog.setSize(80, 115);
//		dialog.setResizable(false);
//		Container dialogPane = dialog.getContentPane();
//		dialogPane.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 15));
//		dialogPane.add(label);
//		dialogPane.add(queding);
//		queding.addActionListener(this);
//		dialog.show();
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		dialog.show(false);
//	}
//}
//
//// qq�˵���
//class caidan extends MenuBar implements ActionListener {
//	String nicheng1 = "��"; // ����Ĭ���ǳ�
//	String nicheng2 = "�Է�";
//	private Menu m1, m2, m3, m4, m5, m6;
//	private MenuItem m11, m12, m13, m22, m23, m31, m51, m52, m53, m54, m55, m61, m62, m63, m64, m65, m66, m67, m68, m69,
//			mm;
//
//	public caidan() {
//		m1 = new Menu("����");
//		m2 = new Menu("����");
//		m3 = new Menu("����");
//		m4 = new Menu("����");
//		m5 = new Menu("��Ϸ");
//		m6 = new Menu("������ɫ");
//
//		m11 = new MenuItem("��������");
//		m12 = new MenuItem("��Ƶ����");
//		m13 = new MenuItem("�ļ�����");
//
//		m22 = new MenuItem("����һ��");
//		m23 = new MenuItem("��������");
//		m51 = new MenuItem("̰����");
//		m52 = new MenuItem("ƴͼ");
//		m53 = new MenuItem("Χ�����");
//		m54 = new MenuItem("���ݵ�");
//		m55 = new MenuItem("����˹����");
//
//		m31 = new MenuItem("�ҵ��ǳ�");
//		m61 = new MenuItem("��ʽ1");
//		m62 = new MenuItem("��ʽ2");
//		m63 = new MenuItem("��ʽ3");
//		m64 = new MenuItem("��ʽ4");
//		m65 = new MenuItem("��ʽ5");
//		m66 = new MenuItem("��ʽ6");
//		m67 = new MenuItem("��ʽ7");
//		m68 = new MenuItem("��ʽ8");
//		m69 = new MenuItem("��ʽ9");
//		mm = new MenuItem("Ĭ��");
//
//		m1.add(m11);
//		m11.addActionListener(this);
//		m1.add(m12);
//		m12.addActionListener(this);
//		m1.add(m13);
//		m13.addActionListener(this);
//		m2.add(m5);
//		m2.add(m22);
//		m22.addActionListener(this);
//		m2.add(m23);
//		m23.addActionListener(this);
//		m5.add(m51);
//		m51.addActionListener(this);
//		m5.add(m52);
//		m52.addActionListener(this);
//		m5.add(m53);
//		m53.addActionListener(this);
//		m5.add(m54);
//		m54.addActionListener(this);
//		m5.add(m55);
//		m55.addActionListener(this);
//
//		m3.add(m31);
//		m31.addActionListener(this);
//		m3.add(m6);
//		m6.add(m61);
//		m61.addActionListener(this);
//		m6.add(m62);
//		m62.addActionListener(this);
//		m6.add(m63);
//		m63.addActionListener(this);
//		m6.add(m64);
//		m64.addActionListener(this);
//		m6.add(m65);
//		m65.addActionListener(this);
//		m6.add(m66);
//		m66.addActionListener(this);
//		m6.add(m67);
//		m67.addActionListener(this);
//		m6.add(m68);
//		m68.addActionListener(this);
//		m6.add(m69);
//		m69.addActionListener(this);
//		m6.add(mm);
//		mm.addActionListener(this);
//		add(m1);
//		add(m2);
//		add(m3);
//		add(m4);
//
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == m51) {
//
//		} else if (e.getSource() == m52) {
//
//		}
//
//		else if (e.getSource() == m53) {
//
//		} else if (e.getSource() == m54) {
//		} else if (e.getSource() == m55) {
//
//		} else if (e.getSource() == m22) {
//
//		} else if (e.getSource() == m23) {
//
//		} else if (e.getSource() == m31) {
//			String nicheng = JOptionPane.showInputDialog("����������ǳƣ�");
//			nicheng1 = nicheng;
//			nicheng2 = nicheng;
//		} else if (e.getSource() == m61) {
//			Color ys = new Color(77, 192, 221);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m62) {
//			Color ys = new Color(45, 168, 118);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m63) {
//			Color ys = new Color(245, 163, 238);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//
//		} else if (e.getSource() == m64) {
//			Color ys = new Color(148, 157, 55);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//
//		} else if (e.getSource() == m65) {
//			Color ys = new Color(240, 162, 142);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m66) {
//			Color ys = new Color(148, 190, 252);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m67) {
//			Color ys = new Color(253, 181, 196);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m68) {
//			Color ys = new Color(182, 162, 230);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == m69) {
//			Color ys = new Color(237, 250, 116);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		} else if (e.getSource() == mm) {
//			Color ys = new Color(157, 242, 173);
//			AboutQQ.p1.setBackground(ys);
//			AboutQQ.p2.setBackground(ys);
//		}
//
//	}
//
//}
//
//// ����Ի�����
//class ziti implements ActionListener, ItemListener {
//	private JLabel lb1 = new JLabel("����"), lb2 = new JLabel("����"), lb3 = new JLabel("��С");
//	JTextField text1 = new JTextField("����"), text2 = new JTextField("PLAIN"), text3 = new JTextField("14", 2);
//	java.awt.List list1 = new java.awt.List(6, false), list2 = new java.awt.List(6, false),
//			list3 = new java.awt.List(6, false);
//	private JButton b_queding = new JButton("ȷ��"), b_quxiao = new JButton("ȡ��");
//	String zixing[] = { "����", "б��", "����" };
//	String daxiao[] = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "34" };
//	private JDialog dialog;
//	Font f;
//	String aa = "����";
//	int cc = 14;
//
//	// ����Ի�����Ĺ��췽��
//	public ziti(JFrame f) {
//		dialog = new JDialog(f, "����", true);
//		dialog.setLocation(150, 150);
//		dialog.setSize(400, 230);
//		dialog.setResizable(false);
//		Container dd = dialog.getContentPane();
//		dd.setLayout(null);
//		lb1.setBounds(4, 2, 30, 20);
//		dd.add(lb1);
//		lb2.setBounds(150, 2, 30, 20);
//		dd.add(lb2);
//		lb3.setBounds(250, 2, 30, 20);
//		dd.add(lb3);
//		text1.setBounds(4, 24, 140, 20);
//		dd.add(text1);
//		text2.setBounds(150, 24, 95, 20);
//		dd.add(text2);
//		text3.setBounds(250, 24, 50, 20);
//		dd.add(text3);
//
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		String fontName[] = ge.getAvailableFontFamilyNames();
//		for (int i = 0; i < fontName.length; i++) {
//			list1.add(fontName[i]);
//		}
//		for (int j = 0; j < zixing.length; j++) {
//			list2.add(zixing[j]);
//		}
//		for (int k = 0; k < daxiao.length; k++) {
//			list3.add(daxiao[k]);
//
//		}
//
//		list1.setBounds(4, 46, 140, 130);
//		dd.add(list1);
//		list2.setBounds(150, 46, 95, 130);
//		dd.add(list2);
//		list3.setBounds(250, 46, 50, 130);
//		dd.add(list3);
//		b_queding.setBounds(315, 60, 60, 20);
//		dd.add(b_queding);
//		b_quxiao.setBounds(315, 100, 60, 20);
//		dd.add(b_quxiao);
//		b_queding.addActionListener(this);
//		b_quxiao.addActionListener(this);
//		list1.addItemListener(this);
//		list2.addItemListener(this);
//		list3.addItemListener(this);
//		dialog.show();
//
//	}
//
//	// ��Ӧ��ť�¼��ķ���
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == b_queding) {
//			aa = text1.getText();
//			cc = Integer.parseInt(text3.getText());
//			if (list2.getSelectedIndex() == 0) {
//				f = new Font(aa, Font.PLAIN, cc);
//			} else if (list2.getSelectedIndex() == 1) {
//				f = new Font(aa, Font.ITALIC, cc);
//			} else if (list2.getSelectedIndex() == 2) {
//				f = new Font(aa, Font.BOLD, cc);
//			} else {
//				f = new Font(aa, Font.PLAIN, cc);
//			}
//			dialog.show(false);
//
//		}
//
//		else {
//			dialog.show(false);
//		}
//	}
//
//	// ��Ӧ�б���¼��ķ���
//	public void itemStateChanged(ItemEvent e) {
//		String name1 = list1.getSelectedItem();
//		String name2 = list2.getSelectedItem();
//		String name3 = list3.getSelectedItem();
//		if (name1 != null) {
//			text1.setText(name1);
//		}
//		if (name2 != null) {
//			text2.setText(name2);
//		}
//		if (name3 != null) {
//			text3.setText(name3);
//		}
//	}
//
//}
