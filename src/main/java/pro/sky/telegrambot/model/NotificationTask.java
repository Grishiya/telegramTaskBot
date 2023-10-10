package pro.sky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_task" )
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long chatId;

    private String notification;
@Column(name = "notification_send_Date")
    private LocalDateTime sendDate;

    public NotificationTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(chatId, that.chatId)
                && Objects.equals(notification, that.notification)
                && Objects.equals(sendDate, that.sendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notification, sendDate);
    }
}
