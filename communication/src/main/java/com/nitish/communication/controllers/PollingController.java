package com.nitish.communication.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PollingController {
    public static void main(String args[]) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/photo_app";
        String username = "root";
        String password = "root";

        isDatabaseUp(jdbcUrl, username, password);
    }

    public static void isDatabaseUp(String jdbcUrl, String username, String password) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable dbStatusTask = new Runnable() {
            @Override
            public void run() {
                try(Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                    if(conn != null && !conn.isClosed()) {
                        System.out.println(getTimeStamp() + " : DB is up. Closing the executor");
                        scheduler.shutdown();
                    } else {
                        System.out.println(getTimeStamp() + " : DB is down");
                    }
                } catch (SQLException e) {
                    System.out.println(getTimeStamp() + " : DB connection failed. Status will be re-checked in 2 seconds.");
                }
            }
        };

        scheduler.scheduleAtFixedRate(dbStatusTask, 0, 2, TimeUnit.SECONDS);
    }

    public static String getTimeStamp() {
        long millis = System.currentTimeMillis();
        Date resultDate = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(resultDate);
    }
}
