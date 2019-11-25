package duoxiancheng;

import java.time.LocalTime;

//多线程
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
      //创建线程实例并启动
      //new Thread(new Show100()).start();
        Show100  a=new Show100();
        Show100 b=new Show100();
        new Thread(a).start();
        new Thread(b).start();
       /* Show100 a=new Show100();
        Thread k=new Thread(a);
        k.start();
        for(int i=0;i<1000;i++){
            Thread.sleep(1000);
            System.out.println("-----------------");
        }*/
       while(true){
           try{
               Thread.sleep(999);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
           System.out.println(LocalTime.now());
       }
    }
}
