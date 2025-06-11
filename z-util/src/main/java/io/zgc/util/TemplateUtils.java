package io.zgc.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * from nacos
 *
 * Template Utils.
 *
 * @author pbting
 * @date 2019-03-04 1:31 PM
 */
public class TemplateUtils {

    private static final Logger log = LoggerFactory.getLogger(TemplateUtils.class);
    /**
     * Execute if string not empty.
     *
     * @param source   source
     * @param runnable execute runnable
     */
    public static void stringNotEmptyAndThenExecute(String source, Runnable runnable) {
        
        if (StringUtils.isNotEmpty(source)) {
            
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("string not empty and then execute cause an exception.", e);
            }
        }
    }
    
    /**
     * Execute if string empty.
     *
     * @param source   empty source
     * @param callable execute callable
     * @return result
     */
    public static String stringEmptyAndThenExecute(String source, Callable<String> callable) {
        
        if (StringUtils.isEmpty(source)) {
            
            try {
                return callable.call();
            } catch (Exception e) {
                log.error("string empty and then execute cause an exception.", e);
            }
        }
        
        return source == null ? null : source.trim();
    }
    
    /**
     * Execute if string blank.
     *
     * @param source   empty source
     * @param callable execute callable
     * @return result
     */
    public static String stringBlankAndThenExecute(String source, Callable<String> callable) {
        
        if (StringUtils.isBlank(source)) {
            
            try {
                return callable.call();
            } catch (Exception e) {
                log.error("string empty and then execute cause an exception.", e);
            }
        }
        
        return source == null ? null : source.trim();
    }
}