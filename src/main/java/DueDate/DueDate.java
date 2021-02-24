package DueDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, Integer turnaroundTime) throws Exception {
        if (isWeekend(submitDate)) {
            throw new Exception("Cannot submit on a weekend!");
        }
        if (isBeforeWorkingHours(submitDate) || isAfterWorkingHours(submitDate)) {
            throw new Exception("Please submit in working hours!");
        }

        int days = calculateDays(turnaroundTime);
        int hours = calculateHours(turnaroundTime);

        LocalDateTime tempDate = calculateResolveDay(submitDate, days);
        return calculateResolveHours(tempDate, hours);
    }

    public boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isBeforeWorkingHours(LocalDateTime date) {
        return date.getHour() < 9;
    }

    public boolean isAfterWorkingHours(LocalDateTime date) {
        return date.getHour() > 17 || date.getHour() == 17 && date.getMinute() > 0;
    }

    public boolean isNotBetweenWorkingHours(LocalDateTime date) {
        return date.getHour() > 17 || date.getHour() <= 9;
    }

    public int calculateDays(Integer hours) {
        return hours / 8;
    }

    public int calculateHours(Integer hours) {
        return hours % 8;
    }

    public LocalDateTime calculateResolveDay(LocalDateTime date, Integer dayCount) {
        if (dayCount == 0) {
            return date;
        }
        LocalDateTime tempDate = date.plusDays(1);
        if (isWeekend(tempDate)) {
            return calculateResolveDay(tempDate, dayCount);
        }
        return calculateResolveDay(tempDate, dayCount - 1);
    }

    public LocalDateTime calculateResolveHours(LocalDateTime date, Integer hours) {
        if (hours == 0){
            return date;
        }
        LocalDateTime tempDate = date.plusHours(1);
        if (isNotBetweenWorkingHours(tempDate) || isWeekend(tempDate)) {
            return calculateResolveHours(tempDate, hours);
        }
        return calculateResolveHours(tempDate, hours - 1);
    }

}
