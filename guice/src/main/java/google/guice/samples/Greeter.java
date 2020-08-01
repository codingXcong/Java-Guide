package google.guice.samples;

import com.google.inject.Inject;

/**
 * @author zhangguicong
 * @date 2020-07-23
 */
public class Greeter {
    private String message;
    private int count;

    // Greeter declares that it needs a string message and an integer
    // representing the number of time the message to be printed.
    // The @Inject annotation marks this constructor as eligible to be used by
    // Guice.
    @Inject
    Greeter(@Message String message, @Count int count) {
        this.message = message;
        this.count = count;
    }

    void sayHello() {
        for (int i=0; i < count; i++) {
            System.out.println(message);
        }
    }
}
