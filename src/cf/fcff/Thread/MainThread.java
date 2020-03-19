package cf.fcff.Thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by H on 2018/8/22.
 */
public class MainThread {
    private static MyThread myThread;
    public static void main(String[] args) {
        startThread();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startThread();
            }
        },5*1000);

/*
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
*/

/*
        ThreadFactory threadFactory = new ThreadFactoryBuilder().build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
*/
/*
        singleThreadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        singleThreadPool.shutdown();
*/



/*
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
*/


/*
        int initialDelay = 5;
        int period = 10;
        BasicThreadFactory.Builder builder = new BasicThreadFactory.Builder();
        builder.namingPattern("example-schedule-pool-%d");
        builder.daemon(true);
        BasicThreadFactory basicThreadFactory = builder.build();

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,basicThreadFactory);
        executorService.scheduleAtFixedRate(() -> {
            //do something
            System.out.println("this");
        },initialDelay,period, TimeUnit.HOURS);
*/
    }

    private static void startThread() {
        if (myThread != null) {
            if (!myThread.isInterrupted()) {
                myThread.interrupt();
            }
        }
        myThread = new MyThread();
        myThread.start();
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            sysOut(System.currentTimeMillis()+"");
        }
    }

    private static Date strToDate(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(string, pos);
        return date;
    }

    public static void sysOut(String string) {
        System.out.println(string);
    }
}
