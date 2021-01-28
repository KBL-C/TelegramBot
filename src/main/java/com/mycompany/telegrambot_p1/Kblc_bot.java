/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.telegrambot_p1;

import com.vdurmont.emoji.EmojiParser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Kumar
 */
public class Kblc_bot extends TelegramLongPollingBot{

    @Override
    public String getBotToken() {
        return "1400461303:AAHSl1kUMcprVixDeDbH8YTgRDjH9T2erkQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        /*
        if(update.hasMessage() && update.getMessage().hasText()){
            String text = update.getMessage().getText();
            
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(text);
            
            try {
                execute(message);
            } catch (TelegramApiException ex) {
                Logger.getLogger(Kblc_bot.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
         if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Message contains photo
            // Set variables
            long chat_id = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String f_id = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getFileId();
            // Know photo width
            int f_width = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getWidth();
            // Know photo height
            int f_height = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getHeight();
            // Set photo caption
            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
            SendPhoto msg = new SendPhoto();
            msg.setChatId(Long.toString(chat_id));
            msg.setPhoto(new InputFile(f_id));
            msg.setCaption(caption);
            try {
                execute(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        
         /////////////////////////////////////
        else if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (message_text.equals("/start")) {
                // User send /start
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText(message_text);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/pic")) {
                // User sent /pic
                SendPhoto msg = new SendPhoto();
                                msg.setChatId(Long.toString(chat_id));
                                msg.setPhoto(new InputFile("AgACAgQAAxkBAAMJYAmoKlHct5sgLGEBd89HFbKWE7wAAsO0MRtXwVBQFSbO7WORtxqugyAnXQADAQADAgADeQADNWkEAAEeBA"));
                                msg.setCaption("Photo");
                        try {
                            execute(msg); // Call method to send the photo
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        /////////////////////////////////////
            }else if (message_text.equals("/markup") || message_text.equals("return")) {
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(Long.toString(chat_id));
                message.setText("Here is your keyboard");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();
                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("defaul image");
                row.add("open google");
                row.add("change keyboard");
                // Add the first row to the keyboard
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("number emoji");
                row.add("money emoji");
                row.add("smile emoji");
                // Add the second row to the keyboard
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }else if (message_text.equals("defaul image")) {
            SendPhoto msg = new SendPhoto();
            msg.setChatId(Long.toString(chat_id));
            msg.setPhoto(new InputFile("AgACAgQAAxkBAAMJYAmoKlHct5sgLGEBd89HFbKWE7wAAsO0MRtXwVBQFSbO7WORtxqugyAnXQADAQADAgADeQADNWkEAAEeBA"));
            msg.setCaption("Photo");
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message_text.equals("open google")) {
            SendMessage msg = new SendMessage();
            //String answear;
            String answer = "https://www.google.com/";
            msg.setChatId(Long.toString(chat_id));
            msg.setText(answer);
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message_text.equals("change keyboard")) {
            SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(Long.toString(chat_id));
                message.setText("Here is your limited keyboard");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();
                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("return");
                row.add("0");
                row.add("1");
                row.add("2");
                row.add("3");
                row.add("4");
                row.add("5");
                // Add the first row to the keyboard
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("a");
                row.add("e");
                row.add("i");
                row.add("o");
                row.add("u");
                // Add the second row to the keyboard
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("b");
                row.add("c");
                row.add("d");
                row.add("e");
                row.add("f");
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }else if (message_text.equals("number emoji")) {
            SendMessage message = new SendMessage();
            String number_emoji = EmojiParser.parseToUnicode(":phone: share your number");         
            message.setChatId(Long.toString(chat_id));
            message.setText(number_emoji);
            try {
                execute(message); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message_text.equals("money emoji")) {
            SendMessage message = new SendMessage();
            String money_emoji = EmojiParser.parseToUnicode(":moneybag:");
            message.setChatId(Long.toString(chat_id));
            message.setText(money_emoji);
            try {
                execute(message); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message_text.equals("smile emoji")) {
            SendMessage msg = new SendMessage();
            String answer = EmojiParser.parseToUnicode(":smile:");
            msg.setChatId(Long.toString(chat_id));
            msg.setText(answer);
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }        
        }else if (message_text.equals("/hide")) {
            SendMessage msg = new SendMessage();
            msg.setChatId(Long.toString(chat_id));
            msg.setText("Keyboard hidden");
            ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
            msg.setReplyMarkup(keyboardMarkup);
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if(message_text.equals("/help")){
            SendMessage msg = new SendMessage();
            String instruction = " HOW TO USE THIS BOT " + " /start -> return your message " + 
                    " /pic -> return the default pic (if you send a pic, it returns you the same pic with its sizes) " + "/markup -> change keyboard (check the keyboard button to see what they do)" +
                    " /hide -> hide the new keyboard ";
            msg.setChatId(Long.toString(chat_id));
            msg.setText(instruction);
            try{
                execute(msg);
            }catch (TelegramApiException e) {
                e.printStackTrace();
            }
            
        }else{
                // Unknown command
                SendMessage message = new SendMessage(); // Create a message object object
                        message.setChatId(Long.toString(chat_id));
                        message.setText("Unknown command");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
         
    }

    @Override
    public String getBotUsername() {
        
        return "Kblc_bot";
    }
    
}
