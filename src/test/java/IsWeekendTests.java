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
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        assertFalse(dueDate.isWeekend(monday));
    }

    @Test
    public void testTuesdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tuesday = now.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));

        assertFalse(dueDate.isWeekend(tuesday));
    }

    @Test
    public void testWednesdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime wednesday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));

        assertFalse(dueDate.isWeekend(wednesday));
    }

    @Test
    public void testThursdayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime thursday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));

        assertFalse(dueDate.isWeekend(thursday));
    }

    @Test
    public void testFridayIsNotWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime friday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        assertFalse(dueDate.isWeekend(friday));
    }

    @Test
    public void testSaturdayIsWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime saturday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        assertTrue(dueDate.isWeekend(saturday));
    }

    @Test
    public void testSundayIsWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime sunday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        assertTrue(dueDate.isWeekend(sunday));
    }
}
