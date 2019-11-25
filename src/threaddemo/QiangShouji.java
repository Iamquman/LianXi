package threaddemo;

public class QiangShouji implements Runnable {
    private static Info a=new Info();
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            a.minusCount();
        }
    }
}
