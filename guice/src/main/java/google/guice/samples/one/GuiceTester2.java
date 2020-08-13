package google.guice.samples.one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import javax.inject.Inject;

/**
 * @author zhangguicong
 * @date 2020-08-04
 */
public class GuiceTester2 {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule2());
        TextEditor2 editor = injector.getInstance(TextEditor2.class);
        editor.makeSpellCheck();
    }
}


class TextEditor2 {
    @Inject private SpellChecker2 spellChecker2;


    /*public TextEditor2(SpellChecker2 spellChecker2) {
        this.spellChecker2 = spellChecker2;
    }*/

    public void makeSpellCheck() {
        spellChecker2.checkSpelling();
    }
}

//Binding Module
class TextEditorModule2 extends AbstractModule {
    @Override
    protected void configure() {
        bind(SpellChecker2.class).to(SpellCheckerImpl2.class);
        bind(SpellCheckerImpl2.class).to(WinWordSpellCheckerImpl2.class);
    }
}

//spell checker interface
interface SpellChecker2 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl2 implements SpellChecker2 {
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
}
//subclass of SpellCheckerImpl
class WinWordSpellCheckerImpl2 extends SpellCheckerImpl2 {
    @Override
    public void checkSpelling() {
        System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling." );
    }
}
