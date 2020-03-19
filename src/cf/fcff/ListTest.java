package cf.fcff;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList();
//        list.get(0);//java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
//        list.add(null);
//        list.remove(null);
        String string = "me";
        list.add(string);
        string = null;
        System.out.println(" "+list.size());
    }
}
