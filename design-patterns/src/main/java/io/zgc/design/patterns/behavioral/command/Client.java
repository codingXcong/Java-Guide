package io.zgc.design.patterns.behavioral.command;

public class Client {

    public static void main(String[] args) {


//        LeiReceiver leiReceiver = new LeiReceiver();
//        leiReceiver.travel();

        TeacherTongInvoker invoker = new TeacherTongInvoker();
        invoker.setCommand(new OnlineCommand());

        invoker.call();
    }
}
