import DueDate.DueDate;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class IsWorkingHourTests {

    @Test
    public void testMondayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        assertFalse(dueDate.isWeekend(monday));
    }

}
