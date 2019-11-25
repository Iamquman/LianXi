import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable{
    private ServerSocket server;
    private Socket socket;
    private boolean loop = true;

    public HttpServer(){
        try{
            server = new ServerSocket(7777);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socket = server.accept();
            InputStream is = socket.getInputStream();
            byte[] b = new byte[128];

            do {
                int l = is.read(b);
                if(l>0) check(b);
            } while (loop);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static int check(byte[] b){
        //如果包含回车，只输出回车前面的内容，换行
        //不包含则输出全部，不换行
        System.out.println(new String(b));
        return 0;
    }

    public void resOK(){
        send("HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "Content-Length: 95\n" +
                "\n" +
                "<html><head><title>Test</title></head><body><marquee><h1>sansuixiaohai</h1></marquee></body></html>");
    }

    public boolean send(String s){
        try{
            OutputStream os = socket.getOutputStream();
            os.write(s.getBytes());
            os.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
