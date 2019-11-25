package redemo;
//正则表达式

import javax.swing.*;
import java.time.Period;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main  {
    /*
    * 1.判断姓名是否姓张
    * 2.判断是否包含元音字母[aeiou]
    * 3.在上述格式的地址中提取  城市|区|路|门牌号
    * */
    //手机号:  1[3456789]\\d{9}  或   1[3456789][0123456789]{9}   或     1[3-9][0-9]{9}
    //1.编译正则表达式，得到范式对象
    private static final String restr_English="^[A-Z][a-z]*$";
    private static final String restr_zhang="^张";
    private static final String restr_lU="区[^00-xff]+路";
    private static final String restr_HAO="\\d\\d\\d号";   //  \\d{1,}号
    private static final String restr_pinyin="[aeiou]{1,}";
    static Pattern p = Pattern.compile(restr_English);

    public static void main(String[] args) {
        getAddress();
        //getDiZhi();
       // getChinese();
          //jiXiangHao();
       // testTxt();

        /*    Scanner sc=new Scanner(System.in);
        String name=null;
        do{
            System.out.println("姓名：");
            name=sc.nextLine();
            //2.对匹配的内容调用Matcher方法，得到matcher对象
            Matcher m = p.matcher(name);
            //3.m.find() 表示是否找到
            if(m.find()) {
                System.out.println("找到了");
                System.out.println(m.group());
            }
           *//* if(name.matches(restr_zhang)){
                System.out.println("9折优惠");
            }else{
                System.out.println("无优惠");
            }*//*
        }while(null!=name);*/

       /* Scanner sc=new Scanner(System.in);
        String name=null;
        do{
            System.out.println("请输入姓名：");
            name=sc.nextLine();
            char fn=name.charAt(0);    //拿到第一个字判断是不是 张
            if(fn=='张'){
                System.out.println("9折优惠");
            }else{
                System.out.println("无优惠");
            }
        }while(null!=name);*/
    }

    static void testTxt(){
        String txt="我叫史铁旺，今年22岁，是个自我认为很（不做评价）的人，虽说2020年小康社会，但小康在20年前就已降临我史家，2050年的世界强国我等着";
        System.out.println(txt);
        String sr="\\d+年";
        Pattern p1= Pattern.compile(sr);
        Matcher m=p1.matcher(txt);
        do{
            if(m.find()){
                System.out.println(m.group());
            }
        }while(true);

    }

    //输入手机号，做规范性验证和判断是否是吉祥号AAAA
    static void jiXiangHao(){
        String re1="^1[3-9]\\d{9}$";
        //String re2="(\\d)\\1\\1\\1$";    //AAAA
        //String re2="((\\d)(?!\\2)(\\d)(?!\\3))\\1$";    //ABAB
        String re2="(\\d)(?!\\1)(\\d)\\2\\1";         //ABBA
        Pattern p1=Pattern.compile(re1);
        Pattern p2=Pattern.compile(re2);
        Scanner sc=new Scanner(System.in);
        String snum=null;
        //反复输入手机号并验证
        do{
            snum=sc.nextLine();
            Matcher m=p1.matcher(snum);
            if(m.find()){
                //是手机号
                System.out.println("是手机号");
                m=p2.matcher(snum);
                if(m.find()){
                    System.out.println("有四位数字");
                }
            }else{
                //不是手机号
                System.out.println("非手机号，程序结束");
                break;
            }
        }while(true);
    }

    static void getChinese(){
        String re="[\\u4e00-\\u9fa5]";
        Pattern p=Pattern.compile(re);
        String s="辽宁省沈阳市铁西区重工街7号";
        Matcher m=p.matcher(s);
        if(m.find()){
            System.out.println(m.group());
        }
    }

    static void getDiZhi(){
        String re="(.*[省市区])?(.*[市])(.*[区县])(.+[路乡])(.+[号村])";
        Pattern p=Pattern.compile(re);
        Scanner sc=new Scanner(System.in);
        String snum=null;

        do {
            System.out.println("请输入地址：");
            snum=sc.nextLine();
            Matcher m=p.matcher(snum);
            if (m.find()) {
                System.out.println(m.group());
                int j=m.groupCount();
                for(int i=1;i<=j;i++){
                        System.out.println(m.group(i));
                }
            }
        }while(snum!=null);
    }


    static void getAddress(){
        String re="([\\u4e00-\\u9fa5]+[省|市])([\\u4e00-\\u9fa5]+[区])([\\u4e00-\\u9fa5]+[县路街])(\\d+号)";
        Pattern p=Pattern.compile(re);
        Scanner sc=new Scanner(System.in);
        String snum=null;

        do {
            System.out.println("请输入地址：");
            snum=sc.nextLine();
            Matcher m=p.matcher(snum);
            if (m.find()) {
                System.out.println(m.group());
                int j=m.groupCount();
                for(int i=1;i<=j;i++){
                    System.out.println(m.group(i));
                }
            }
        }while(snum!=null);

    }

}
