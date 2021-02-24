import DueDate.DueDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class Main {

    public static void main(String[] args) throws Exception {
        DueDate dueDate = new DueDate();
        LocalDateTime mondayNoon = LocalDateTime
                .now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .with(LocalTime.of(16, 0));
        System.out.println(dueDate.dueDateCalculator(mondayNoon, 3));

    }
}