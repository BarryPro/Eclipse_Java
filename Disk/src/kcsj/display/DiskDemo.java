package kcsj.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;

/**
 * Created by belong on 2017/2/25.
 */

import kcsj.action.ToolBarAction;
import kcsj.pojo.Message;

/**
 * 磁盘界面显示类
 * 功能：用于界面的显示
 * @author belong
 *
 */
public class DiskDemo extends JFrame{
    private static final int WIDTH=1200;
    private static final int HEIGHT=600;
    private Disk disk;
    private Message message = new Message();
    private JButton[][] demoBlocks;
    private Container container;
    private JPanel blocksPanel,buttonPanel,lTextPanel,bTextPanel;
    private JTextArea lText,bText;
    private JTabbedPane bTextTab;
    private JMenu menu1,menu2 ;
    private JMenuItem item1,item2;
    private JMenuBar bar;
    private static Logger logger = Logger.getLogger(DiskDemo.class);
    
    public DiskDemo(){    	
        super("Demo disk assignment");
        logger.info("磁盘管理系统开始运行……");
        demoBlocks = new JButton[Disk.diskRodes][Disk.diskColumns];
        disk = new Disk(this);
        logger.info("开始设置布局");
        setFrame();  // 设置窗口的显示
        logger.info("布局设置结束");
        logger.info("开始初始化数据");
        initData();  // 初始化数据
        logger.info("初始化数据结束");
    }
    
    public static void main(String[] args){
    	logger.info("主程序开始");    	
        try {
        	new DiskDemo();
		} catch (Exception e) {
			logger.info("程序好像有点蒙圈了路  %>_<% ");
		}
        logger.info("主程序结束");
    }

    public void initData(){
        Message message = new Message("hu","folder",1,-1);
        disk.allocateSpace(message);
        message = new Message("a","file",3,-1);
        disk.allocateSpace(message);
        message = new Message("b","file",3,0);
        disk.allocateSpace(message);
        message = new Message("c","folder",1,0);
        disk.allocateSpace(message);
        message = new Message("d","file",6,-1);
        disk.allocateSpace(message);
        changeDiskInfo();
    }

