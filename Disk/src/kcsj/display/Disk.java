package kcsj.display;

/**
 * Created by Administrator on 2017/2/25.
 */

import kcsj.pojo.Block;
import kcsj.pojo.Message;
import kcsj.pojo.MyColor;
import kcsj.pojo.NullLinkTable;
import kcsj.pojo.NullTable;

import javax.swing.*;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟磁盘的空间的盘块类
 * 管理整个磁盘的类
 * @author belong * 
 */
public class Disk {
	public static HashMap<String,NullTable>map=new HashMap<>();
	public static int diskRodes = 10;  // 行数
	public static int diskColumns = 6;  // 列数
	public static int totalBlocks = diskRodes * diskColumns;  // 物理盘块的总个数
	public static int freeB= totalBlocks;  // 可用的磁盘块数
	public Block[][] blockTable = new Block[diskRodes][diskColumns];    
	public static Map<String,ArrayList<Integer>> dismap = new HashMap<String, ArrayList<Integer>>();
	private boolean[][] diskFreeMap = new boolean[diskRodes][diskColumns];  // 用位示图法来表示磁盘空闲状况
	private DiskDemo demo;
	private MyColor myColor = new MyColor();  // 用于表示同一个文件
	private  JButton[][] demoBlocks ;  // 按钮表示磁盘块
	JButton button_temp ;  // 临时盘块
	private static Logger logger = Logger.getLogger(Disk.class);


	public Disk(){

	}

	public Disk(DiskDemo demo){
		this.demo = demo;
		this.demoBlocks = demo.getDemoBlocks();
	}

	public int[] getFreeBlocks(int size){
		int [] free = new int[size*2]; // 得到空闲的盘块
		int count=0;
		for(int i=0;i<Disk.diskRodes;i++){
			for(int j=0;j<Disk.diskColumns;j++){
				if(count==size*2)break;
				if(diskFreeMap[i][j]==false){
					diskFreeMap[i][j]=true; // 把得到的空闲块直接就变成已占用
					free[count++] = i;
					free[count++] = j;
				}
			}
			if(count==size*2)break;
		}
		return free;
	}

	/**
	 * 回收空闲盘块
	 * 获取空间盘块的行列值,同时把空闲盘块拿出来     *
	 * @return 长度为2*size的数组，没两个相邻值为一个节点地址
	 */
	public int[] getFreeBlocks(int[][] num){
		int [] free = new int[num[0][0]*2];  // 按文件所需的两倍进行分配磁盘快
		int size=num[0][0];
		int count=0;
		logger.info("开始分配磁盘块");
		logger.info("所需盘块的个数："+num[0][0]+" 初始盘块的横坐标："+num[0][1]+" 初始盘块的纵坐标："+num[0][2]);
		logger.info("分配结束");
		int locate=num[0][1];  // 初始盘块的横坐标

		for(int i=num[0][1];i<Disk.diskRodes;i++){  // 从初始盘块的位置进行扫描
			if(i==locate+1){
				num[0][2]=0;
			}
			for(int j=num[0][2];j<Disk.diskColumns;j++){
				if(count==size*2){
					break;
				}
				if(diskFreeMap[i][j]==false){
					diskFreeMap[i][j]=true;  // 正式分配盘块
					free[count++] = i;
					free[count++] = j;
				}
			}
			if(count==size*2){
				break;
			}
		}
		return free;
	}
	/**
	 * 找到父文件夹的空闲项目
	 * @param parentFileNumber 父文件盘块号
	 * @return 空闲索引号，-1已经满了
	 */
	public int getFreeItem(int parentFileNumber){
		int row = parentFileNumber/Disk.diskColumns;
		int column = parentFileNumber%Disk.diskColumns;
		int[] subFile = blockTable[row][column].getSubFile();
		int fCount = 0,result=-1;
		try {
			for(int i = 0; i< Block.totalSubFiles; i++){
				if(subFile[i]==-1){
					result = i;
					break;
				}
				fCount++;
			}
			if(fCount==Block.totalSubFiles){
				result = -1;
			}
		} catch (Exception e) {
			logger.info("空指针了亲❤");
		}
		return result;
	}

