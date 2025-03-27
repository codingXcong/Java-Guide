package cn.zgc.network.nio.buffer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangguicong
 * @date 2025/3/27
 */
public class LruDemo {

    public static void main(String[] args) {

    }

    static class SimpleLRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public SimpleLRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

}
