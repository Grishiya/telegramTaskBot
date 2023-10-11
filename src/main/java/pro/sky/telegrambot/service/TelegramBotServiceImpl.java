package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.model.Parser;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {
    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;

    public TelegramBotServiceImpl(TelegramBot telegramBot, NotificationTaskRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }


    @Override
    public void addTask(Message message) {
        NotificationTask task;
        long chatId = message.chat().id();
        SendMessage result;
        try {
            task = Parser.parseNotificationTask(message.text());
        } catch (Exception e) {
            result = new SendMessage(chatId, "Bad notification");
            telegramBot.execute(result);
            return;
        }
        repository.save(task);
        result = new SendMessage(chatId, String.format(task.getNotification(), task.getSendDate()));
        telegramBot.execute(result);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void Notification() {
        var time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        var result = repository.findByNotificationSendDate(time);
        for (var t : result) {
            var response = new SendMessage(
                    t.getChatId(), t.getNotification()
            );
            telegramBot.execute(response);
        }
    }
}
