package pl.sda.javawwa22.imitationclasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultTicketService implements TicketService {

    Map<String, Integer> customerAskedTimesMap = new HashMap<>();
    private TicketRepository ticketRepository;

    public DefaultTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public double checkTicketPrice(String flightCode, String customerName) {
        int askedTimes = handleCustomerAsk(customerName);
        if(ticketRepository.noOfTicketsAvailable(flightCode) > 0) {
            try {
                return ticketRepository.getTicketPrice(flightCode, customerName, askedTimes);
            }
            catch(IOException e) {
                return -2.0;
            }
        }
        else {
            return -1.0;
        }
    }

    private int handleCustomerAsk(String customerName) {
        Integer askedTimes = 0;
        if(customerAskedTimesMap.containsKey(customerName)) {
            askedTimes = customerAskedTimesMap.get(customerName);
        }
        askedTimes += 1;
        customerAskedTimesMap.put(customerName, askedTimes);
        return askedTimes;
    }
}
