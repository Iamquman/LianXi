package netdemo;


import java.io.*;
import java.net.SocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public abstract class SocketThread extends Thread{
    // 控制是否循环
    private boolean loop = true;
    // 线程服务的客户端socket
    private Socket socket;
    // 客户端socket的输入流
    private InputStream input;
    // 客户端的socket原始key ip:port
    private String key;
    //接受数据的缓冲区
    private ByteBuffer buffer=ByteBuffer.allocate(512);
    //消息头第一个字节
    private byte h1=0;
    //消息头第二个字节
    private byte h2=0;

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key=key;
    }

    private int readBuffer() throws Exception{
        //读取缓冲区,返回字节数
        int a;//记录输入流中可读字节数
        int b;//记录缓冲区中剩余空间
        int c;//a和b中较小的一个,表示可存入缓冲区的字节数

        a = input.available();//输入流中可读取数
        b = buffer.remaining();// 指针后面所剩下的字节数
        c = a < b ? a : b;
        // 读取c字节到缓存
        for (int i = 0; i < c; i++) {
            buffer.put((byte) input.read());
        }
        return c;
    }

    private int findHead(){
        // 寻找消息的头字节，如果找不到，rewind（不要了）
        boolean found = false;// 假设找不到
        int pos = buffer.position();
        buffer.rewind();
        //bug就在这里
        for (int i = 0; i < pos; i++) {
            h1 = h2;
            h2 = buffer.get();
            if (h1 == (byte)0xfe && h2 == (byte)0xfd) {
                // 找到了消息头
                return pos;
            }
        }
        return -1;
    }

    private ChatMsg decodeMsg(int pos) throws Exception{
        ChatMsg msg = null;
        // 如果找到，读取详细长度信息
        int len = buffer.getShort();
        // 判断剩余到pos的数据是否够一个消息
        if (pos - buffer.position() - len >= 0) {
            // 如果够:解析消息，并且重置buffer
            msg = new ChatMsg(buffer);
            // 转移buffer内剩余的数据到开头的部分
            int c = buffer.position();
            buffer.rewind();
            for (int i = c; i < pos; i++) {
                buffer.put(buffer.get(i));
            }
        }else{
            //如果找到消息头以后,剩余字节不足以表达完整消息,归还消息头
            buffer.position(buffer.position()-4);
        }
        return msg;
    }

    public SocketThread(Socket s) throws IOException{
        this.socket = s;
        String ip = s.getInetAddress().toString();
        int port = s.getPort();
        this.key = ip+":"+port;
        this.input = s.getInputStream();
    }

    public void run(){
        while (this.loop) {
            try {
                // 读取输入流字节到缓冲区，尝试解码
                int c = readBuffer();
                if(c==0){//如果没有读到字节,不解析
                    continue;
                }
                //找消息头,-1表示没有找到
                int pos = findHead();
                if (pos<0) {
                    buffer.rewind();
                } else {
                    ChatMsg m = decodeMsg(pos);
                    onMessage(m);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            this.input.close();
            this.socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopService(){
        this.loop = false;
    }

    public boolean send(ChatMsg m){
        try {
            OutputStream os = this.socket.getOutputStream();
            //序列化 ChatMsg
            //消息标记字节2个: fefd
            //消息长度字节2个: short类型
            //消息类型4字节: int msgType
            //消息时间8字节: long
            //消息属性1字节: isPriv: 1字节
            //消息属性长度2字节n1:sender
            //消息属性值n1个字节:sender.getBytes
            //reciever
            //message
            ByteBuffer bf = ByteBuffer.allocate(256);
            short n = 13;
            byte[] sndBytes = m.sender.getBytes("utf-8");
            short n1 = (short)sndBytes.length;
            byte[] rcvBytes = m.reciever.getBytes("utf-8");
            short n2 =(short)rcvBytes.length;
            byte[] msgBytes = m.message.getBytes("utf-8");
            short n3 =(short)msgBytes.length;
            n += n1;
            n += n2;
            n += n3;
            n += 6;
            bf.put((byte)0xfe);
            bf.put((byte)0xfd);
            bf.putShort(n);
            bf.putInt(m.msgType);
            bf.putLong(m.time);
            bf.put(m.isPrivate?(byte)1:(byte)0);
            bf.putShort(n1);
            bf.put(sndBytes);
            bf.putShort(n2);
            bf.put(rcvBytes);
            bf.putShort(n3);
            bf.put(msgBytes);
            os.write(bf.array(),0,bf.position());
            os.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public abstract void onMessage(ChatMsg msg);  //相当于事件
}
