package com.hui.Thread;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by H on 2018/8/22.
 */
public class MainThread {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                sysOut("after sleep");
            }
        });
        thread.start();


        Calendar calendar = Calendar.getInstance();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                sysOut("run Task" + calendar.get(Calendar.SECOND));
            }
        };
        sysOut("" + System.currentTimeMillis());
        timer.schedule(timerTask, strToDate("2018-08-16 14:06:00"));
//        timer.cancel();
        sysOut(""+(timer == null));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sysOut("run Task" + calendar.get(Calendar.SECOND));
            }
        }, strToDate("2018-08-16 14:06:05"));
    }


    private static Date strToDate(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(string,pos);
        return date;
    }

    public static void sysOut(String string) {
        System.out.println(string);
    }
}
