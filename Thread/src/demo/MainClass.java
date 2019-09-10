package demo;
public class MainClass {

    public static void main(String[] args) {     
        Fu f = new Zi();                         //Zi method3
        Zi z = new Zi();                         //Zi method3
        System.out.println("f.num:"+f.num);      //f.num:5
        System.out.println("z.num:"+z.num);      //z.num:6
        System.out.println("f.num:"+f.num2);     //f.num:9
        System.out.println("z.num:"+z.num2);     //z.num:8
        f.method1();                             //Zi method1
        z.method1();                             //Zi method1
        f.method4();                             //Fu method4
        z.method4();                             //Zi method4
    }
}
abstract  class Fu{
    public  int num = 5;
    public  int num2 = 7;
    public Fu(){
        num2 = 9;
        method3();
    }
    public void method1(){
        System.out.println("Fu method1");
    }
    
    void method3(){
        System.out.println("Fu method3");
    }
   static void method4(){
        System.out.println("Fu method4");
    }
}


class Zi extends Fu{
    public int num = 6;
    public  int num2 = 8;
    public void method1(){
        System.out.println("Zi method1");
    }
    
    void method3(){
        System.out.println("Zi method3");
    }
    
    static void method4(){
        System.out.println("Zi method4");
    }
}