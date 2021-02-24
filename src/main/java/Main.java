import DueDate.DueDate;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime saturday = now.plusHours(7);
        DueDate dueDate = new DueDate();

        System.out.println(dueDate.calculateHours(12));


    }
}