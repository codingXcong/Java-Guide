package io.zgc.design.patterns.structural.proxy.dynamic;

/**
 * @author codingxcong
 * @date 2023-09-13
 */
public class Client {
    public static void main(String[] args) {
        TicketService ticketService = JdkStationProxy.getProxy(new Station());

        ticketService.sellTicket();
        ticketService.withdraw();
    }
}
