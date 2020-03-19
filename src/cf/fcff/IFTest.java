package cf.fcff;

/**
 * Created by H on 2018/8/22.
 */
public class IFTest {

    public static void main(String[] args) {
        if (true)sysOut("无空格");

        if (true) sysOut("有空格");

        if (true)
            sysOut("换行");
        String str = "o";
        if (true) str += "me";
        sysOut(str);
    }

    public static void sysOut(String string) {
        System.out.println(string);
    }
}
