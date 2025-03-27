package cn.zgc.network.nio.buffer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangguicong
 * @date 2025/3/27
 */
public class T {
    public static void main(String[] args) {
        /*Map<String, String> map = new LinkedHashMap<>();
        map.put("a", "2");
        map.put("g", "3");
        map.put("r", "1");
        map.put("e", "23");

        for (Map.Entry < String, String > entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }*/

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        //访问元素2,该元素会被移动至链表末端
        map.get(2);
        //访问元素3,该元素会被移动至链表末端
        map.get(3);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
