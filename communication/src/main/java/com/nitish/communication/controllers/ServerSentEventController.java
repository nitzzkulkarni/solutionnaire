package com.nitish.communication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class ServerSentEventController {

    @GetMapping("/sse")
    public SseEmitter eventStream() {
        SseEmitter sseEmitter = new SseEmitter(0L);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
           try {
               //generating random price
               double price = 100 + (1 + Math.random() * (4));
               DecimalFormat df = new DecimalFormat("#.##");

               long millis = System.currentTimeMillis();
               Date resultDate = new Date(millis);
               SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

               String stockPrice = "Stock price at " + sdf.format(resultDate) + " = " + df.format(price);
               sseEmitter.send(SseEmitter.event().name("stock-prices").data(stockPrice));
           } catch (IOException e) {
               sseEmitter.completeWithError(e);
           }
        //sending the event for every second
        }, 0, 1, TimeUnit.SECONDS);

        return sseEmitter;
    }
}
