package iodemo;
import java.io.*;
//20190923
class Car implements Serializable{
    long serialVersionUID=-733L;
    String color;
    void run(){}
}
public class EndianTest {

    public static void main(String[] args) {
        //将对象写到一个流里
        ObjectOutputStream oos=null;
        Car x=new Car();
        x.color="red";
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            oos.writeObject(x);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        byte[] by={(byte)228,(byte)184,(byte)173,(byte)229,(byte)155,(byte)189};
        System.out.println(by);
        try {
            System.out.println(new String(by,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        testSerial();
        //字节序
        //将int拆成4个字节
        int a=28951540;//它的第四个字节为00000001  三：10111001    11000011    11110100
       /* //b0表示00000001
        byte b0=(byte)(a/(256*256*256));
        byte b1=(byte)(a/(256*256*256)-b0*256);
        byte b2=(byte)(a/(256*256*256)-b0*256*256-b1*256);
        byte b3=(byte)(a/(256*256*256)-b0*256*256*256-b1*256*256-b2*256);*/
      /*  //直接对256求余都会得到最低的字节
        int b=a%256;
        System.out.println(b);
        byte b0=(byte) b;
*/
      byte[] b=new byte[8];
      int i=0;
        do{
            //System.out.println(a%256);
            //System.out.println(a & 255);
            b[i]=(byte)(a&255);  //对256求余
            i++;
            a>>=8;  //a向右移8位再付给a 相当于 a=a/256
        }while(a>0);
       //把字节数组写到文件.\\files\\long.dat里
        try {
            FileOutputStream fos = new FileOutputStream(".\\files\\long.dat");
            fos.write(b);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //每拿到一个字节，放到相应位置
      long c=0;
      for(i=0;i<b.length;i++){
          c +=((int)b[i] & 255)<<(i*8);  //强转为int , 用与运算将前三个字节换成0（负数为1） ，再移位
      }
      System.out.println(c);

    }

    static void testEndian(){
        long a=28951540;
        try {
            FileOutputStream fos=new FileOutputStream(".\\files\\long1.dat");
            DataOutputStream dos=new DataOutputStream(fos);
            dos.writeLong(a);
            dos.close();
            fos.close();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
     static void testSerial(){
        String s="Hello,中国！";
         try {
             FileOutputStream fos=new FileOutputStream(".\\files\\txt1.dat");
             DataOutputStream dos=new DataOutputStream(fos);
            // dos.writeBytes(s);
             // dos.writeUTF(s);    //
             //dos.write(s.getBytes("GBK"));  //两个字符
             dos.write(s.getBytes("UTF-8"));  //三个字节,例：中：228，184，173
             dos.close();
             fos.close();
             //读，并显示每个字节
             FileInputStream fis=new FileInputStream(".\\files\\txt1.dat");
             int n=fis.available();
             for(int i=0;i<n;i++){
                 System.out.println(fis.read());
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
