package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
