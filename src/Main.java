import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        Thread task = new Thread(server);
        task.setDaemon(true);
        task.start();
        Scanner sc = new Scanner(System.in);
        do{
            //
            String line = sc.nextLine();
            if(line.equals("end")) break;
            if(line.equals("ok")){
                server.resOK();
            }
            server.send(line);
        }while(true);
    }










        /*try {
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
*/
    }