    /**
     * 用于构建GUI的显示
     */
    public void setFrame(){        
        menu1 = new JMenu("开始");  // 菜单
        item1 = new JMenuItem("退出");
        menu1.add(item1);
        item1.addActionListener(new ActionListener() {	// 定义点击退出系统事件
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("操作：退出");
				System.exit(0);				
			}
		});
        JMenu menu2 = new JMenu("关于");
        item2 = new JMenuItem("介绍");
        menu2.add(item2);
        item2.addActionListener(new ActionListener() {  // 定义点击关于事件
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("操作：关于");
				JOptionPane jOptionPane = new JOptionPane();
				jOptionPane.showMessageDialog(null,"第十组：磁盘空间管理\n由于成龙和张彬共同完成！");				
			}
		});
        bar = new JMenuBar();  // 定义装菜单单项的菜单容器
        bar.add(menu1);
        bar.add(menu2);
        this.setJMenuBar(bar);        
        JToolBar toolBar = new JToolBar();  // 新建一个工具条
        toolBar.setFloatable(true);
        // 新建五个抽象动作类
        ToolBarAction file_new = new ToolBarAction("新建",null,this);
        ToolBarAction file_del = new ToolBarAction("删除",null,this);
        ToolBarAction file_add = new ToolBarAction("追加内容",null,this);
        ToolBarAction file_dec = new ToolBarAction("删除内容",null,this);
        ToolBarAction file_map = new ToolBarAction("映射", null, this);
        // 把第一个按钮加入工具条
        toolBar.setFloatable(false);  // 设置工具条可移动
        JButton jb;
        jb = toolBar.add(file_new);
        jb.setActionCommand("NEW");// 设置其产生事件所显示的命令
        jb.setToolTipText("新建");
        jb.setFocusPainted(false);
        toolBar.addSeparator(); // 增加一个隔离栏
        // 把删除按钮加入工具条
        jb = toolBar.add(file_del);
        jb.setActionCommand("DELETE");// 设置其产生事件所显示的命令
        jb.setToolTipText("删除");
        jb.setFocusPainted(false);
        toolBar.addSeparator();
        jb = toolBar.add(file_add);// 把增加按钮加入工具条
        jb.setActionCommand("ADD");// 设置其产生事件所显示的命令
        jb.setToolTipText("追加");
        jb.setFocusPainted(false);
        toolBar.addSeparator();
        jb = toolBar.add(file_dec);// 把减少按钮加入工具条
        jb.setActionCommand("DEC");// 设置其产生事件所显示的命令
        jb.setToolTipText("减少");
        jb.setFocusPainted(false);
        toolBar.addSeparator();
        jb = toolBar.add(file_map);//
        jb.setActionCommand("MAP");// 设置其产生事件所显示的命令
        jb.setToolTipText("映射");
        jb.setFocusPainted(false);
        toolBar.addSeparator();
        this.add(toolBar,BorderLayout.NORTH); // 设置其布局，放在最上面

		/*设置左边 tab 面板*/
        JTabbedPane queues = new JTabbedPane(JTabbedPane.TOP);  // 定义选项卡
        blocksPanel = new JPanel();
        blocksPanel.setLayout(new GridLayout(Disk.diskRodes,Disk.diskColumns));  // 先把磁盘格式化，分好块
        blocksPanel.setBackground(Color.gray);  // 设置装盘块容器的背景颜色
        // 初始化盘块       
        for(int i=0;i<Disk.diskRodes;i++){
            for(int j=0;j<Disk.diskColumns;j++){
                JButton block = new JButton();  // 一个小盘块儿
                block.setBackground(Color.black);  // 盘块的背景颜色是黑色的初始化
                block.setToolTipText("R"+i+"C"+j+"Block"+(i*Disk.diskColumns+j));  // 设置盘块按钮当鼠标悬停时的显示信息
                demoBlocks[i][j] = block;
                blocksPanel.add(block);
            }
        }
        //磁盘总体信息
        bText = new JTextArea();  // 定义多行文本区域用于显示信息
        bText.setEditable(false);  // 设置不允许编辑
        bText.setBackground(Color.white); // 背景设置为白色
        // 磁盘信息变量
        String diskInfo = "磁盘大小："+Disk.totalBlocks+"\t已用空间："+(Disk.totalBlocks-Disk.freeB)+"\t剩余空间:"+Disk.freeB;
        bText.setText(diskInfo);  // 显示磁盘的信息
        bTextTab = new JTabbedPane();  //新建一个选项卡来进行装磁盘的信息
        bTextTab.add("磁盘信息",bText);  
		/*整体显示布局*/
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,blocksPanel,bTextTab);
        jsp.setDividerLocation(0.8);  // 设置分割线的大小
        JTabbedPane jtb2 = new JTabbedPane();
        jtb2.add("磁盘分配", jsp);

        jtb2.validate();  // 允许设置其组件的布局
        this.add(jtb2,BorderLayout.CENTER);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  // 得到屏幕的大小
        this.setBounds(((int)d.getWidth()-WIDTH)/2, ((int)d.getHeight()-HEIGHT)/2, WIDTH, HEIGHT);
        this.setSize(1200, 600);
        this.setVisible(true);  // 允许显示布局
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 设置点叉关闭窗口同时推出程序
    }

    /**
     * 用于显示磁盘的使用情况
     */
    public void changeDiskInfo(){
        bText.setText("磁盘大小："+Disk.totalBlocks+"\t已用空间："+(Disk.totalBlocks-Disk.freeB)+"\t剩余空间:"+Disk.freeB+"\n");

    }
    
    /**
     * 用于显示文件的使用情况
     */
    public void changeDiskInfo1(String name){
        bText.setText("文件名称："+name+"\t开始位置："+(Disk.map.get(name).getStart())+"\t大小:"+Disk.map.get(name).getSize()+"\n");

    }
    public Disk getDisk() {
        return disk;
    }
    public void setDisk(Disk disk) {
        this.disk = disk;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public JButton[][] getDemoBlocks() {
        return demoBlocks;
    }
    public void setDemoBlocks(JButton[][] demoBlocks) {
        this.demoBlocks = demoBlocks;
    }

}
