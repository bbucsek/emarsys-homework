package DueDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        LocalDateTime before = date.with(LocalTime.of(9, 0, 0, 0));
        return date.isBefore(before);
    }

    public boolean isAfterWorkingHours(LocalDateTime date) {
        LocalDateTime after = date.with(LocalTime.of(17, 0, 0, 0));
        return date.isAfter(after);
    }

    public boolean isNotBetweenWorkingHours(LocalDateTime date) {
        LocalDateTime nine = date.with(LocalTime.of(9, 0, 0, 0));
        return isAfterWorkingHours(date) || isBeforeWorkingHours(date) || date.isEqual(nine);
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
