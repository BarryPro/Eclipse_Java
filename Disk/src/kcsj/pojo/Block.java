package kcsj.pojo;

/**
 * 磁盘块的信息（磁盘空间管理）
 * 用于描述磁盘块的具体信息的
 * Created by belong on 2017/2/25.
 */
public class Block {
    public static int totalSubFiles = 10;
    private String fileName;
    private String fileType;
    private int number;         //盘块号
    private int nextBlock=-1;   //盘块的下一个盘块，文件夹只能为-1
    private int parentBlock=-1;  //文件夹的子文件才需要知道的项目，直接文件为-1
    private int[] subFile;//文件夹只需存储子文件首地址的索引,为-1表示无子文件
    public Block(){}

    public Block(String fileName, String fileType, int number) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.number = number;
    }
    public void initialSubFile(){ //初始化子文件
        subFile = new int[totalSubFiles];
        for(int i=0;i<totalSubFiles;i++){ //初始时子文件没有
            subFile[i] = -1;
        }
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
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNextBlock() {
        return nextBlock;
    }
    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    public int getParentBlock() {
        return parentBlock;
    }
    public void setParentBlock(int parentBlock) {
        this.parentBlock = parentBlock;
    }
    public int[] getSubFile() {
        return subFile;
    }
    public void setSubFile(int[] subFile) {
        this.subFile = subFile;
    }

}
