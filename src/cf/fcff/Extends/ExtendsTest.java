package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ExtendsTest {

    public static void main(String[] args) {

        Children children = new Children();
//        ChildrenSuper children = new ChildrenSuper();
//        ChildrenSuperBefore children = new ChildrenSuperBefore();
//        ChildrenSuperAfter children = new ChildrenSuperAfter();

        children.protectedMethod();
        children.defaultMethod();
        children.publicMethod();

        Parent parent = new Parent();
        parent.defaultMethod();
        parent.protectedMethod();
        parent.publicMethod();
    }
}
