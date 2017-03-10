package kcsj.pojo;

/**
 * 空闲表发
 * 定义一个文件的开始以及大小的信息
 * 用于管理文件的大小
 * Created by belong on 2017/2/25.
 */
public class NullTable {
    private int start; //第一个空闲盘块号
    private int size; //空闲块数
    public NullTable(int i,int j){
        start=i;
        size=j;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
