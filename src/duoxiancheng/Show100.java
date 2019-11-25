package duoxiancheng;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Show100 implements Runnable{
    @Override
    public void run() {
        //显示100个数字
        for(int i=0;i<1000;i++){
            Thread t=Thread.currentThread();
            System.out.println(t.getName()+":"+i);
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }*/
         //   System.out.println(i);

        }
    }
}
