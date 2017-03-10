package kcsj.action;

/**
 * Created by Administrator on 2017/2/25.
 */

import kcsj.display.DiskDemo;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 工具栏定义类
 * @author belong
 *
 */
public 	class ToolBarAction extends AbstractAction {
    private DiskDemo frame;
    public ToolBarAction(String name,Icon icon,DiskDemo frame){
        super(name,icon);
        this.frame = frame;
    }
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        new FileDialog(frame,command);
    }
}
