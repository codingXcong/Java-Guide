package google.guice.samples.binding.annotations;

import java.lang.annotation.Target;
import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
@BindingAnnotation
@Target({ FIELD, PARAMETER, METHOD })
@Retention(RUNTIME)
@interface WinWord {

}

public class GuiceTester {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeSpellCheck();     
   } 
}

class TextEditor {

   private SpellChecker spellChecker;

   @Inject
   public TextEditor(@WinWord SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }

   public void makeSpellCheck() {
      spellChecker.checkSpelling(); 
   } 
}

//Binding Module
class TextEditorModule extends AbstractModule {
   @Override
   protected void configure() {
      bind(SpellChecker.class).annotatedWith(WinWord.class)
         .to(WinWordSpellCheckerImpl.class);    
   } 
}

//spell checker interface
interface SpellChecker {
   void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {
   @Override
   public void checkSpelling() {
      System.out.println("Inside checkSpelling." );
   } 
}

//subclass of SpellCheckerImpl
class WinWordSpellCheckerImpl extends SpellCheckerImpl {
   @Override
   public void checkSpelling() {
      System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling." );
   } 
}
