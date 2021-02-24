import DueDate.DueDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class Main {

    public static void main(String[] args) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        DueDate dueDate = new DueDate();
        LocalDateTime fridayAtFourPM = monday.with(LocalTime.of(17, 1));

        System.out.println(fridayAtFourPM);
        System.out.println(dueDate.dueDateCalculator(fridayAtFourPM, 2));


    }
}