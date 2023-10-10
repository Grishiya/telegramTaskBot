package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.model.Parser;

public class TelegramBotServiceImpl implements TelegramBotService {
    @Override
    public void addTask(Message message) {
        NotificationTask task;
        long chatId = message.chat().id();
        SendMessage result;
        try {
            task = Parser.parseNotificationTask(message.text());
        } catch () {
        }

    }
}
