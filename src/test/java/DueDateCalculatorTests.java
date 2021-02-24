import DueDate.DueDate;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class DueDateCalculatorTests {

    @Test
    public void testSubmitTimeIsOutOfWorkingHoursThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt6 = LocalDate.now().atTime(6, 0);

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(todayAt6, 15));
        assertEquals("Please submit in working hours!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsSaturdayThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime saturday = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(saturday, 15));
        assertEquals("cannot submit on a weekend!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsSundayThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime sunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(sunday, 15));
        assertEquals("cannot submit on a weekend!", exception.getMessage());
    }
}
