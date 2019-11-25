package TextBook;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ServerSocket serverForClient;

    public static void main(String[] args){
       try {
           serverForClient = new ServerSocket(2010);
           Socket sc=serverForClient.accept(); //讲客户端套接字与服务器端套接字连接起来
           OutputStream os=sc.getOutputStream();
           os.write(1);

       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
