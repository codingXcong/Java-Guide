package io.zgc.design.patterns.structural.proxy.cglib;

import io.zgc.design.patterns.structural.proxy.dynamic.TicketService;

public class Station implements TicketService {
  
    @Override  
    public void sellTicket() {  
        System.out.println("\n\t售票.....\n");  
    }  
  
    @Override  
    public void inquire() {  
        System.out.println("\n\t问询。。。。\n");  
    }  
  
    @Override  
    public void withdraw() {  
        System.out.println("\n\t退票......\n");  
    }  
  
}
