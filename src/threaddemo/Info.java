package threaddemo;

public class Info {
    private int count =10000000;
    synchronized void minusCount(){     //synchronized 同步的    ：某个线程执行此方法时，其他线程不允许执行
        count--;
    }
    public int getCount(){
        return count;
    }
}
