package google.guice.samples.binding;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


public class MultiValue {
  Integer num;
  public MultiValue(int i) {
    this.num = i;
    System.out.println(i);
  }

  @Override public String toString() {
    return "MultiValue{" + "num=" + num + '}';
  }

  public static void main(String[] args) {
    Injector i = Guice.createInjector(new MultiValueModule());
    List<MultiValue> instance = i.getInstance(Key.get(new TypeLiteral<List<MultiValue>>(){}));
    System.out.println (instance);

    Client client = i.getInstance(Client.class);
    client.toSee();
  }

}

class Client {

  @Inject
  private List<MultiValue> multiValueList;

  public void toSee() {
    System.out.println(multiValueList);
  }

}

class MultiValueModule extends AbstractModule {

  /*@Override
  public void configure() {
    bind(MultiValue.class).annotatedWith(Names.named("1")).toInstance(new MultiValue(1));
    bind(new TypeLiteral<List<MultiValue>>(){})
            .toProvider(new Provider<List<MultiValue>>() {
              @Inject @Named("1") MultiValue v;
              public List<MultiValue> get() {
                ArrayList<MultiValue> arrayList = new ArrayList<MultiValue>();
                arrayList.add (v);
                return arrayList;
              }
            });
  }*/


  @Override
  public void configure() {
    //bind(MultiValue.class).annotatedWith(Names.named("1")).toInstance(new MultiValue(1));
    bind(new TypeLiteral<List<MultiValue>>(){}).toProvider(new MultiValueProvider());
  }

}

class MultiValueProvider extends TypeLiteral<List<MultiValue>> implements Provider<List<MultiValue>> {

  @Override public List<MultiValue> get() {
    ArrayList<MultiValue> arrayList = new ArrayList<MultiValue>();

    MultiValue m1 = new MultiValue(1);
    MultiValue m2 = new MultiValue(2);

    arrayList.add(m1);
    arrayList.add(m2);
    return arrayList;
  }
}


