package netdemo;
//显示本地IP和本地端口号，远程IP和远程端口号，并发送请求，读取字节数据
import javax.jws.Oneway;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
public class NryDemoMain {
    public static void main(String[] args) {
        testConnect();

    }
    static void testConnect(){
        //IP和端口号表达一个网络应用程序地址
        String s="GET / HTTP/1.1\\nHost: 127.0.0.1\\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36\\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\\nAccept-Language: zh-CN,zh;q=0.9\\n\\n\n";
        try {
            Socket c = new Socket("127.0.0.1", 8888);   //182.61.200.7
            System.out.println("连接成功");
            InetAddress la=c.getLocalAddress();
            System.out.println("本地地址："+la);
            System.out.println("本地端口："+c.getLocalPort());
            InetAddress ra=c.getInetAddress();
            System.out.println("远程地址："+ra);
            System.out.println("远程端口："+c.getPort());
            //收发数据
            OutputStream os=c.getOutputStream();
            BufferedOutputStream bos=new BufferedOutputStream(os);
            //输出流发送数据
            bos.write(s.getBytes());
            bos.flush();
            System.out.println("请求已发送");
            InputStream is=c.getInputStream();
            BufferedInputStream bis=new BufferedInputStream(is);
            //int n=0;   //流中可用的字节数
            int i=0;
           // byte[] content=new byte[10240];  //10k
            do{
                int n=bis.available();
                if(n>0) {
                    byte[] content = new byte[n];

                    bis.read(content);
                    i++;
                    // n=bis.available();
                    System.out.println(new String(content));
                }
                }while(i<20);
            //System.out.println(new String(content));

            c.close();
            System.out.println("连接已关闭");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
