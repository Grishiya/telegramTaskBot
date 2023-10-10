package pro.sky.telegrambot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static NotificationTask parseNotificationTask(String text) {
        Pattern pattern = Pattern.compile("(.{5})([0-9.:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(text);
        NotificationTask notificationTask = new NotificationTask();
        if (matcher.matches()) {
            LocalDateTime localDateTime = LocalDateTime.parse(
                    matcher.group(2),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            notificationTask.setSendDate(localDateTime);
            notificationTask.setNotification(matcher.group(4));
        } else {
            throw new IllegalArgumentException("Неправильный формат времени");
        }
        return notificationTask;
    }
}
