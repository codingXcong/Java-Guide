package google.guice.samples;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhangguicong
 * @date 2020-07-23
 */
@Qualifier
@Retention(RUNTIME)
public @interface Count {
}
