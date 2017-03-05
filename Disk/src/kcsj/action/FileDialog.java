package kcsj.action;

/**
 * Created by Administrator on 2017/2/25.
 */
import kcsj.display.Disk;
import kcsj.display.DiskDemo;
import kcsj.pojo.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private String[] fileType = { "file", "folder" };

    public FileDialog() {

    }

    public FileDialog(DiskDemo frame, String type) {
        super(frame, "文件操作对话框(" + type + ")", true);
        this.frame = frame;
        this.dialog_type = type;
        init();
        this.setLayout(null);
        this.setBounds(frame.getX() + frame.getWidth() / 2 - 150, frame.getY()
                + frame.getHeight() / 2 - 115, 300, 230);// 长300宽3230
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void init() {
        confirm.setBounds(100, 150, 80, 20);
        if (dialog_type.equals("NEW")) {
            tip_name = new JLabel("文件名    :", JLabel.RIGHT);
            tip_name.setBounds(20, 30, 80, 20);
            this.add(tip_name);
            tip_fileType = new JLabel("文件类型:", JLabel.RIGHT);
            tip_fileType.setBounds(20, 60, 80, 20);
            this.add(tip_fileType);
            tip_size = new JLabel("文件大小:", JLabel.RIGHT);
            tip_size.setBounds(20, 90, 80, 20);
            this.add(tip_size);
            tip_parentN = new JLabel("父文件号:", JLabel.RIGHT);
            tip_parentN.setBounds(20, 120, 80, 20);
            this.add(tip_parentN);
            text_name = new JTextField(20);
            text_name.setBounds(100, 30, 100, 20);
            this.add(text_name);
            text_fileType = new JComboBox(fileType);
            text_fileType.setBounds(100, 60, 100, 20);
            this.add(text_fileType);
            text_size = new JTextField(20);
            text_size.setBounds(100, 90, 100, 20);
            this.add(text_size);
            text_parentN = new JTextField(20);
            text_parentN.setBounds(100, 120, 100, 20);
            this.add(text_parentN);
            confirm.addActionListener(this);
            this.add(confirm);
        } else if (dialog_type.equals("DELETE")) {
            tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
            tip_fileSA.setBounds(20, 30, 80, 20);
            this.add(tip_fileSA);
            text_fileSA = new JTextField(20);
            text_fileSA.setBounds(100, 30, 100, 20);
            this.add(text_fileSA);
            confirm.addActionListener(this);
            this.add(confirm);
            System.out.println("DELETE");
        } else if (dialog_type.equals("ADD")) {
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
        } else if (dialog_type.equals("DEC")) {
            tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
            tip_fileSA.setBounds(20, 30, 80, 20);
            this.add(tip_fileSA);
            tip_size = new JLabel("文件大小:", JLabel.RIGHT);
            tip_size.setBounds(20, 60, 80, 20);
            this.add(tip_size);
            tip_decSize = new JLabel("减小空间:", JLabel.RIGHT);
            tip_decSize.setBounds(20, 90, 80, 20);
            this.add(tip_decSize);
            text_fileSA = new JTextField(20);
            text_fileSA.setBounds(100, 30, 100, 20);
            this.add(text_fileSA);
            text_size = new JTextField(20);
            text_size.setBounds(100, 60, 100, 20);
            this.add(text_size);
            text_decSize = new JTextField(20);
            text_decSize.setBounds(100, 90, 100, 20);
            this.add(text_decSize);
            confirm.addActionListener(this);
            this.add(confirm);
        } else if (dialog_type.equals("MAP")) {
            /*tip_fileSA = new JLabel("初始盘块:", JLabel.RIGHT);
            tip_fileSA.setBounds(20, 30, 80, 20);
            this.add(tip_fileSA);
            tip_size = new JLabel("文件大小:", JLabel.RIGHT);
            tip_size.setBounds(20, 60, 80, 20);
            this.add(tip_size);
            text_fileSA = new JTextField(20);
            text_fileSA.setBounds(100, 30, 100, 20);
            this.add(text_fileSA);
            text_size = new JTextField(20);
            text_size.setBounds(100, 60, 100, 20);
            this.add(text_size);*/
            text_newName = new JTextField(20);
            text_newName.setBounds(100,90,100,20);
            this.add(text_newName);
            tip_newName = new JLabel("名称:", JLabel.RIGHT);
            tip_newName.setBounds(20, 90, 80, 20);
            this.add(tip_newName);
            confirm.addActionListener(this);
            this.add(confirm);
        } else {
            JOptionPane.showMessageDialog(this, "程序有错，从新输入s");
            this.dispose();
        }
    }

    public void actionPerformed(ActionEvent e) {
        Message m = null;
        Disk d = frame.getDisk();
        try {
            if (dialog_type.equals("NEW")) {//用户要新增一个文件
                //创建函数所需的信息对象
                m = new Message(text_name.getText(), text_fileType
                        .getSelectedItem().toString(), Integer
                        .parseInt(text_size.getText()), Integer
                        .parseInt(text_parentN.getText()));
                //调用分配空间方法
                m = d.allocateSpace(m);
                frame.setMessage(m);
                frame.changeDiskInfo();
            } else if (dialog_type.equals("DELETE")) { //执行删除功能
                //创建删除磁盘上文件所需的信息
                m = new Message(Integer.parseInt(text_fileSA.getText()));
                m = d.reclaimSpace(m);//执行删除方法
                frame.setMessage(m);
                frame.changeDiskInfo();
            } else if (dialog_type.equals("ADD")) {
                m = new Message(Integer.parseInt(text_fileSA.getText()),
                        Integer.parseInt(text_fileEA.getText()), Integer
                        .parseInt(text_incSize.getText()));
                m = d.addSpace(m);
                frame.setMessage(m);
                frame.changeDiskInfo();
            } else if (dialog_type.equals("DEC")) {
                m = new Message();
                m.setStartAdd(Integer.parseInt(text_fileSA.getText()));
                m.setSize(Integer.parseInt(text_size.getText()));
                m.setDecSize(Integer.parseInt(text_decSize.getText()));
                d.subSpace(m);
                frame.setMessage(m);
                frame.changeDiskInfo();
            } else if (dialog_type.equals("MAP")) {
               /* m = new Message(Integer.parseInt(text_fileSA.getText()),
                        Integer.parseInt(text_size.getText()),text_newName.getText());*/
               m = new Message(
                        text_newName.getText());
                d.renewFile(m);
                m.setOpeSuccess(true);
                frame.setMessage(m);
                frame.changeDiskInfo1(m.getNewFileName());

            }

            if (!m.isOpeSuccess()) {
                JOptionPane.showMessageDialog(this, m.getOpeMessage());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据输入有误，请重新输入");
        }
        this.dispose();
    }

}
