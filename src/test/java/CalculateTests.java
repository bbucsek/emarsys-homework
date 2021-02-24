import DueDate.DueDate;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class CalculateTests {

    @Test
    public void calculateDaysTest() {
        DueDate dueDate = new DueDate();

        assertEquals(1, dueDate.calculateDays(8));
    }

    @Test
    public void calculateDaysTestWithRemainder() {
        DueDate dueDate = new DueDate();

        assertEquals(3, dueDate.calculateDays(26));
    }

    @Test
    public void calculateDaysTestWithHugeNumber() {
        DueDate dueDate = new DueDate();

        assertEquals(80807057, dueDate.calculateDays(646456456));
    }
    @Test
    public void calculateHoursTest() {
        DueDate dueDate = new DueDate();

        assertEquals(1, dueDate.calculateHours(9));
    }

    @Test
    public void calculateHoursTestWithRemainder() {
        DueDate dueDate = new DueDate();

        assertEquals(3, dueDate.calculateHours(11));
    }

    @Test
    public void calculateHoursTestWithHugeNumber() {
        DueDate dueDate = new DueDate();

        assertEquals(7, dueDate.calculateHours(646456455));
    }
}

