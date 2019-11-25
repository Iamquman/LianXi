package MyMarket;

import java.util.ArrayList;
import java.util.Scanner;

//操作类
public class MemberDao {
    static Scanner sc= new Scanner(System.in);
    ArrayList<Member> member=new ArrayList();

    public static void kaiKa(){
        Member me=new Member();
        System.out.print("请输入注册姓名：");
        String name=sc.next();
        me.setName(name);
        System.out.print("请输入手机号：");
        String phone=sc.next();
        me.setPhone(phone);
        System.out.println("请输入注册密码：");
        int mima=sc.nextInt();
        me.setMima(mima);

    }
    public static void start(){
        do {
        int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    //积分累计
                    break;
                case 2:
                    //积分兑换
                    break;
                case 3:
                    //查询剩余积分
                    break;
                case 4:
                    //开卡
                    kaiKa();
                    break;
                case 5:
                    //修改密码
                    break;
                case 6:
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
    public static void main(String[] args){
        caiDan();
        start();
    }
}
