package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
public class DefaultController
{
    @RequestMapping("/")
    public String index()
    {
        LocalDateTime today = LocalDateTime.now();
        return "<font size=\"+3\">Домашнее задание 12.1<br>" +
                today.format(DateTimeFormatter.ofPattern("Сегодня: d MMMM uuuu г. Сейчас: HH:mm:ss")) +
                "<br> Случайное число: " +
                new Random().nextInt() + "</font>";
    }
}