	public void setFolderItem(int parentFileNumber,int headBlock,int freeItem){
		int row = parentFileNumber/Disk.diskColumns;
		int column = parentFileNumber%Disk.diskColumns;
		int[] subFile = blockTable[row][column].getSubFile();
		subFile[freeItem] = headBlock;
	}

	public void display(){
		for(int i=0;i<diskRodes;i++){
			for(int j=0;j<diskColumns;j++){
				if(blockTable[i][j]==null){
					//					System.out.print("nullBlock"+"\t");
				}else{
					Block tB = blockTable[i][j];
					//					System.out.print(tB.getNumber()+"|"+tB.getFileName()+"|"+tB.getNextBlock()+"|"+tB.getParentBlock()+"\t");
				}
			}
		}
		System.out.println("空闲盘块："+Disk.freeB);
		for(int i=0;i<diskRodes;i++){
			for(int j=0;j<diskColumns;j++){
				Block block = blockTable[i][j];
				if(block!=null){
					if(block.getFileType().equals("folder")){ // 判断是否是文件夹
						int t[] = block.getSubFile();
						System.out.print("folder"+block.getFileName()+" ");
						for(int k=0;k<10;k++){
							if(t[k]!=-1){
								System.out.print(t[k]+" ");
							}else{
								break;
							}
						}
					}
				}
			}
			System.out.println();
		}
	}

