import DueDate.DueDate;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class DueDateCalculatorTests {

    @Test
    public void testSubmitTimeIsBeforeOfWorkingHoursThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt6 = LocalDate.now().atTime(6, 0);

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(todayAt6, 15));
        assertEquals("Please submit in working hours!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsAfterOfWorkingHoursThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt6 = LocalDate.now().atTime(18, 0);

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(todayAt6, 15));
        assertEquals("Please submit in working hours!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsAfterOfWorkingHoursByOneMinuteThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt6 = LocalDate.now().atTime(17, 1);

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(todayAt6, 15));
        assertEquals("Please submit in working hours!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsAfterOfWorkingHoursByOneNanoSecondThrowsException() {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt6 = LocalDate.now().atTime(17, 0, 0, 1);

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(todayAt6, 15));
        assertEquals("Please submit in working hours!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsSaturdayThrowsException() {
        DueDate dueDate = new DueDate();

        LocalDateTime saturday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(saturday, 15));
        assertEquals("Cannot submit on a weekend!", exception.getMessage());
    }

    @Test
    public void testSubmitTimeIsSundayThrowsException() {
        DueDate dueDate = new DueDate();

        LocalDateTime sunday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        Throwable exception = assertThrows(Exception.class, () -> dueDate.dueDateCalculator(sunday, 15));
        assertEquals("Cannot submit on a weekend!", exception.getMessage());
    }

    @Test
    public void testSubmitMondayAtNineAM() throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime todayAt9 = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .atTime(9, 0);

        LocalDateTime expected = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .atTime(11, 0);

        assertEquals(expected, dueDate.dueDateCalculator(todayAt9, 2));
    }

    @Test
    public void testSubmitMondayAtFourPM() throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime mondayAt16 = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .atTime(16, 0);

        LocalDateTime expected = mondayAt16
                .with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                .with(LocalTime.of(12, 0));

        assertEquals(expected, dueDate.dueDateCalculator(mondayAt16, 4));
    }

    @Test
    public void testSubmitFridayAtNineAM() throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime fridayAt9 = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
                .atTime(9, 0);

        LocalDateTime expected = fridayAt9
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .with(LocalTime.of(11, 0));

        assertEquals(expected, dueDate.dueDateCalculator(fridayAt9, 10));
    }

    @Test
    public void testSubmitFridayAtFourPMTenMinutes() throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime fridayAt1610 = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
                .atTime(16, 10);

        LocalDateTime expected = fridayAt1610
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .with(LocalTime.of(13, 10));

        assertEquals(expected, dueDate.dueDateCalculator(fridayAt1610, 5));
    }

    @Test
    public void testSubmitMidweekWithALongTurnaroundTime() throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime tuesdayAt1035 = LocalDate
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY))
                .atTime(10, 35);

        LocalDateTime expected = tuesdayAt1035
                .plusDays(14);

        assertEquals(expected, dueDate.dueDateCalculator(tuesdayAt1035, 80));
    }

}
