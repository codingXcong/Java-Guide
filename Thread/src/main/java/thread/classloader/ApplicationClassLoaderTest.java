package thread.classloader;

public class ApplicationClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("classloader: "+ApplicationClassLoaderTest.class.getClassLoader());
        System.out.println(System.getProperty("java.class.path"));
    }
}
