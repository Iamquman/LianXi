package threaddemo;
import java.time.Instant;
public class TimeProc extends Thread {
    private String name;
    private int seconds;
    public TimeProc(String name,int secs){
        this.name=name;
        this.seconds=secs;
    }
    public void run(){
        Instant beginTime=Instant.now();
        System.out.println("任务：" +this.name+"开始于："+beginTime.toString());
        try {
            for(int i=0;i<this.seconds;i++){
                Thread.sleep(1000);
                //显示进度
                int p=100*(i+1)/seconds;
                System.out.println("任务："+this.name+"进度："+p+"%");
            }
            System.out.println("任务："+this.name+"结束于："+beginTime.toString());

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
