package netdemo;
//客户端z
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
   public static void main(String[] args) throws Exception {
       //TODO
       int n=args.length;
       String ip="127.0.0.1";
       int port=7777;
       switch(n){
           case 0:
               break;
           case 1:
               //指定IP,端口设置为默认值
               ip=args[0];
               break;
           case 2:
               //指定ip,端口
               ip=args[0];
               port=Integer.valueOf(args[1]);
               break;
           default:
               //参数错误
               System.out.println("命令参数不正确");
               System.exit(1);
       }
       Scanner sc=new Scanner(System.in);
       Socket s=new Socket(ip,port);
       SocketThread t=new SocketThread(s) {
           public void onMessage(ChatMsg msg){
               if(msg.msgType==ChatMsg.MSG_TYPE_COMMAND){
                   System.out.println("服务器对你说："+msg.message);
               }else{
                   System.out.print(msg.sender);
                   System.out.println(msg.isPrivate?"悄悄":"");
                   System.out.println("对"+msg.reciever);
                   System.out.println("说："+msg.message);

               }
           }
       };
       t.start();
       String m; //键盘输入
       do{
           m=sc.nextLine();
           if(m.equals("end")) break;
           ChatMsg ms=new ChatMsg("me",m);
           t.send(ms);
       }while(true);
       t.stopService();
   }
}
