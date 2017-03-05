package kcsj.pojo;

/**
 * Created by Administrator on 2017/2/25.
 */
public class MyMap {
    private int start;
    private int size;
    public MyMap(int i,int j){
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
