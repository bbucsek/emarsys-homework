import DueDate.DueDate;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Test
    public void calculateResolveDayTestWithoutWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime expected = monday.plusDays(4);

        assertEquals(expected, dueDate.calculateResolveDay(monday, 4));
    }

    @Test
    public void calculateResolveDayTestWithWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime expected = monday.plusDays(8);

        assertEquals(expected, dueDate.calculateResolveDay(monday, 6));
    }

    @Test
    public void calculateResolveDayTestWithWeekendFromFriday() {
        DueDate dueDate = new DueDate();
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        LocalDateTime expected = monday.plusDays(3);

        assertEquals(expected, dueDate.calculateResolveDay(monday, 1));
    }

    @Test
    public void calculateResolveHoursTestWithinWorkingHours() {
        DueDate dueDate = new DueDate();
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime mondayAtNoon = monday.with(LocalTime.of(12, 0));
        LocalDateTime expected = mondayAtNoon.plusHours(3);

        assertEquals(expected, dueDate.calculateResolveHours(mondayAtNoon, 3));
    }

    @Test
    public void calculateResolveHoursTestAfterWorkingHours() {
        DueDate dueDate = new DueDate();
        LocalDateTime monday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime mondayAtFourPM = monday.with(LocalTime.of(16, 10));
        LocalDateTime expected = mondayAtFourPM.plusHours(19);

        assertEquals(expected, dueDate.calculateResolveHours(mondayAtFourPM, 3));
    }

    @Test
    public void calculateResolveHoursTestAfterWeekend() {
        DueDate dueDate = new DueDate();
        LocalDateTime friday = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        LocalDateTime fridayAtFourPM = friday.with(LocalTime.of(16, 10));
        LocalDateTime expected = fridayAtFourPM.plusHours(67);

        assertEquals(expected, dueDate.calculateResolveHours(fridayAtFourPM, 3));
    }

}

