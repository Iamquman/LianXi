import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class quman {
    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(80);
            Socket client = server.accept();
            InputStream is = client.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            while(bis.available()>0){
                int n = bis.available();
                byte[] buff = new byte[n];
                bis.read(buff);
                System.out.println(new String(buff,"utf-8"));
            }

            OutputStream os = client.getOutputStream();

            PrintWriter pw = new PrintWriter(os);

            String msg = "<html><head><title>Test</title></head><body><marquee><h1>Welcome!</h1></marquee></body></html>";
            int len = msg.length();

            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html");
            pw.println("Content-Length: "+len);
            pw.println("");
            pw.println("");
            pw.println(msg);
            pw.println("");
            pw.close();
            os.flush();
            os.close();
            client.close();
            System.out.println("end.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
