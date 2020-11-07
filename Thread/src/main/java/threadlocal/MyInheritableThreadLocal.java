package threadlocal;

import com.alibaba.fastjson.JSONObject;


public class MyInheritableThreadLocal<T> extends InheritableThreadLocal<T> {
    @Override
    protected T childValue(T parentValue) {
        String value = JSONObject.toJSONString(parentValue);
        return (T) JSONObject.parseObject(value, parentValue.getClass());
    }
}
