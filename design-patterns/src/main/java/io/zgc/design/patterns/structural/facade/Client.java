package io.zgc.design.patterns.structural.facade;

/**
 * @author codingxcong
 * @date 2023-09-13
 */
public class Client {
    public static void main(String[] args) {
//        Police police = new Police();
//        police.resgister("张三");
//
//        Edu edu = new Edu();
//        edu.assignSchool("张三");
//
//        Social social = new Social();
//        social.handleSocial("张三");

        WeiXinFacade facade = new WeiXinFacade();
        facade.handleXxx("张三");
    }
}
