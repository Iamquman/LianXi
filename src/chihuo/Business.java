package chihuo;


//import com.sun.tools.corba.se.idl.constExpr.Or;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class Business {
    static Scanner sc = new Scanner(System.in);
    static Platform p = new Platform();

    //显示初始菜单
    static void showMenu(){
        System.out.println("1.顾客订餐");
        System.out.println("2.商家接单");
        System.out.println("3.查看平台全部订单");
        System.out.println("0.退出");
        System.out.print("请选择:");
    }

    //通过手机号判断是否为会员，若是则从顾客表中获取，若不是则创建新会员加入到顾客表中；返回顾客类型
    static Customer getCustomer(){
        Customer c=null;
        System.out.print("请输入电话:");
        String tel = sc.next();
        if(p.customers.containsKey(tel)){
            //从平台顾客表获取
             c=p.customers.get(tel);
            System.out.println("欢迎回来:"+c);
            //return c;
        }else {
            System.out.println("欢迎新会员！");
            System.out.print("请输入姓名:");
            String name = sc.next();
            System.out.print("请输入地址:");
            String addr = sc.next();

             c = new Customer(name, tel, addr);
            //新顾客加入顾客表
            p.customers.put(tel,c);
            //@TODO
            //更新数据文件
            addCustomerToFile(c);
            //return c;
        }
        return c;
    }

    //保存顾客信息至文件夹
    static void addCustomerToFile(Customer  c){
        //把顾客信息写入文件
        File f =new File(".\\files\\customers.csv");
        try{
            FileWriter fw =new FileWriter(f,true);
            BufferedWriter bw =new BufferedWriter(fw);
            bw.newLine();
            bw.write(c.telno);
            bw.write(",");
            bw.write(c.name);
            bw.write(",");
            bw.write(c.address);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //商家登录，若手机号正确则返回商家类型，否则继续循环
    static Restaurant getRestaurant(){
        do {
            System.out.println("请输入商家电话登录");
            String telno = sc.next();
            Restaurant r = p.getRestaurantByTelno(telno);
            if (null == r) {
                System.out.println("商户不存在或电话不正确");
            }else{
                return r;
            }
        }while(true);
    }

    //商家向平台请求接单
    static Order requestOrder(Restaurant r){
        //商家向平台请求接单
        //检索平台订单,返回符合条件的
        for(Order o:p.orderList){
            //同一商家 已提交的订单
            if(o.restaurant==r && o.status==Order.ST_SUBMIT){
                o.status = Order.ST_ACCEPT;
                return o;
            }
        }
        return null;
    }

    //显示所有订单
    static void showAllOrders(){
        //遍历全部订单
        for(Order o:p.orderList){
            System.out.println("-------------------");
            System.out.println(o);
        }
        System.out.println("------------------");
    }

    //保存订单至文件夹
     static void saveAllOrders(){
        //使用对象输出流 保存OrderList文件
        File f=new File(".\\files\\orders.obj");
        try{
            FileOutputStream fos=new FileOutputStream(f);  //构建文件输出流
            ObjectOutputStream oos=new ObjectOutputStream(fos);   //创建一个新的对象输出流
            oos.writeObject(p.orderList); //把平台的订单对象写入
            oos.close();
            fos.close();
            System.out.println("订单保存成功");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("订单保存失败");
        }
    }

    //每隔一分钟，检测一次订单是否超时
    static void checkOrderTimeout(){
       // System.out.println("检测订单超时...");
        //获取当前时间
        Instant iNow = Instant.now();
        for(Order o:p.orderList){
            Duration d = Duration.between(o.orderTime,iNow);
            if(d.getSeconds() > 60 && o.status==Order.ST_SUBMIT){
                o.status = Order.ST_CANCELED;
                System.out.println("订单:"+o.number+"因超时被取消");
            }
        }
    }

    //返回平台的所有商家
    public static List<Restaurant> getAllRestaurants() {
        return p.restaurants;
    }

    //订单提交
    public static void submit(Order o) {
        p.submit(o);
    }

    //顾客确认订单已完成
    static void confirm(Customer c){
        // 把所有订单中,顾客为c的,状态为商家已配送的列举给用户选择
        // 根据用户的选择,获取订单对象
        // 修改订单状态为 完成
    }


    public static void main(String[] args) {
        p.loadData();
        //创建一个线程,定时检查订单状态,超时则取消
        CheckTimeoutTask t = new CheckTimeoutTask();
        t.setTimeTip(10);
        t.start();
        boolean run = true;
        do {
            showMenu();
            int ch = sc.nextInt();
            switch (ch) {
                case 0:
                    run = false;
                    t.stopRunning();
                    //退出时  保存全部订单数据
                    saveAllOrders();
                    break;
                case 1:
                    Customer a = getCustomer();
                    a.order();
                    break;
                case 2:
                    Restaurant r = getRestaurant();
                    r.accept();
                    break;
                case 3:
                    showAllOrders();
                    break;
                default:
                    break;
            }
        }while(run);
        System.out.println("感谢使用,再见!");
    }


}



