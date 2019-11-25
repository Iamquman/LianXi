package xiancheng;

public class MyRunnable implements Runnable {
    public void run(){
        //输出10以内的整数
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行"+i);
        }
    }
}
