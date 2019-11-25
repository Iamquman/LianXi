package netdemo;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.regex.Pattern;

public class ChatMsg implements Serializable{
    final static public int MSG_TYPE_COMMAND = 0;
    final static public int MSG_TYPE_CHAT = 1;
    public int msgType;
    public String sender = "";
    public String reciever = "";
    public String message = "";
    public boolean isPrivate;
    public long time;

    private ChatMsg(){}
    public ChatMsg(ByteBuffer buff) throws Exception{
        this.msgType = buff.getInt();
        this.time = buff.getLong();
        this.isPrivate = buff.get() == 1;
        int len = buff.getShort();
        byte[] b = new byte[len];
        buff.get(b);
        this.sender = new String(b,"utf-8");
        len = buff.getShort();
        b = new byte[len];
        buff.get(b);
        this.reciever = new String(b,"utf-8");
        len = buff.getShort();
        b = new byte[len];
        buff.get(b);
        this.message = new String(b,"utf-8");
    }
    public ChatMsg(String send,String msg){
        this.sender = send;
        this.message = msg;
        this.time = System.currentTimeMillis();
        this.reciever = "所有人";
        this.isPrivate = false;
        this.msgType = MSG_TYPE_CHAT;
    }

    /**
     * 构造一个聊天消息,指定各个属性
     * @param send 发送者名字
     * @param recv 接收者名字
     * @param msg 消息内容
     * @param isPriv 是否为悄悄话,true表示是悄悄话
     */
    public ChatMsg(String send,String recv,String msg,boolean isPriv){
        this.sender = send;
        this.reciever = recv;
        this.message = msg;
        this.isPrivate = isPriv;
        this.time = System.currentTimeMillis();
        this.msgType = MSG_TYPE_CHAT;
    }

    public ChatMsg(String cmd){
        this.msgType = MSG_TYPE_COMMAND;
        this.message = cmd;
        this.time = System.currentTimeMillis();
    }
}





