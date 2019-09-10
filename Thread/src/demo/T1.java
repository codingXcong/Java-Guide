package demo;

public class T1 {
	private StringBuilder msg = new StringBuilder("default");
	private int num = 10;
	public StringBuilder getMsg() {
		return msg;
	}
	public int getNum() {
		return num;
	}
	public  void  print(){
		System.out.println("T1:msg="+msg+",num="+num);
	}
	
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "abc";
		String s3 = "ab"+"c";
		System.out.println(s1==s3);
	}
}
