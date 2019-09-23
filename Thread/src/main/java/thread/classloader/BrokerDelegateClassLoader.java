package thread.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 打破双亲委派模式
 * @author zhangguicong
 * @date 2019-09-23
 */
public class BrokerDelegateClassLoader extends ClassLoader{

    private final Path classDir = Paths.get("/Users/classloader");;

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (name.startsWith("java.") || name.startsWith("javax.")) {
                    try {
                        c = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        c = this.findClass(name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (c == null ) {
                        if (getParent()!=null) {
                            c = getParent().loadClass(name);
                        } else {
                            c = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            if (null == c) {
                throw new ClassNotFoundException("The class "+name+" not found.");
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classBytes = this.readClassBytes(name);

        if (null == classBytes || classBytes.length == 0 ){
            throw new ClassNotFoundException("Can not load the class "+name);
        }

        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] readClassBytes(String className) throws ClassNotFoundException {
        String classPath = className.replace(".","/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class "+className+" not found");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class "+className+" occur error");
        }
    }
}
