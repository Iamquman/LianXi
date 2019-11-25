package netdemo;
//聊天服务器
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
import java.util.regex.Pattern;
import java.net.Socket;

public class ChatServer {
    //指令消息正则表达式
    static final Pattern cmdPatt=Pattern.compile("^#(\\w+)#(.*)");
    // 服务端socket
    static ServerSocket server;
    // 客户端集合,K为ip:port组合
    static Map<String,SocketThread> clients;

    static boolean start(){
        try {
            //创建服务端socket
            //绑定监听端口号
            server = new ServerSocket(7777);
            //创建客户端集合
            clients = new HashMap<>();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void service(){
        while(true) {
            //获取客户端连接
            try {
                Socket c = server.accept();
                SocketThread st = new SocketThread(c) {
                    @Override
                    public void onMessage(ChatMsg msg) {
                        ChatServer.dealMsg(msg,this);
                    }
                };
                st.start();
                //向客户端发送欢迎信息
                clients.put(st.getKey(),st);
                System.out.println("有客户端接入: " + st.getKey());
                st.send(new ChatMsg("哈喽啊!"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static void dealMsg(ChatMsg msg, SocketThread t){
        msg.sender = t.getKey();
        //判断消息类型:COMMAND或CHAT
        switch (msg.msgType){
            case ChatMsg.MSG_TYPE_COMMAND:
                // 处理指令消息
                if(msg.message.equals("list")){
                    Set<String> ou=ChatServer.getUsers();
                    StringBuilder sb = new StringBuilder();
                    sb.append("当前在线用户有:\n");
                    for(String a:ou){
                        sb.append(a);
                        sb.append("\n");
                    }
                    sb.append("===============\n");
                    sb.append("共");
                    sb.append(ou.size());
                    sb.append("人\n");
                    ChatMsg cm = new ChatMsg("服务器",t.getKey(),sb.toString(),true);
                    t.send(cm);
                    break;
                }
                if(msg.message.equals("name")){
                    String name = msg.reciever;
                    if(clients.containsKey(name)){
                        t.send(new ChatMsg("该名字已被占用:"+name));
                    }else{
                        SocketThread cli= clients.get(t.getKey());
                        clients.put(name,cli);
                        clients.remove(t.getKey());
                        t.setKey(name);
                        t.send(new ChatMsg("改名成功"));
                    }
                    break;
                }
                break;
            case ChatMsg.MSG_TYPE_CHAT:
                //处理聊天消息

                if(msg.isPrivate){
                    //私聊
                    SocketThread ct = clients.get(msg.reciever);
                    if(null!=ct) {
                        if(!ct.send(msg)){
                            //发送失败,认为客户端离线
                            clients.remove(msg.reciever);
                            System.out.println(msg.reciever+"已经离线");
                            t.send(new ChatMsg("发送失败,可能已经离线"));
                        }else{
                            //
                        }
                    }else{
                        //不在线
                        t.send(new ChatMsg("已经离开了"));
                    }
                }else{
                    //公开
                    broadcast(msg);
                }
                break;
            default:
        }
    }

    static void broadcast(ChatMsg m){
        //广播消息
        Set<String> willDele = new HashSet<>();
        Iterator<String> itr = clients.keySet().iterator();
        while(itr.hasNext()){
            String k = itr.next();
            SocketThread s = clients.get(k);
            if(!s.send(m)){
                willDele.add(k);
            }
        }
        for (String dk:willDele){
            clients.remove(dk);
            System.out.println(dk+"已离线");
        }
        System.out.println("广播消息:"+m);
    }

    static Set<String> getUsers(){
        return clients.keySet();
    }

    public static void main(String[] args) {
        if(start()){
            System.out.println("启动成功");
            service();
        }
    }
}
