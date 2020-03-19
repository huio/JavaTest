package cf.fcff;

/**
 * Created by H on 2018/9/6.
 */
public class ArrayTest {

    public static void main(String[] args) {
        String string = "12345";
        String[] arr = string.split("/");
        for (String string1 : arr) {
            System.out.println(string1);
        }
    }
}
