package kcsj.pojo;

/**
 * Created by belong on 2017/2/25.
 */
import java.io.Serializable;

/**
 * 用于描述分配出去的磁盘块的具体信息
 * 文件或文件夹的具体信息类
 * (文件空间管理)
 * @author belong
 *
 */
public class Message implements Serializable {

    private String fileName;
    private String fileType;  // 用于判断是文件还是文件夹
    
    /**
     * 父文件的盘块号，如果父文件盘块号为-1，则是直接存放在磁盘上的文件
     * 否则，是文件夹子文件
     */
    private int parentFileNumber; 
    private int size; // 文件大小
    private int incSize; // 需要增加多少空间
    private int decSize; // 减少多少空间
    // 上面五个是要发过来的属性
    //-----------------------------------------------------------------------------------------------------------------------------------------------
    private String newFileName; //文件重命名的名字
    private int startAdd;
    private int endAdd = -1; // 只有文件才需要填入此项 文件夹不需要
    private boolean opeSuccess; // 操作是否成功，成：true，败:false
    private String opeMessage; // 操作返回信息，失败原因。

    /**
     * 创建完整的文件/文件夹信息
     * @param fileName ：文件或文件夹的名称
     * @param fileType ：文件的类型：是文件还是文件夹
     * @param size ：创建文件的大小
     * @param parentFileNumber ：文件所在的父文件夹下
     */
    public Message(String fileName, String fileType, int size,
                   int parentFileNumber) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.parentFileNumber = parentFileNumber;
        this.size = size;
    }
    
    /**
     * 只创建新文件名的构造器
     * @param newFileName ：新文件的名称
     */
    public Message(String newFileName){
        this.newFileName=newFileName;
    }

    public Message(int startAdd, int endAdd, int incSize) {
        this.incSize = incSize;
        this.startAdd = startAdd;
        this.endAdd = endAdd;
    }

    public Message(int startAdd,int size) {
        this.startAdd = startAdd;
        this.size = size;
    }

    public Message(int startAdd, int size, String newFileName) {
        this.size = size;
        this.newFileName = newFileName;
        this.startAdd = startAdd;
    }

    public Message(int startAdd) {
        this.startAdd = startAdd;
    }

    public Message() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartAdd() {
        return startAdd;
    }

    public void setStartAdd(int startAdd) {
        this.startAdd = startAdd;
    }

    public int getEndAdd() {
        return endAdd;
    }

    public void setEndAdd(int endAdd) {
        this.endAdd = endAdd;
    }

    public boolean isOpeSuccess() {
        return opeSuccess;
    }

    public void setOpeSuccess(boolean opeSuccess) {
        this.opeSuccess = opeSuccess;
    }

    public String getOpeMessage() {
        return opeMessage;
    }

    public void setOpeMessage(String opeMessage) {
        this.opeMessage = opeMessage;
    }

    public int getParentFileNumber() {
        return parentFileNumber;
    }

    public void setParentFileNumber(int parentFileNumber) {
        this.parentFileNumber = parentFileNumber;
    }

    public int getIncSize() {
        return incSize;
    }

    public void setIncSize(int incSize) {
        this.incSize = incSize;
    }

    public int getDecSize() {
        return decSize;
    }

    public void setDecSize(int decSize) {
        this.decSize = decSize;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }


}
