package org.example;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        Scanner sc = new Scanner(new File("checks.csv"));
        sc.useDelimiter("\n");
        List<String> strings = new ArrayList<>();
        while (sc.hasNext()) {
            strings.add(sc.next());
        }
        sc.close();
        Long l = Long.parseLong(strings.get(0).substring(0, strings.get(0).indexOf(",")));
        String w = strings.get(0).substring(strings.get(0).indexOf(",") + 2, strings.get(0).length() - 2);
        Long l2 = Long.parseLong(strings.get(1).substring(0, strings.get(1).indexOf(",")));
        String w2 = strings.get(1).substring(strings.get(1).indexOf(",") + 2, strings.get(1).length() - 2);

        List<Check> checks = new ArrayList<>();
        for (String string : strings) {
            checks.add(new Check(string.substring(string.indexOf(",") + 2, string.length() - 2), Long.parseLong(string.substring(0, string.indexOf(",")))));
        }

        Bot bot = new Bot();
        for (Check check : checks) {
            downloadFile(getFilePath(check.getCheck(), bot), "checks_images/" + check.getPid() + ".jpg", bot);
        }
    }

    public static org.telegram.telegrambots.meta.api.objects.File getFilePath(String fileId, Bot bot) throws TelegramApiException {
        GetFile getFile = new GetFile();
        getFile.setFileId(fileId);
        return bot.execute(getFile);
    }

    public static void downloadFile(org.telegram.telegrambots.meta.api.objects.File file, String localFilePath, Bot bot) throws IOException {
        java.io.File localFile = new java.io.File(localFilePath);
        InputStream is = new URL(file.getFileUrl(bot.getBotToken())).openStream();
        FileUtils.copyInputStreamToFile(is, localFile);
    }
}