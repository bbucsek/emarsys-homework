package DueDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, Integer turnaroundTime) throws Exception {
        if (isNextDayWeekend(submitDate)) {
            throw new Exception("cannot submit on a weekend!");
        }
        if (submitDate.getHour() > 17 || submitDate.getHour() < 9) {
            throw new Exception("Please submit in working hours!");
        }
        return submitDate;
    }

    public boolean isNextDayWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public int calculateDays(Integer hours) {
        return hours / 8;
    }

    public int calculateHours(Integer hours) {
        return hours % 8;
    }
}
