package user;

import java.io.*;
import java.util.*;

public class User {
    public static String name;
    public static String pws;
    //用户输入用户名和密码如果匹配则可修改，若不匹配，则提示不正确
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] li=new String[2];
        try{
            File f=new File(".\\files\\user.txt");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            li=line.split(",");
        }catch(IOException e){
            e.getStackTrace();
        }
        System.out.print("请输入用户名：");
        name=sc.next();
        System.out.print("请输入密码：");
        pws=sc.next();
        if(name.equals(li[0])&&pws.equals(li[1])){
            System.out.print("请输入新密码：");
            pws=sc.next();
            System.out.println("您的新密码是："+pws);
        }else{
            System.out.println("用户名或密码输入不正确！");
        }
        System.out.println("运行结束");

    }
}