	/**
	 * 采用的是位示图的方式来查找磁盘中的空闲盘块
	 * 回收空闲块
	 * 实现连续分配和离散分配的接口
	 * @param size
	 * @return
	 */
	public int[][] getFree(int size){
		/**
		 * 定义计数器，计数器有三个空间
		 * count[0][0]: 用于计数
		 * count[0][1]: 用于记录首个空闲盘块在位示图中横坐标的位置
		 * count[0][2]: 用于记录首个空闲盘块在位示图中纵坐标的位置
		 */
		int[][]count=new int[1][3];
		for(int i=0;i<Disk.diskRodes;i++){
			for(int j=0;j<Disk.diskColumns;j++){
				if(i<9){  // 因为子程序中有i+1的情况，防止行超过范围
					if((diskFreeMap[i][j]==false&&diskFreeMap[i+1][0]==false&&j==5)||(diskFreeMap[i][j]==false&&diskFreeMap[i][j+1]==false)){
						if(count[0][0]==0){
							count[0][1]=i;
							count[0][2]=j;
						}
						count[0][0]++;  // 记录空闲盘块的数量
						if(count[0][0]>=size){
							return count;  //返回可用盘块的数量
						}
					}else {
						count[0][0]=0;  // 清空计数器
					}
				}else{
					if(j<5){ // 防止列超出范围
						if((diskFreeMap[i][j]==false&&diskFreeMap[i][j+1]==false)){
							if(count[0][0]==0){
								count[0][1]=i;
								count[0][2]=j;
							}
							count[0][0]++;
							if(count[0][0]>=size){
								return count;
							}
						}else {
							count[0][0]=0;
						}
					}else{
						if((diskFreeMap[i][j]==false&&diskFreeMap[i][j-1]==false)){  // ***?
							if(count[0][0]==0){
								count[0][1]=i;
								count[0][2]=j;
							}
							count[0][0]++;
							if(count[0][0]>=size){
								return count;
							}
						}else {
							count[0][0]=0;
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 * 实现离散的连续分配
	 * @param message : 文件的基本信息
	 * @return 
	 */
	public Message discreteAllocateSpace(Message message){
		Color c_temp = myColor.getColor();
		int size = message.getSize(); //得到文件的大小
		String fileType = message.getFileType();
		String fileName = message.getFileName();
		int parentFileNumber = message.getParentFileNumber();
		int startAdd = 0,endAdd = 0;
		int freeIndex = 0;
		int preR=0,preC=0,preNumber=0;
		int index = 0;
		if(size<=Disk.freeB){
			if(fileType.equals("file")){ //是文件
				if(parentFileNumber!=-1){ //文件 有父文件夹
					int pSubFileItem = getFreeItem(parentFileNumber);
					if(pSubFileItem!=-1){ //文件 父文件夹有多余项
						int[] freeBlocks = getFreeBlocks(size);
						for(int i=0;i<size;i++){
							//实现磁盘分配
							Integer number = freeBlocks[freeIndex]*Disk.diskColumns+freeBlocks[freeIndex+1];
							int row = freeBlocks[freeIndex];
							int column = freeBlocks[freeIndex+1];
							blockTable[row][column] = new Block(fileName,fileType,number);
							NullLinkTable table = new NullLinkTable();
							if(dismap.containsKey(fileName)){
								dismap.get(fileName).add(number);
							} else {
								ArrayList<Integer> list = new ArrayList<>();
								list.add(number);
								dismap.put(fileName, list);
							}
							button_temp = demoBlocks[row][column];
							button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
							button_temp.setBackground(c_temp);
							button_temp.setFont(new Font("隶书",1,15));
							button_temp.revalidate();
							if(i==0){
								startAdd = number;
								setFolderItem(parentFileNumber,number,pSubFileItem);
								blockTable[row][column].setParentBlock(parentFileNumber);
							}
							freeIndex+=2;
							if(i!=0 && i!=size-1){  //第一个块，不知道子块
								blockTable[preR][preC].setNextBlock(number);
							}else if(i!=0&&i==(size-1)){
								blockTable[preR][preC].setNextBlock(number);
								blockTable[row][column].setNextBlock(-1);
								//需要增加文件 最后一个盘块号		
							}else if(i==0 && i==(size-1)){
								endAdd = number;
								blockTable[row][column].setNextBlock(-1);
							}
							preR = row;
							preC = column;
						} 
						Disk.freeB=Disk.freeB-size;
						message.setOpeSuccess(true);
						message.setEndAdd(endAdd);
						message.setStartAdd(startAdd);
						return message;  //子文件分配成功
					}else{  //文件，父文件夹无多余文件
						message.setOpeSuccess(false);
						message.setOpeMessage("父文件夹空间已满\n无法创建子文件");
						return message;
					}
				}else{//文件，无父文件
					int[] freeBlocks = getFreeBlocks(size); // 根据文件的大小来找空闲块儿，得到的是size的2倍的下标数组，横纵坐标挨着。
					for(int i=0;i<size;i++){
						// 离散存储的关键语句
						int number = freeBlocks[freeIndex]*Disk.diskColumns+freeBlocks[freeIndex+1];
						if(i==0){
							startAdd = number;
						}
						int row = freeBlocks[freeIndex];
						int column = freeBlocks[freeIndex+1];
						blockTable[row][column] = new Block(fileName,fileType,number);
						button_temp = demoBlocks[row][column];
						button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
						button_temp.setBackground(c_temp);
						button_temp.setFont(new Font("隶书",1,15));
						button_temp.revalidate();
						freeIndex+=2;
						if(i!=0 && i!=size-1){  //中间块
							blockTable[preR][preC].setNextBlock(number);

						}else if(i!=0&&i==(size-1)){ //最后一个非第一个
							blockTable[preR][preC].setNextBlock(number);
							blockTable[row][column].setNextBlock(-1);
						}else if(i==0 && i==(size-1)){  //第一个，也是最后一个
							blockTable[row][column].setNextBlock(-1);
							endAdd = number;
						}
						preR = row;
						preC = column;
					} 
					Disk.freeB=Disk.freeB-size;
					message.setOpeSuccess(true);
					message.setEndAdd(endAdd);
					message.setStartAdd(startAdd);
					return message;  //子文件分配成功
				}
			}else{ //是文件夹
				if(parentFileNumber!=-1){ //文件夹 有父文件
					int pSubFileItem = getFreeItem(parentFileNumber);
					if(pSubFileItem!=-1){ //文件夹 父文件夹有多余项
						int[] freeBlocks = getFreeBlocks(1);
						int number = freeBlocks[0]*Disk.diskColumns+freeBlocks[1];
						int row = freeBlocks[0];
						int column = freeBlocks[1];
						startAdd = number;
						setFolderItem(parentFileNumber,number,pSubFileItem);
						blockTable[row][column] = new Block(fileName,fileType,number);
						blockTable[row][column].setNextBlock(-1);
						blockTable[row][column].initialSubFile();
						blockTable[row][column].setParentBlock(parentFileNumber);

						button_temp = demoBlocks[row][column];
						button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
						button_temp.setBackground(c_temp);
						button_temp.setFont(new Font("隶书",1,15));
						button_temp.revalidate();
						Disk.freeB--;
						message.setOpeSuccess(true);
						message.setStartAdd(startAdd);
						return message;
					}else{ //文件夹 父文件无多余项
						message.setOpeSuccess(false);
						message.setOpeMessage("父文件夹空间已满\n无法创建子文件");
						return message;
					}
				}else{ //文件夹 无父文件
					int[] freeBlocks = getFreeBlocks(1);
					int row = freeBlocks[0];
					int column = freeBlocks[1];
					int number = row*Disk.diskColumns+column;
					startAdd = number;
					blockTable[row][column] = new Block(fileName,fileType,number);
					blockTable[row][column].setNextBlock(-1);
					blockTable[row][column].initialSubFile();
					button_temp = demoBlocks[row][column];
					button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
					button_temp.setBackground(c_temp);
					button_temp.setFont(new Font("隶书",1,15));
					button_temp.revalidate();
					Disk.freeB--;
					message.setOpeSuccess(true);
					message.setStartAdd(startAdd);
					return message;
				}
			}
		}else{
			message.setOpeSuccess(false);
			message.setOpeMessage("空间不够\n所需空间"+size+"<剩余空间"+Disk.freeB);
			return message;
		}
	}

	/**
	 * 连续分配
	 * 给一个文件/文件夹分配存储空间，以及修改信息，并返回相应的消息对象
	 * @param message size,fileName,fileType,pFileNumber
	 * @return  success:startAdd,endAdd fail:
	 */
	public Message allocateSpace(Message message){
		// 获取窗口表单的信息-------------------------------------------------------------
		Color c_temp = myColor.getColor();  // 得到文件的类型
		int size = message.getSize();
		String fileType = message.getFileType();
		String fileName = message.getFileName();        
		//---------------------------------------------------------------------------
		int parentFileNumber = message.getParentFileNumber();
		int startAdd = 0,endAdd = 0;
		int freeIndex = 0;
		int preR=0,preC=0,preNumber=0;
		int[][] free=getFree(size);  // 得到空闲磁盘块的数量,这里是实现四种算法的入口
		if(size<=free[0][0]){  // 相当于count[0][0],表示的是可用的空闲块儿的个数
			if(fileType.equals("file")){ //是文件
				if(parentFileNumber!=-1){ //文件 有父文件夹
					int pSubFileItem = getFreeItem(parentFileNumber);
					if(pSubFileItem!=-1){ //文件 父文件夹有多余项
						int[] freeBlocks = getFreeBlocks(free);

						for(int i=0;i<size;i++){
							int number = freeBlocks[freeIndex]*Disk.diskColumns+freeBlocks[freeIndex+1];
							int row = freeBlocks[freeIndex];
							int column = freeBlocks[freeIndex+1];
							blockTable[row][column] = new Block(fileName,fileType,number);

							button_temp = demoBlocks[row][column];
							button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
							button_temp.setBackground(c_temp);
							button_temp.setFont(new Font("隶书",1,15));
							button_temp.revalidate();
							if(i==0){
								startAdd = number;
								setFolderItem(parentFileNumber,number,pSubFileItem);
								blockTable[row][column].setParentBlock(parentFileNumber);
							}
							freeIndex+=2;
							if(i!=0 && i!=size-1){  //第一个块，不知道子块
								blockTable[preR][preC].setNextBlock(number);
							}else if(i!=0&&i==(size-1)){
								blockTable[preR][preC].setNextBlock(number);
								blockTable[row][column].setNextBlock(-1);
								//需要增加文件 最后一个盘块号
							}else if(i==0 && i==(size-1)){
								endAdd = number;
								blockTable[row][column].setNextBlock(-1);
							}
							preR = row;
							preC = column;
						}
						Disk.freeB=Disk.freeB-size;
						message.setOpeSuccess(true);
						message.setEndAdd(endAdd);
						message.setStartAdd(startAdd);
						map.put(fileName,new NullTable(startAdd,size));
						return message;  //子文件分配成功
					}else{  //文件，父文件夹无多余文件
						message.setOpeSuccess(false);
						message.setOpeMessage("父文件夹空间已满\n无法创建子文件");
						return message;
					}
				}else{//文件，无父文件
					int[] freeBlocks = getFreeBlocks(free);
					for(int i=0;i<size;i++){
						int number = freeBlocks[freeIndex]*Disk.diskColumns+freeBlocks[freeIndex+1];
						if(i==0){
							startAdd = number;
						}
						int row = freeBlocks[freeIndex];
						int column = freeBlocks[freeIndex+1];
						blockTable[row][column] = new Block(fileName,fileType,number);
						button_temp = demoBlocks[row][column];
						button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
						button_temp.setBackground(c_temp);
						button_temp.setFont(new Font("隶书",1,15));
						button_temp.revalidate();
						freeIndex+=2;
						if(i!=0 && i!=size-1){  //中间块
							blockTable[preR][preC].setNextBlock(number);

						}else if(i!=0&&i==(size-1)){ //最后一个非第一个
							blockTable[preR][preC].setNextBlock(number);
							blockTable[row][column].setNextBlock(-1);
						}else if(i==0 && i==(size-1)){  //第一个，也是最后一个
							blockTable[row][column].setNextBlock(-1);
							endAdd = number;
						}
						preR = row;
						preC = column;
					}
					Disk.freeB=Disk.freeB-size;
					message.setOpeSuccess(true);
					message.setEndAdd(endAdd);
					message.setStartAdd(startAdd);
					map.put(fileName,new NullTable(startAdd,size));
					return message;  //子文件分配成功
				}
			}else{ //是文件夹
				if(parentFileNumber!=-1){ //文件夹 有父文件
					int pSubFileItem = getFreeItem(parentFileNumber);
					if(pSubFileItem!=-1){ //文件夹 父文件夹有多余项
						int[] freeBlocks = getFreeBlocks(free);
						int number = freeBlocks[0]*Disk.diskColumns+freeBlocks[1];
						int row = freeBlocks[0];
						int column = freeBlocks[1];
						startAdd = number;
						setFolderItem(parentFileNumber,number,pSubFileItem);
						blockTable[row][column] = new Block(fileName,fileType,number);
						blockTable[row][column].setNextBlock(-1);
						blockTable[row][column].initialSubFile();
						blockTable[row][column].setParentBlock(parentFileNumber);

						button_temp = demoBlocks[row][column];
						button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
						button_temp.setBackground(c_temp);
						button_temp.setFont(new Font("隶书",1,15));
						button_temp.revalidate();
						Disk.freeB--;
						message.setOpeSuccess(true);
						message.setStartAdd(startAdd);
						map.put(fileName,new NullTable(startAdd,1));
						return message;
					}else{ //文件夹 父文件无多余项
						message.setOpeSuccess(false);
						message.setOpeMessage("父文件夹空间已满\n无法创建子文件");
						return message;
					}
				}else{ //文件夹 无父文件
					int[] freeBlocks = getFreeBlocks(free);
					int row = freeBlocks[0];
					int column = freeBlocks[1];
					int number = row*Disk.diskColumns+column;
					startAdd = number;
					blockTable[row][column] = new Block(fileName,fileType,number);
					blockTable[row][column].setNextBlock(-1);
					blockTable[row][column].initialSubFile();
					button_temp = demoBlocks[row][column];
					button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+"");  // 磁盘上文件的显示信息
					button_temp.setBackground(c_temp);
					button_temp.setFont(new Font("隶书",1,15));
					button_temp.revalidate();
					Disk.freeB--;
					message.setOpeSuccess(true);
					message.setStartAdd(startAdd);
					map.put(fileName,new NullTable(startAdd,1));
					return message;
				}
			}
		}else{
			message.setOpeSuccess(false);
			message.setOpeMessage("空间不够\n所需空间"+size+"<剩余空间"+Disk.freeB);
			return message;
		}
	}

	public int[] getFileBlocks(int startAdd,int size,String newName){
		int[] fileBlocks = new int[size];
		int number = startAdd;
		for(int i=0;i<size;i++){
			if(number!=-1){
				fileBlocks[i] = number;
			}else{
				break;
			}
			int row = getRowIndex(number);
			int column = getColumnIndex(number);
			button_temp = demoBlocks[row][column];
			String fn = button_temp.getText();
			String fnn = "";
			String[] fns = fn.split(",");
			fns[2]=newName;
			for(int j=0;j<fns.length;j++){
				fnn+=fns[j];
				if(j!=fns.length-1){
					fnn+=",";
				}
			}
			button_temp.setBackground(button_temp.getBackground().brighter());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			button_temp.revalidate();
			button_temp.setBackground(button_temp.getBackground().darker());
			button_temp.setText(fnn);
			button_temp.revalidate();
			number = blockTable[row][column].getNextBlock();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileBlocks;
	}

	/**
	 * 高亮显示文件或文件夹所在的盘块号
	 * @param message startAdd,size,newFileName
	 *  output 打印文件名和所在盘块号
	 */
	public void renewFile(Message message){
		int number = message.getStartAdd();
		String fileName = message.getFileName();
		String newfName = message.getNewFileName();
		int size = message.getSize();
		System.out.println(Disk.map.get(newfName).getStart());
		System.out.println(Disk.map.get(newfName).getSize());


		//		if(fileType.equals("file")){
		//zzyint[] fileBlocks = getFileBlocks(number,size,newfName);
		//			System.out.print("选择文件："+fileName+"盘块号：");
		/* for(int i=0;i<size;i++){
            zzy
//				System.out.print(fileBlocks[i]+" ");
        }*/

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回收文件的空间
	 * @param message startAdd fileType
	 * @return success:true fail:false
	 */
	public Message reclaimSpace(Message message){
		int startAdd = message.getStartAdd();
		int row = startAdd/Disk.diskColumns;
		int column = startAdd%Disk.diskColumns;
		int parentRow = -1,parentColumn = -1;
		int parentFileNumber = blockTable[row][column].getParentBlock();
		boolean result = false;
		if(parentFileNumber!=-1){ //有父文件
			parentRow = parentFileNumber/Disk.diskColumns;
			parentColumn = parentFileNumber%Disk.diskColumns;
			int pSubFile[] = blockTable[parentRow][parentColumn].getSubFile();
			for(int i=0;i<Block.totalSubFiles;i++){
				if(pSubFile[i]==startAdd){
					pSubFile[i] = -1;
					result = true;
					break;
				}
			}
			if(!result){ //找不到父文件
				System.out.println("父文件"+parentFileNumber+"不存在该子文件！"+startAdd);
				message.setOpeSuccess(false);
				message.setOpeMessage("父文件"+parentFileNumber+"不存在该子文件！"+startAdd);
				return message;
			}

			deleteFF(startAdd);
			message.setOpeSuccess(true);
			return message;
		}else{//没有父文件
			deleteFF(startAdd);
			message.setOpeSuccess(true);
			return message;
		}
	}

	public void deleteFile(int startAdd){
		int row = startAdd/Disk.diskColumns;
		int column = startAdd%Disk.diskColumns;
		int nextBlock = blockTable[row][column].getNextBlock();
		diskFreeMap[row][column] = false;
		blockTable[row][column] = null;
		button_temp = demoBlocks[row][column];
		button_temp.setBackground(Color.black);
		button_temp.setText(null);
		button_temp.revalidate();
		Disk.freeB++;
		while(nextBlock!=-1){
			row = nextBlock/Disk.diskColumns;
			column = nextBlock%Disk.diskColumns;
			nextBlock = blockTable[row][column].getNextBlock();
			diskFreeMap[row][column] = false;
			blockTable[row][column] = null;
			button_temp = demoBlocks[row][column];
			button_temp.setBackground(Color.black);
			button_temp.setText(null);
			button_temp.revalidate();
			Disk.freeB++;
		}

	}
	/**
	 * 删除文件 递归的删除
	 * @param startAdd  文件的第一个盘块号！
	 */
	public void deleteFF(int startAdd){
		int row = startAdd/Disk.diskColumns;
		int column = startAdd%Disk.diskColumns;
		String fileType = blockTable[row][column].getFileType();
		if(fileType.equals("file")){
			deleteFile(startAdd);
		}else{
			int[] subFile = blockTable[row][column].getSubFile();
			for(int i=0;i<Block.totalSubFiles;i++){
				if(subFile[i]!=-1){
					deleteFF(subFile[i]);
				}
			}
			blockTable[row][column] = null;
			diskFreeMap[row][column] = false;
			button_temp = demoBlocks[row][column];
			button_temp.setBackground(Color.black);
			button_temp.setText(null);
			button_temp.revalidate();
			Disk.freeB++;
		}
	}
	/**
	 * 增加文件的大小
	 * @param message startAdd,endAdd,increment
	 */
	public Message addSpace(Message message){
		int incSize = message.getIncSize();
		int endAdd = message.getEndAdd();
		int number = endAdd, freeIndex=0;
		int row = 0,column = 0;
		int nextBlockNumber = 0;
		row =getRowIndex(number);
		column = getColumnIndex(number);
		String fileName = blockTable[row][column].getFileName();
		String fileType = blockTable[row][column].getFileType();
		int parentFileNumber =  blockTable[row][column].getParentBlock();
		int preR = row, preC = column,preN = 0;
		int[][] free=getFree(incSize);
		if(incSize<=free[0][0]){ //空间够
			int[] inSN = getFreeBlocks(free);
			for(int i=0;i<incSize;i++){
				row = inSN[freeIndex]; //新的
				column = inSN[freeIndex+1];
				number = getNumber(row,column);
				blockTable[preR][preC].setNextBlock(number);
				Block block = new Block(fileName,"file",number);
				if(i==incSize-1){
					block.setNextBlock(-1);
				}
				blockTable[row][column] = block;
				button_temp = demoBlocks[row][column];
				button_temp.setText(number+","+fileType+","+fileName+","+parentFileNumber+";");
				button_temp.setBackground(demoBlocks[preR][preC].getBackground());
				button_temp.setFont(new Font("隶书",1,15));
				button_temp.revalidate();
				freeIndex+=2;
				preR = row;
				preC = column;
				preN = number;
			}
			Disk.freeB = Disk.freeB-incSize;
			message.setSize(message.getSize()+incSize);
			message.setOpeSuccess(true);
		}else{
			message.setOpeSuccess(false);
			message.setOpeMessage("磁盘空间不够,所需空间"+incSize+"<剩余空间"+Disk.freeB);
		}
		return message;

	}

	public int getNumber(int row,int column){
		return row*Disk.diskColumns + column;
	}

	public int getRowIndex(int number){
		return number/Disk.diskColumns;
	}
	public int getColumnIndex(int number){
		return number%Disk.diskColumns;
	}
	/**
	 * 减少文件的大小,只能够删除最后几个盘块 
	 * @param message 获取必要的文件信息 decSize,size,startAdd
	 * @return message opeSuccess,opeMessage
	 */
	public Message subSpace(Message message){
		int decSpace = message.getDecSize();
		int size = message.getSize();
		int order = size-decSpace;
		int row = 0, column = 0;
		int number = message.getStartAdd();
		int total = Disk.dismap.get(message.getFileName()).size(); //一个文件的总个数
		for(int i = 0;i<decSpace;i++){
			Disk.dismap.get(message.getFileName()).remove(total-i-1);
		}
		if(order>0){ //减少空间小于文件大小
			for(int i=0;i<order;i++){
				row = getRowIndex(number);
				column = getColumnIndex(number);
				number = blockTable[row][column].getNextBlock();
				if(i==order-1){
					blockTable[row][column].setNextBlock(-1);
				}
			}
			if(number!=-1){  //不是最后一块
				while(number!=-1){
					row = getRowIndex(number);
					column = getColumnIndex(number);
					number = blockTable[row][column].getNextBlock();
					blockTable[row][column] = null;
					diskFreeMap[row][column] = false;
					button_temp = demoBlocks[row][column];
					button_temp.setText(null);
					button_temp.setBackground(Color.black);
					button_temp.revalidate();
					Disk.freeB++;
				}
			}else{
				System.out.println("您删除的空间为0");
			}
			message.setOpeSuccess(true);
			return message;
		}else{ //减少空间大于文件大小
			message.setOpeSuccess(false);
			message.setOpeMessage("减少空间大于文件大小");
			return message;
		}
	}
	public DiskDemo getDemo() {
		return demo;
	}
	public void setDemo(DiskDemo demo) {
		this.demo = demo;
	}

}
