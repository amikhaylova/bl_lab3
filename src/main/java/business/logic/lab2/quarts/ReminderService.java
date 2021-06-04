package business.logic.lab2.quarts;

import business.logic.lab2.dto.EmailMessageDTO;
import business.logic.lab2.entity.Booking;
import business.logic.lab2.repository.BookingRepository;
import business.logic.lab2.service.Queue.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {
    @Autowired
    BookingRepository repository;

    @Autowired
    MessageProducer messageProducer;

    public void putRemindsInQueue() {
        LocalDate twoDaysAfterToday = LocalDate.now().plusDays(2);
        List<Booking> bookingList = repository.getAllByCheckIn(java.sql.Date.valueOf(twoDaysAfterToday));
        for (Booking b : bookingList) {
            EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
            emailMessageDTO.setBeginningDate(b.getCheckIn());
            emailMessageDTO.setEmail(b.getUser().getEmail());
            try {
                messageProducer.produceMessage(emailMessageDTO);
            } catch (JMSException e) {
                System.out.println("Не удалось поместить сообщение в очередь.");
                System.out.println(e.getMessage());
            }

        }
    }

}
