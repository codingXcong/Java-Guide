package google.guice.samples.failfast;

import com.google.inject.*;

public class GuiceTester {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeSpellCheck();
   } 
}


class TextEditor {
   @Inject private Awrong wrong;
   @Inject private SpellChecker spellChecker;

   public TextEditor() {

      System.out.println("TextEditor");
   }

   public void makeSpellCheck() {
      wrong.say();
      spellChecker.checkSpelling();
   } 
}


//Binding Module
class TextEditorModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(SpellChecker.class).toProvider(SpellCheckerProvider.class);
      bind(Awrong.class).toProvider(AwrongProvider.class);
   }

}


//spell checker interface
interface SpellChecker {
   void checkSpelling();
}


//spell checker implementation
class SpellCheckerImpl implements SpellChecker {
   private String dbUrl;
   private String user;
   private Integer timeout;

   public SpellCheckerImpl(String dbUrl,
      String user, 
      Integer timeout) {
      this.dbUrl = dbUrl;
      this.user = user;
      this.timeout = timeout;
      System.out.println("SpellCheckerImpl");
      throw new RuntimeException("IOException");
   }

   @Override
   public void checkSpelling() { 
      System.out.println("Inside checkSpelling." );
      System.out.println(dbUrl);
      System.out.println(user);
      System.out.println(timeout);
   }
}

class SpellCheckerProvider implements Provider<SpellChecker> {
   @Override
   public SpellChecker get() {
      String dbUrl = "jdbc:mysql://localhost:5326/emp";
      String user = "user";
      int timeout = 100;
      SpellChecker
              SpellChecker = new SpellCheckerImpl(dbUrl, user, timeout);
      return SpellChecker;
   }
}

interface Awrong {
   void say();
}

class AwrongImpl implements Awrong {

   private String name;

   public AwrongImpl(String name) {
      this.name = name;
      System.out.println("AwrongImpl");
   }

   @Override public void say() {
      System.out.println("hello,"+name);
   }
}

class AwrongProvider implements Provider<Awrong> {

   @Override public Awrong get() {
      return new AwrongImpl("zgc");
   }
}


