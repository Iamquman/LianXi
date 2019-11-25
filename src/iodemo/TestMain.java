package iodemo;

//字节流：在java里最小的就是字节流
import java.io.*;

//字符流：
import java.io.BufferedReader;  //带有缓冲区的Read
import java.nio.ByteBuffer;

public class TestMain {


    static void readCharArray() {
        try{
        FileReader fr = null;
        fr = new FileReader(".\\files\\customers.csv");
        char[] chs = new char[4];
        int c = 0;
        do {
            c = fr.read(chs);
            System.out.println(chs);
        } while (c == 4);
        fr.close();
    }catch(IOException e){
            e.printStackTrace();
        }finally {
        }
    }

    static void readByChar(){
        try{
            FileReader fr=new FileReader("D:\\BIGDATA\\.metadata\\LianXi\\files\\ac.txt");
            int c = 0;
            do {
                c = fr.read();
                System.out.println(c);
            } while (c!=-1);
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {


        File f=new File(".\\files\\customers.csv");

        if(f.exists()){    //如果存在
            //直接打开
            System.out.println("文件存在");
        }else{
            System.out.println("文件不存在");
            boolean r=false;
            try{
               // r=f.createNewFile();   //创建
                writerBuffer();
            }catch(IOException e){
                r=false;
            }
            if(r){
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }

        }
       /* readCharArray();
        readByChar();*/
        listFiles();
        readCSV();
        writerTest();
    }

    static void writerBuffer() throws IOException{
        File f=new File(".\\files\\test.txt");
        if(f.exists()){
            System.out.println("文件已存在");
        }else{
            System.out.println("文件不存在");
            f.createNewFile();
        }
        FileWriter w=new FileWriter(f,true);
        //w.write("Hello啊\r\n挺好\n");
        for(char x:"This 追加内容\n".toCharArray()){
            w.append(x);
        }
        w.close();
    }
    //文件写入
    static void writerTest() throws IOException{
        File f=new File(".\\files\\test.txt");
        if(f.exists()){
            System.out.println("文件已存在");
        }else{
            System.out.println("文件不存在");
            f.createNewFile();
        }
        FileWriter w=new FileWriter(f);
        BufferedWriter bw= new BufferedWriter(w);
        bw.write("hello\n");
        bw.newLine();
        bw.write("--追加内容--",2,4);
        bw.close();
        w.close();
    }

    static void listFiles(){
        File f=new File(".\\files");
        if(f.isDirectory()){
            System.out.println("是个文件夹");
            String[] fs=f.list();
            System.out.println("内容有：");
            for(String s:fs){
                System.out.println(s);
            }
            File[] fss=f.listFiles();
            for(File fl:fss){
                System.out.println(fl.getName());
                if(fl.isDirectory()){
                    System.out.println("是个文件夹");
                }else{
                    System.out.println("是个文件");
                }
            }
        }else {
            System.out.println("不是文件夹");
        }
    }



    static void readCSV(){
        //CSV=Comma Split Values    逗号分隔的值
        File f=new File(".\\files\\customers.csv");
        if(f.exists()){
            try {
                FileReader fr=new FileReader(f);               //相当于直接喝
                BufferedReader br=new BufferedReader(fr);     //相当于拿箱子装
                String line=br.readLine();
                while(null!=line){
                    String[] cols=line.split(",");
                    System.out.println("电话："+cols[0]);
                    System.out.println("姓名："+cols[1]);
                    System.out.println("地址："+cols[2]);
                    line=br.readLine();
                    System.out.println("行："+line);
                }
               /* String line=null;
                do {
                    line=br.readLine();
                    System.out.println("行"+line);
                    //br.readLine();  //一行一行的读
                }while(null!=line);*/
                br.close();
                fr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //循环读整行，每行是一条记（即一个对象）
        //每行中用逗号分隔多个值
    }

    //使用字节流来读写文件
      static void bytesWrite() throws IOException{
        int a=65535;
        //字节流
          FileOutputStream fos=new FileOutputStream(".\\files\\a.dat");
          BufferedOutputStream bos=new BufferedOutputStream(fos);
          OutputStream os;
          fos.write(a);
          fos.close();
          bos.close();
      }

      static void bytesRead() throws IOException{
        int a=0;
        //字节流
          File f=new File(".\\files\\a.dat");
          FileOutputStream fos=new FileOutputStream(f);
          DataOutputStream dos =new DataOutputStream(fos);
          dos.write(a);
          dos.close();
          fos.close();
    }

}

