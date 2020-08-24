package google.guice.samples; /**
 * Guice module that provides bindings for message and count used in
 * {@link Greeter}.
 */
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class DemoModule extends AbstractModule {

    /**
     * @Provides : Guice injector internally creates Providers for all the object it knows how to create.
     */
    @Provides
    @Count
    static Integer provideCount() {
        return 3;
    }

    @Provides
    @Message
    static String provideMessage() {
        return "hello world";
    }

    @Override protected void configure() {

    }
}
