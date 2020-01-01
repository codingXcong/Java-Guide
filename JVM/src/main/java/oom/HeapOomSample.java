package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * vm args:
 *    -Xms10m -Xmx10m -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * @author zhangguicong
 * @date 2019-12-23
 */
public class HeapOomSample {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        while (true) {
            personList.add(new Person("zs",23));
        }
    }

    static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
