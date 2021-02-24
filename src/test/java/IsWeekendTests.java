import DueDate.DueDate;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class IsWeekendTests {

    @Test
    public void testMondayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        assertFalse(dueDate.isNextDayWeekend(monday));
    }

    @Test
    public void testTuesdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tuesday = now.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));

        assertFalse(dueDate.isNextDayWeekend(tuesday));
    }

    @Test
    public void testWednesdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime wednesday = now.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));

        assertFalse(dueDate.isNextDayWeekend(wednesday));
    }

    @Test
    public void testThursdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thursday = now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));

        assertFalse(dueDate.isNextDayWeekend(thursday));
    }

    @Test
    public void testFridayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime friday = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        assertFalse(dueDate.isNextDayWeekend(friday));
    }

    @Test
    public void testSaturdayIsWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime saturday = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        assertTrue(dueDate.isNextDayWeekend(saturday));
    }

    @Test
    public void testSundayIsWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        assertTrue(dueDate.isNextDayWeekend(sunday));
    }
}
