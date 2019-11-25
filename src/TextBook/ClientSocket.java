package TextBook;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket {
    public static void main(String[] args) {
        try {
            Socket ClientSocket=new Socket("127.0.0.1",2010);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
