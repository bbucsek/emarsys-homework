import DueDate.DueDate;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class IsWeekendTests {

    @Test
    public void testWeekdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDateTime tuesday = now.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        LocalDateTime wednesday = now.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        LocalDateTime thursday = now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        LocalDateTime friday = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        assertFalse(dueDate.isNextDayWeekend(monday));
        assertFalse(dueDate.isNextDayWeekend(tuesday));
        assertFalse(dueDate.isNextDayWeekend(thursday));
        assertFalse(dueDate.isNextDayWeekend(wednesday));
        assertFalse(dueDate.isNextDayWeekend(friday));

    }

    @Test
    public void testSaturdayAndSundayIsWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime saturday = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDateTime sunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        assertTrue(dueDate.isNextDayWeekend(saturday));
        assertTrue(dueDate.isNextDayWeekend(sunday));

    }
}
