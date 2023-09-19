package io.zgc.design.patterns;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author codingxcong
 * @date 2023-09-10
 */
public class Main {

    public static void main(String[] args) {
        Set<String> data = new HashSet<>(300);
        for (int i = 0; i<300; i++) {
            data.add(""+i);
        }
        //[230, 110, 231, 111, 232, 112, 233, 113, 234, 114]
        //[235, 115, 236, 116, 237, 117, 238, 118, 239, 119]
        List<String> pageLimit = JavaMemoryPageUtil.getPageLimit(data, 4, 10);
        System.out.println(pageLimit);
    }
}
