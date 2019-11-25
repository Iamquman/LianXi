//20190925
package fanshe;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class MainTest {
    Class c;   //类的类
    public static void main(String[] args) {
        try{
            Class o=Class.forName("chihuo.Food");
            System.out.println(o.getName());
            System.out.println(o.isInterface());

            Field[] fs=//o.getFields();  //获得其属性
            o.getDeclaredFields();
            System.out.println("属性有：");
            for(Field f:fs){
                System.out.println(f.getName());
            }

            Method[] ms=o.getMethods();
            System.out.println("方法有：");
            for(Method m:ms) {
                System.out.println(m.getName() + ":" + m.getDeclaringClass().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
