package google.guice.samples.binding;

import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-04
 */
public class TypeLiteralSamples {

    public static void main(String[] args) {

        //TypeLiteral<?> a = TypeLiteral.get(getClass().getField("wildcardExtends").getGenericType());
        TypeLiteral<?> b = TypeLiteral.get(Types.listOf(Types.subtypeOf(CharSequence.class)));
        TypeLiteral<?> c = new TypeLiteral<List<MultiValue>>() {};

        System.out.println(c.getClass().getName());
    }


}
