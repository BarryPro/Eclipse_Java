package kcsj.action;

import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Created by belong on 2017/2/25.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.NEW;

import kcsj.display.Disk;
import kcsj.display.DiskDemo;
import kcsj.pojo.Block;
import kcsj.pojo.Message;
import kcsj.pojo.NullLinkTable;

/**
 * 定义了弹出窗口的基本显示布局，以及鼠标点击操作
 * 工具栏的功能都是在这个类里面实现的
 * @author belong
 *
 */
public class FileDialog extends JDialog implements ActionListener {
	private DiskDemo frame;
	private String dialog_type;
	private JLabel tip_name, tip_size, tip_fileType, tip_parentN, tip_opeType,
	tip_fileSA;
	private JTextField text_name, text_size, text_parentN, text_opeType,
	text_fileSA,text_newName;
	private JLabel tip_fileEA, tip_incSize, tip_decSize,tip_newName;
	private JTextField text_fileEA, text_incSize, text_decSize;
	private JComboBox text_fileType;
	private JButton confirm = new JButton("确定");
	private String[] fileType = { "file", "folder" }; //区分是文件类型还是文件夹类型
	private Logger logger = Logger.getLogger(FileDialog.class);

    public FileDialog() {

    }

    /**
     * 设置弹出框的标识
     * @param 
     * @param type
     */
    public FileDialog(DiskDemo frame, String type) {
        super(frame, "文件操作对话框(" + type + ")", true);
        this.frame = frame;
        this.dialog_type = type;
        init();
        this.setLayout(null);
        this.setBounds(frame.getX() + frame.getWidth() / 2 - 150, frame.getY()
                + frame.getHeight() / 2 - 115, 300, 230);  // 弹出窗的尺寸（300x230）
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * 初始化点击事件
     */
    private void init() {
        confirm.setBounds(100, 150, 80, 20);  // 初始化确认按钮的的坐标与大小      
        if (dialog_type.equals("NEW")) {  // 工具栏中：新建
            new_Action();
        } else if (dialog_type.equals("DELETE")) {  // 工具栏中： 删除
        	delete_Action();
        } else if (dialog_type.equals("ADD")) {  // 工具栏中：追加内容
        	add_Action();            
        } else if (dialog_type.equals("DEC")) {  // 工具栏中： 删除内容
            dec_Action();   
        } else if (dialog_type.equals("MAP")) { // 工具栏中：映射
        	info_Action();                        
        } else {  // 其他情况
            error_Action();
        }
    }

	private void error_Action() {
		JOptionPane.showMessageDialog(this, "程序有错，从新输入s");
		this.dispose();
	}

	private void info_Action() {
		text_newName = new JTextField(20);
		text_newName.setBounds(100,90,100,20);
		this.add(text_newName);
		tip_newName = new JLabel("名称:", JLabel.RIGHT);
		tip_newName.setBounds(20, 90, 80, 20);
		this.add(tip_newName);
		confirm.addActionListener(this);
		this.add(confirm);
	}

	private void dec_Action() {
		tip_name = new JLabel("文件名称:", JLabel.RIGHT);
		tip_name.setBounds(20, 30, 80, 20);
		this.add(tip_name);
		text_name = new JTextField(20);
        text_name.setBounds(100, 30, 100, 20);
        this.add(text_name);        
		
		tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
		tip_fileSA.setBounds(20, 60, 80, 20);
		this.add(tip_fileSA);
		text_fileSA = new JTextField(20);
		text_fileSA.setBounds(100, 60, 100, 20);		
		this.add(text_fileSA);
		
		tip_size = new JLabel("文件大小:", JLabel.RIGHT);
		tip_size.setBounds(20, 90, 80, 20);
		this.add(tip_size);
		text_size = new JTextField(20);
		text_size.setBounds(100, 90, 100, 20);
		this.add(text_size);
		
		tip_decSize = new JLabel("减小空间:", JLabel.RIGHT);
		tip_decSize.setBounds(20, 120, 80, 20);
		this.add(tip_decSize);
		text_decSize = new JTextField(20);
		text_decSize.setBounds(100, 120, 100, 20);
		this.add(text_decSize);	
		
		confirm.addActionListener(this);
		this.add(confirm);
	}

	private void add_Action() {
		tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
		tip_fileSA.setBounds(20, 30, 80, 20);
		this.add(tip_fileSA);
		tip_fileEA = new JLabel("最后盘块:", JLabel.RIGHT);
		tip_fileEA.setBounds(20, 60, 80, 20);
		this.add(tip_fileEA);
		tip_incSize = new JLabel("增加空间:", JLabel.RIGHT);
		tip_incSize.setBounds(20, 90, 80, 20);
		this.add(tip_incSize);
		text_fileSA = new JTextField(20);
		text_fileSA.setBounds(100, 30, 100, 20);
		this.add(text_fileSA);
		text_fileEA = new JTextField(20);
		text_fileEA.setBounds(100, 60, 100, 20);
		this.add(text_fileEA);
		text_incSize = new JTextField(20);
		text_incSize.setBounds(100, 90, 100, 20);
		this.add(text_incSize);
		confirm.addActionListener(this);
		this.add(confirm);
	}

	/**
	 * 删除动作函数
	 */
	private void delete_Action() {
		tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
		tip_fileSA.setBounds(20, 30, 80, 20);
		this.add(tip_fileSA);
		text_fileSA = new JTextField(20);
		text_fileSA.setBounds(100, 30, 100, 20);
		this.add(text_fileSA);
		confirm.addActionListener(this);
		this.add(confirm);
		System.out.println("DELETE");
	}
    
	/**
	 * 新建动作函数
	 */
    private void new_Action(){
    	tip_name = new JLabel("文件名    :", JLabel.RIGHT);
        tip_name.setBounds(20, 30, 80, 20);
        this.add(tip_name);
        
        text_name = new JTextField(20);
        text_name.setBounds(100, 30, 100, 20);
        this.add(text_name);
        
        tip_fileType = new JLabel("文件类型:", JLabel.RIGHT);
        tip_fileType.setBounds(20, 60, 80, 20);
        this.add(tip_fileType);
        
        text_fileType = new JComboBox(fileType);
        text_fileType.setBounds(100, 60, 100, 20);
        this.add(text_fileType);
        
        tip_size = new JLabel("文件大小:", JLabel.RIGHT);
        tip_size.setBounds(20, 90, 80, 20);
        this.add(tip_size);
        
        text_size = new JTextField(20);
        text_size.setBounds(100, 90, 100, 20);
        this.add(text_size);
       
        tip_parentN = new JLabel("父文件号:", JLabel.RIGHT);
        tip_parentN.setBounds(20, 120, 80, 20);
        this.add(tip_parentN);
        
        text_parentN = new JTextField(20);
        text_parentN.setBounds(100, 120, 100, 20);
        this.add(text_parentN);
        
        confirm.addActionListener(this);     
        this.add(confirm);           
    }

    /**
     * 分配回收主要函数都在这里
     * 具体的实现点击工具栏的功能
     */
    public void actionPerformed(ActionEvent e) {
    	Message m = null;
    	Disk d = frame.getDisk();
    	try {
    		if (dialog_type.equals("NEW")) {  // 用户要新增一个文件(实现离散还是连续)
    			//创建函数所需的信息对象
    			try {
    				m = new Message(text_name.getText(), 
        					text_fileType.getSelectedItem().toString(), 
        					Integer.parseInt(text_size.getText()), 
        					Integer.parseInt(text_parentN.getText())
        					);
        			
        			if(Integer.parseInt(text_size.getText()) <= 4){
        				//调用连续分配分配空间方法
            			m = d.allocateSpace(m);
        			} else {
        				//调用离散分配空间方法
            			m = d.discreteAllocateSpace(m);
        			}
        			
        			frame.setMessage(m);
        			frame.changeDiskInfo();
				} catch (Exception e2) {
					logger.info("❤转换类型出了点问题哦❤");
				}
    		} else if (dialog_type.equals("DELETE")) {  // 执行删除功能
    			//创建删除磁盘上文件所需的信息
    			m = new Message(Integer.parseInt(text_fileSA.getText()));
    			m = d.reclaimSpace(m);  // 执行删除方法
    			frame.setMessage(m);
    			frame.changeDiskInfo();
    		} else if (dialog_type.equals("ADD")) {  // 追加内容功能
    			try{
    				m = new Message(Integer.parseInt(text_fileSA.getText()),
        					Integer.parseInt(text_fileEA.getText()), Integer
        					.parseInt(text_incSize.getText()));
        			m = d.addSpace(m);
        			frame.setMessage(m);
                    frame.changeDiskInfo();
    			} catch(Exception exception){
    				logger.info("类型转换错误");
    			}
            } else if (dialog_type.equals("DEC")) {  // 删除文件的内容功能
                m = new Message();
                m.setStartAdd(Integer.parseInt(text_fileSA.getText()));
                m.setSize(Integer.parseInt(text_size.getText()));
                m.setDecSize(Integer.parseInt(text_decSize.getText()));
                m.setFileName(text_name.getText());
                d.subSpace(m);
                frame.setMessage(m);
                frame.changeDiskInfo();
            } else if (dialog_type.equals("MAP")){  // 文件的映射功能
            	logger.info("弹出文件映射窗口");
            	try{
            		JFrame temp_Windows = new JFrame(text_newName.getText()+"文件的映射");
                	temp_Windows.setVisible(true);
                	temp_Windows.setLayout(new GridLayout(0,2));
                	temp_Windows.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	temp_Windows.setBounds(812,425,300,230);
                	temp_Windows.setAlwaysOnTop(true);
                	int size = 0; // 初始化0
                	JLabel label2 = new JLabel("逻辑块");
                	JLabel label3 = new JLabel("物理块");
                	try { //对空指针进行处理,连续显示映射
                		size = Disk.map.get(text_newName.getText()).getSize();
                		int start = Disk.map.get(text_newName.getText()).getStart();
                    	JLabel label_L[] = new JLabel[size];
                    	JLabel label_D[] = new JLabel[size];                    	
                    	temp_Windows.add(label2);
                    	temp_Windows.add(label3);
                    	for(int i = 0;i<size;i++){
                    		label_L[i] = new JLabel(" "+(i)+" "); // 显示逻辑块
                    		label_D[i] = new JLabel(" "+(start+i)+" "); // 显示物理块
                        	temp_Windows.add(label_L[i]);
                        	temp_Windows.add(label_D[i]);                	
                    	}
					} catch (Exception e2) {
						// 离散显示映射
						int size1 = Disk.dismap.get(text_newName.getText()).size();
						ArrayList<Integer> list_subLink = Disk.dismap.get(text_newName.getText());
						System.out.println(list_subLink);
						JLabel label_L1[] = new JLabel[size1];
                    	JLabel label_D1[] = new JLabel[size1];         
                    	for(int i = 0;i<size1;i++){
                    		label_L1[i] = new JLabel(" "+(i)+" "); // 显示逻辑块
                    		label_D1[i] = new JLabel(" "+(list_subLink.get(i))+" "); // 显示物理块
                    		temp_Windows.add(label_L1[i]);
                        	temp_Windows.add(label_D1[i]);                
                    	}
					}
                	
            	}catch (Exception ee) {
					logger.info("空指针了亲❤");
				}
            	logger.info("弹出文件映射窗口结束");            	
            	
            }
            if(m != null){
            	if (!m.isOpeSuccess()) {
                    JOptionPane.showMessageDialog(this, m.getOpeMessage());
                }
            }
        } catch (Exception e1) {
        	e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据输入有误，请重新输入");  // 弹窗显示信息
        }
        this.dispose();
    }

}
