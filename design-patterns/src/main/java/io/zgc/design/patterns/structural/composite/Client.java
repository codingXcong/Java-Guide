package io.zgc.design.patterns.structural.composite;

/**
 * @author codingxcong
 * @date 2023-09-19
 */
public class Client {
    public static void main(String[] args) {
        Menu root = new Menu(1, "系统管理");
        Menu role = new Menu(2, "角色管理");
        root.addChildMenu(role);
        role.addChildMenu(new Menu(6,"固定角色"));
        role.addChildMenu(new Menu(7,"临时授予"));


        Menu user = new Menu(3, "用户管理");
        root.addChildMenu(user);


        user.addChildMenu(new Menu(4,"临时用户"));
        user.addChildMenu(new Menu(5,"注册用户"));


        //按照不同层级展示
        root.printMenu();
    }
}
