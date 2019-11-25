package chihuo;


public class CheckTimeoutTask extends Thread {
    private boolean run = true;
    private long mills = 10000;
    public void setTimeTip(int secs){
        this.mills = secs * 1000;
    }
    public void stopRunning(){
        this.run = false;
    }
    public void run(){
        //循环
        do {
            try {
                Thread.sleep(this.mills);
                Business.checkOrderTimeout();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //遍历订单,标记符合超时条件的
        }while (this.run);
    }
}
