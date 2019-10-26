package pl.sda.javawwa22.imitationclasses;

import java.io.IOException;

public interface TicketRepository {

    double getTicketPrice(String flightCode, String customer, int askedTimes) throws IOException;
    int noOfTicketsAvailable(String flightCode);
    void buyTicket(String flighCode, String customer);

}
