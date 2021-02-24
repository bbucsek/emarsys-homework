import DueDate.DueDate;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class IsWorkingHourTests {

    @Test
    public void testIsNoonBetweenWorkingDay() {
        DueDate dueDate = new DueDate();

        LocalDateTime mondayNoon = LocalDateTime
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .with(LocalTime.of(12, 0));


        assertFalse(dueDate.isNotBetweenWorkingHours(mondayNoon));
    }

    @Test
    public void testIsNightBetweenWorkingDay() {
        DueDate dueDate = new DueDate();

        LocalDateTime mondayNoon = LocalDateTime
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .with(LocalTime.of(20, 0));


        assertTrue(dueDate.isNotBetweenWorkingHours(mondayNoon));
    }
}
