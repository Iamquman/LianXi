package threaddemo;
//20190918
public class TimeRun {
    public static void main(String[] args) {
        new TimeProc("下载MP3",25).start();
        new TimeProc("下载JPG",12).start();
        new TimeProc("播放视频",10).start();


        /*System.out.println("开始："+System.currentTimeMillis());
        try {
            for(int i=0;i<17;i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束："+System.currentTimeMillis());*/
    }
}
