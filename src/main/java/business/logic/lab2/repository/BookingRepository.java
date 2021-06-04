package business.logic.lab2.repository;

import business.logic.lab2.entity.Booking;
import business.logic.lab2.utils.BookingState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> getAllByState(BookingState state);

    Optional<Booking> findById(Integer id);

    /*@Query(value = "SELECT * FROM BOOKING" +
            "where date_part('day', age(current_date, check_in_date)) <= 3" +
            "and date_part(\"month\", age(current_date, check_in_date)) = 0" +
            "and date_part(\"year\", age(current_date, check_in_date)) = 0" +
            "and (current_date < check_in_date)" +
            "and b.hotel_confirm = true;", nativeQuery = true)
    List<Booking> getReminders();*/

    @Query(value = "select b from Booking b where checkIn = :date")
    List<Booking> getAllByCheckIn(@Param("date") Date date);
}