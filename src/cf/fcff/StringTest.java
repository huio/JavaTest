package cf.fcff;

/**
 * Created by H on 2018/9/6.
 */
public class StringTest {
    public static void main(String[] args) {
        String path = "/etc.me/he/love";
        int index = path.lastIndexOf("|");
        System.out.println(index);
        System.out.println(path.substring(0, index));
    }
}
