package netdemo;
//字节缓冲流
import java.io.BufferedInputStream;
import java.nio.ByteBuffer;

public class ByteBufferDemo {
    static void demo(){
        ByteBuffer b= ByteBuffer.allocate(4096);   //java.nio包里的byteBuffer,一个字节缓冲区；allocate分配一个新的字节缓冲区；参数为容量
        b.putInt(255);   //4   用于写入int值的绝对put方法
        b.putInt(85);    //4
        String m="Hello,Java!";
        short len=(short)m.length();
        byte[] c=m.getBytes();
        b.putShort(len);  //2
        b.put(c);  //len
         // byte[] r=new byte[len+10];
        byte[] r=new byte[b.position()];   //position:返回此缓冲区的位置
        //b.flip();
       // byte[] r=b.array();
        b.rewind();  //把目前进行到的位置归0  因为要查看每一个位置上的字节，所以要把b的位置移到最前面，向下一次读取
        b.get(r);
        System.out.println(r);   //[B@677327b6
        for(byte bt:r){
            System.out.println(bt);     // 255：0 0 0 -1   85: 0 0 0 85 short：0 11   Hello,java!:72 101 108 108 111 44 74 97 118 97 33
        }
        System.out.println("-----------");
        b.rewind();
        int a=b.getInt();
        System.out.println(a);
        a=b.getInt();
        System.out.println(a);
        a = b.getShort();
        System.out.println(a);
        byte[] k=new byte[a];
        b.get(k);
        System.out.println(new String(k));
    }

    public static void main(String[] args) {
        demo();
    }
}
