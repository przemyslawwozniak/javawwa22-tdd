package pl.sda.javawwa22.imitationclasses;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DefaultTicketServiceTest {

    TicketService ticketService;

    @Test
    public void price_increments_with_requests() throws Exception {
        //given:
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.noOfTicketsAvailable("WWA-STG")).thenReturn(48);
        when(ticketRepository.getTicketPrice("WWA-STG", "PW", 1))
                .thenReturn(650.0);
        when(ticketRepository.getTicketPrice("WWA-STG", "PW", 2))
                .thenReturn(725.0);
        when(ticketRepository.getTicketPrice("WWA-STG", "PW", 3))
                .thenReturn(759.0);
        when(ticketRepository.getTicketPrice("WWA-STG", "PW", 4))
                .thenReturn(899.0);
        when(ticketRepository.getTicketPrice("WWA-STG", "Another Client", 1))
                .thenReturn(599.0);

        ticketService = new DefaultTicketService(ticketRepository);

        //when:
        double price0 = ticketService.checkTicketPrice("WWA-STG", "PW");
        double price1 = ticketService.checkTicketPrice("WWA-STG", "PW");
        double priceAnother = ticketService.checkTicketPrice("WWA-STG", "Another Client");
        double price2 = ticketService.checkTicketPrice("WWA-STG", "PW");
        double price3 = ticketService.checkTicketPrice("WWA-STG", "PW");

        //then:
        assertEquals(650.0, price0, 0.0001);
        assertEquals(725.0, price1, 0.0001);
        assertEquals(759.0, price2, 0.0001);
        assertEquals(899.0, price3, 0.0001);
        assertEquals(599.0, priceAnother, 0.0001);
    }

    @Test
    public void indicates_no_tickets_available() {
        //given:
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.noOfTicketsAvailable("WWA-STG")).thenReturn(0);

        ticketService = new DefaultTicketService(ticketRepository);

        //when:
        double price0 = ticketService.checkTicketPrice("WWA-STG", "PW");
        double price1 = ticketService.checkTicketPrice("WWA-STG", "AC");

        //then:
        assertEquals(-1.0, price0, 0.0001);
        assertEquals(-1.0, price1, 0.0001);
    }

    @Test
    public void indicates_cannot_establish_db_connection() throws Exception {
        //given:
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.noOfTicketsAvailable("WWA-STG")).thenReturn(1);
        //doThrow(new IOException()).when(ticketRepository.getTicketPrice(anyString(), anyString(), anyInt()));
        when(ticketRepository.getTicketPrice(anyString(), anyString(), anyInt())).thenThrow(new IOException());

        ticketService = new DefaultTicketService(ticketRepository);

        //when:
        double price0 = ticketService.checkTicketPrice("WWA-STG", "PW");

        //then:
        assertEquals(-2.0, price0, 0.0001);
    }

}
