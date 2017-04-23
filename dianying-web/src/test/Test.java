/**
 * Created by Administrator on 2016/12/30.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Class.forName("com.tianl.dianying.entity.Movie");
        System.out.println(c.getSuperclass().getName());
    }
}
