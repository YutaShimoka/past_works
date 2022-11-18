package demo.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Hoge {

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
    public void xxx() {
        System.out.println("The time is now " + dateFormat(LocalDateTime.now()));
    }

    private String dateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
