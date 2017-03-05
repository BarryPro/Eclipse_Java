package kcsj.action;

/**
 * Created by Administrator on 2017/2/25.
 */

import kcsj.display.DiskDemo;

import javax.swing.*;
import java.awt.event.ActionEvent;


public 	class ToolBarAction extends AbstractAction {
    DiskDemo frame;
    public ToolBarAction(String name,Icon icon,DiskDemo frame){
        super(name,icon);
        this.frame = frame;
    }
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        new FileDialog(frame,command);
    }
}
