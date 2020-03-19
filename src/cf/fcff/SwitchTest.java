package cf.fcff;

/**
 * Created by H on 2018/9/27.
 *
 * @author H
 */
public class SwitchTest {
    public static void main(String[] args) {
        int i = 4;
        switch (i) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
