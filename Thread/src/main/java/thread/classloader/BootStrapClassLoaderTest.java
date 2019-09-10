package thread.classloader;

public class BootStrapClassLoaderTest {
    public static void main(String[] args) {
        /**
         * 输出 Bootstrap:null
         * 根类加载器获取不到引用，所以打印出来为null
         */
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
