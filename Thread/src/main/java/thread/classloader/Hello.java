package thread.classloader;

/**
 *
 * @author zhangguicong
 * @date 2019-09-23
 */
public class Hello {
    static {
        System.out.println("static code block");
    }

    public String hello (String name) {
        return "hello "+name;
    }

}
