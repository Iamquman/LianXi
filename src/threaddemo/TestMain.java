package threaddemo;

public class TestMain {
    public static void main(String[] args)  {
        //输出count
        Info a=new Info();
        System.out.println(a.getCount());
        //创建10个线程
        Thread[] t=new Thread[10];
        for(int i=0;i<10;i++){
            t[i]=new Thread(new QiangShouji());
        }
        //启动十个线程
        for(int i=0;i<10;i++){
            t[i].start();
        }
        //等待10个线程都结束
        for(int i=0;i<10;i++){
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //输出count
        System.out.println(a.getCount());
       /* Thread t0=new Thread(new QiangShouji());
        try {
            t0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
