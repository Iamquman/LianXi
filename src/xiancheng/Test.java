package xiancheng;

public class Test {
    public static void main(String[] args) {
        System.out.println("*************线程强制执行**************");
        Thread t=new Thread(new MyRunnable(),"bb");
        t.start();
        for(int i=0;i<20;i++){
            //当主线程执行到i==5时，暂停执行，让子线程执行完毕再执行
            if(i==5){
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行"+i);

        }
    }
}
