package com.hui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by H on 2018/9/5.
 */
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 5);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        System.out.println(year+"年"+(month+1)+"月"+day+"日"+hour+"时"+min+"分"+sec+"秒");

        Date date = calendar.getTime();
        /**
         * y-MM-dd HH:mm:ss : 2018-09-05 09:01:01
         * y-M-d H:m:s : 2018-9-5 9:1:1
         * yy : 18
         * HH : 15
         * hh : 03
         */
        System.out.println(new SimpleDateFormat("yy-MM-dd HH:mm:ss ").format(date));//2018-09-05
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
        SimpleDateFormat myFmt4=new SimpleDateFormat("一年中的第 D 天,一年中第 w 个星期,一月中第 W 个星期,在一天中 k 时, z时区");
        System.out.println(myFmt.format(date));
        System.out.println(myFmt4.format(date));

        /**
         * US LONG : September Wednesday
         * US SHORT :Sep Wed
         *
         * CHINA LONG : 九月 星期三
         * CHINA SHORT : 九月 星期三
         *
         */
        System.out.println(Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));

        /**
         *
         * US FULL : Wednesday, September 5, 2018
         * US LONG : September 5, 2018
         * US MEDIUM : Sep 5, 2018
         * US SHORT : 9/5/18
         *
         * CHINA FULL : 2018年9月5日 星期三
         * CHINA LONG : 2018年9月5日
         * CHINA MEDIUM : 2018-9-5
         * CHINA SHORT : 18-9-5
         */
        System.out.println(SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL,Locale.CHINA).format(date));
        System.out.println(SimpleDateFormat.getDateInstance().format(date));//MEDIUM

        /**
         * US FULL : 3:15:49 PM CST
         * US LONG : 3:15:37 PM CST
         * US MEDIUM : 3:15:26 PM
         * US SHORT : 3:15 PM
         *
         * CHINA FULL : 下午03时14分21秒 CST
         * CHINA LONG : 下午03时14分33秒
         * CHINA MEDIUM : 15:14:44
         * CHINA SHORT : 下午3:14
         */
        System.out.println(SimpleDateFormat.getTimeInstance(SimpleDateFormat.FULL, Locale.CHINA).format(date));
        System.out.println(SimpleDateFormat.getTimeInstance().format(date));//MEDIUM


        dateTest();
    }

    public static void dateTest() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date afterDate = new Date();
        Calendar afterCalendar = Calendar.getInstance();
        /**
         * true false -1
         *
         * compareTo：
         *              -1 <
         *              0 =
         *              1 >
         */
        System.out.println(date.before(afterDate)+" "+date.after(afterDate)+" "+date.compareTo(afterDate));

        System.out.println(calendar.before(afterCalendar)+" "+calendar.after(afterCalendar)+" "+calendar.compareTo(afterCalendar));
    }
}
