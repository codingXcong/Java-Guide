package io.zgc.design.patterns.structural.proxy.statics;

/**
 * @author codingxcong
 * @date 2023-09-13
 */
public class Client {
    public static void main(String[] args) {
        TicketService ticketService = new StationProxy(new Station());
        ticketService.sellTicket();
        ticketService.withdraw();
    }
}
