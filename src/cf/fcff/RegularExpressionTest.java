package cf.fcff;

public class RegularExpressionTest {
    public static void main(String[] args) {
        String regularExpression = "^#[0-9a-fA-F]{6}$";
        String str = "#000000";
        if (str.matches(regularExpression)) {
            System.out.println(str);
        }
    }
}
