package huiyuan;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

//平台类：积分累计，积分兑换，查询剩余积分，修改密码，开卡，退出
public class Platform {
    static Scanner sc=new Scanner(System.in);
    List<Member> memberList=new ArrayList<>();
    static int choose=0;

    static void showCaiDan(){
        System.out.println("*********************欢迎进入超市会员管理系统**********************");
        System.out.println("1.积分累计\t2.积分兑换\t3.查询剩余积分\t4.修改密码\t5.开卡\t6退出");
        System.out.println("******************************************************************");
        System.out.print("请选择：");
    }

    static void saveScore(){}
    static void minusScore(){}
    static void queryMember(){}
    static void modifyPswd(){}
    //开卡
    static void creatId(){
        Member member=new Member();
        System.out.println("请输入姓名：");
      //  member.getName()=sc.nextLine();


    }
    public static void main(String[] args) {
        do{
            showCaiDan();
            choose=sc.nextInt();
            switch (choose){
                case 1:
                    //积分累计
                    saveScore();
                    continue;
                case 2:
                    //积分兑换
                    minusScore();
                    continue;
                case 3:
                    //查询剩余积分
                    queryMember();
                    continue;
                case 4:
                    //修改密码
                    modifyPswd();
                    continue;
                case 5:
                    //开卡
                    creatId();
                    continue;
                case 6:
                    System.out.println("程序退出！");
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    continue;
            }
            break;
        }while (true);
        System.out.println("欢迎使用");
    }

}
