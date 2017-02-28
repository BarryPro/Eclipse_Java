package others.belong.application;

public class PersonalCalorie {
	{
		System.out.println("sex:(m/w) weight:(57kg) height:(165cm)");
	}
	public String calorieOfDay(String sex ,double weight, double height,int age){
		if(sex.equals("m")){
			return (10*weight+6.25*height-5*age+5)+"卡路里/天";
		} else {
			return (10*weight+6.25*height-5*age-161)+"卡路里/天";
		}
	}
}
