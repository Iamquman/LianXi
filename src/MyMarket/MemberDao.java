package MyMarket;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//操作类
public class MemberDao {
    static Scanner sc= new Scanner(System.in);
    ArrayList<Member> member=new ArrayList();

    public static void testFile(Member me){
        ArrayList<String> lines = new ArrayList<>();
        File f=new File(".\\files\\Member.txt");
        try {
            FileReader fr=new FileReader(f);
            BufferedReader br= new BufferedReader(fr);
            String line = br.readLine();
            while(null!=line){
                lines.add(line);
                line=br.readLine();
            }
            br.close();
            fr.close();

            boolean flag=true;
            for(String l:lines){
                String[] s=l.split(",");
                if(s[1].equals(me.getPhone())){
                    System.out.println("该手机号已被开通！");
                    flag=false;
                    break;
                }
;            }
            if(flag){
                fileMem(me);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void fileMem(Member me){
        File f=new File(".\\files\\Member.txt");
        String s=me.getName()+","+me.getPhone()+","+me.getKahao()+","+me.getRiqi();
        try {
            FileOutputStream fos=new FileOutputStream(f,true);
            byte[] b=s.getBytes();
            fos.write(b);
            String n="\n";
            byte[] n1=n.getBytes();
            fos.write(n1);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void kaiKa(){
        Member me=new Member();
        System.out.print("请输入注册姓名：");
        String name=sc.next();
        me.setName(name);
        System.out.print("请输入手机号：");
        do {
            String phone = sc.next();
            if (!me.setPhone(phone)) {
                System.out.println("输入有误，请重新输入：");
                continue;
            }else{
                me.setPhone(phone);
                break;
            }
        }while (true);
        System.out.print("请输入注册密码(不少于六位)：");
        do {
            String mima = sc.next();
            if(!me.setMima(mima)){
                System.out.println("输入不符合，请重新输入：");
                continue;
            }else {
                break;
            }
        }while(true);
        me.setKahao(mumber());
        me.setJifen(1000+me.getJifen());
        me.setRiqi(time());
        testFile(me);
    }

    public static String time(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd:hh");
        return sdf.format(date);
    }

    public static int mumber(){
        return (int)(Math.random()*100000000+1);
    }

    public static void start(){
        do {
        String choose = sc.next();
            switch (choose) {
                case "1":
                    //积分累计
                    break;
                case "2":
                    //积分兑换
                    break;
                case "3":
                    //查询剩余积分
                    break;
                case "4":
                    //开卡
                    kaiKa();
                    break;
                case "5":
                    //修改密码
                    break;
                case "6":
                    //退出
                    break;
                default:
                    System.out.println("输入有误，请重新输入：");
                    continue;
            }
            break;
        }while(true);
    }
    //开始菜单
    public static void caiDan(){
        System.out.println("********欢迎进入超市会员管理系统********");
        System.out.print("1.积分累计");
        System.out.print("\t\t\t\t");
        System.out.println("4.开卡");
        System.out.print("2.积分兑换");
        System.out.print("\t\t\t\t");
        System.out.println("5.修改密码");
        System.out.print("3.查询剩余积分");
        System.out.print("\t\t\t");
        System.out.println("6.退出");
        System.out.println("*************************************");
        System.out.println("请选择(1-6)：");
    }
    public static void main(String[] args) {
        caiDan();
        start();
    }
}
