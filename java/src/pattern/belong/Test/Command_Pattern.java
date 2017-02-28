package pattern.belong.Test;
/**
 * 命令模式
 * 定义：
 * 命令模式把一个请求或者操作封装到一个对象中，可以把发出命令的责任和执行
 * 命令的责任分割开，委派给不同的对象。
 * 命令模式允许请求的一方和发送的一方独立开来，使得请求的一方不必知道接受
 * 的一方的接口，更不必知道请求是怎么被接收，以及操作是否执行，何时被执行
 * 以及是怎么被执行。
 * 系统支持命令的撤销。
 * @author Belong
 * 优点：
 * 解耦了调用者和接收者之间联系。调用者调用一个操作，接受者接受请求执行相
 * 应的动作，因为使用Command模式解耦，调用者无需知道接受者任何接口。
 * 缺点：
 * 造成出现过多的具体命令类
 * 
 * 应用场景：
 * 1.在面向对象的程序设计中，一个对象调用另一个对象，一般情况下的调用过程是
 * 创建目标对象实例；设置调用参数；调用目标对象的方法。但在有些情况下有必要
 * 使用一个专门的类对这种调用过程加以封装，我们把这种专门的类称作Command类。
 * 2.整个调用过程比较繁杂，或者存在多处这种调用。这时，使用Command类对该调
 * 用加以封装，便于功能的再利用。
 * 3.调用前后需要对调用参数进行某些处理。
 * 4.调用前后需要进行某些额外处理，比如日志，缓存，记录历史操作等。
 * 
 *
 */
public class Command_Pattern {
	public static void main(String[] args) {		
		Receiver receiver = new Receiver();
		new Invoker(new ConcreteCommand(receiver)).go();//调用者发送命令
	}
}
//抽象命令：（封装了一些基本调用操作）
interface Command{
	public void execute();
}
//具体命令：具体的调用操作（封装了接收命令的接受者）
class ConcreteCommand implements Command{//通过具体命令进行中转
	private Receiver receiver;//接受者要执行的调用的对象
	public ConcreteCommand(Receiver receiver){
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("发送命令");
		this.receiver.action();
	}	
}
//接收者：负责接收
class Receiver {
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("Receiver:"+"收到");
	}	
}
//调用者：负责发出命令
class Invoker{
	private Command command;
	public Invoker(Command command){
		this.command = command;
	}
	public void go(){//发命令方法
		command.execute();
	}
	
}